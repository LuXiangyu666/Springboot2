package com.java1234.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.java1234.entity.Order;
import com.java1234.entity.Product;
import com.java1234.entity.ProductSwiperImage;
import com.java1234.entity.R;
import com.java1234.mapper.ProductMapper;
import com.java1234.service.IProductService;
import com.java1234.service.IProductSwiperImageService;
import com.java1234.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 商品Controller*/
//@RestController：用于标注控制层组件,这个controller里的方法的返回值都以响应形式返回
@RestController
//@RequestMapping：提供路由信息，负责URL到Controller中的具体函数的映射。
@RequestMapping("/product")
public class ProductController {

    //@Autowired：自动导入依赖的bean
    @Autowired
    private IProductService productService;

    @Autowired
    private IProductSwiperImageService productSwiperImageService;

    /**查询轮播商品*/
    //@GetMapping用于处理请求方法的GET类型
    //@PostMapping用于处理请求方法的POST*类型等。
    @GetMapping("/findSwiper")
    public R findSwiper(){
        List<Product> swiperProductList = productService.list(new QueryWrapper<Product>().eq("isSwiper", true).eq("state",2).orderByAsc("swiperSort"));
        Map<String,Object> map=new HashMap<>();
        map.put("message",swiperProductList);
        return R.ok(map);
    }



    /**查询热门推荐商品8个*/
    @GetMapping("/findHot")
    public R findHot(){
        Page<Product> page=new Page<>(0,8);
        Page<Product> pageProduct = productService.page(page, new QueryWrapper<Product>().eq("isHot", true).eq("state",2).orderByAsc("hotDateTime"));
        List<Product> hotProductList = pageProduct.getRecords();
        Map<String,Object> map=new HashMap<>();
        map.put("message",hotProductList);
        return R.ok(map);
    }

    /**根据id查询商品信息*/
    @GetMapping("/detail")
    public R detail(Integer id){
        Product product = productService.getById(id);
        List<ProductSwiperImage> productSwiperImageList = productSwiperImageService.list(new QueryWrapper<ProductSwiperImage>().eq("productId", product.getId()).orderByAsc("sort"));
        product.setProductSwiperImageList(productSwiperImageList);
        Map<String,Object> map=new HashMap<>();
        map.put("message",product);
        return R.ok(map);
    }

    /**商品搜索*/
    @GetMapping("/search")
    public R search(String q){
        List<Product> productList = productService.list(new QueryWrapper<Product>().like("name", q).eq("state",2));
        Map<String,Object> map=new HashMap<>();
        map.put("message",productList);
        return R.ok(map);
    }

    /**我的商品列表*/
    @GetMapping("/myproduct")
    public R myproduct(Integer id){
        Page<Product> page=new Page<>(0,30);
        Page<Product> pageProduct = productService.page(page, new QueryWrapper<Product>().eq("sellerId", id).orderByDesc("createDate"));
        List<Product> myProductList = pageProduct.getRecords();
        Map<String,Object> map=new HashMap<>();
        map.put("message",myProductList);
        return R.ok(map);
    }

    /**修改我的商品 */
    @RequestMapping("/revise")
    //@RequestHeader，可以将请求头中变量值映射到控制器的参数中
    //@ResponseBody的作用其实是将java对象转为json格式的数据
    public R save(@RequestBody Product product){
        productService.update(product);
        return R.ok();
    }



    /**卖家发货*/
    @RequestMapping("/fahuo")
    public R fahuo(Integer id){
        Product resultProduct = productService.getById(id);
        resultProduct.setState(4);
        productService.saveOrUpdate(resultProduct);
        return R.ok("修改成功，商品已发货");
    }

    /**买家收货*/
    @RequestMapping("/shouhuo")
    public R shouhuo(Integer id){
        Product resultProduct = productService.getById(id);
        resultProduct.setState(5);
        productService.saveOrUpdate(resultProduct);
        return R.ok("修改成功，商品已收货");
    }

    /**买家提交订单，修改商品状态为3未发货*/
    @RequestMapping("/noFaHuo")
    public R noFaHuo(Integer id){
        Product resultProduct = productService.getById(id);
        resultProduct.setState(3);
        int stock = resultProduct.getStock();
        stock--;
        resultProduct.setStock(stock);
        productService.saveOrUpdate(resultProduct);
        return R.ok("修改成功，商品状态已改为未发货");
    }



}
