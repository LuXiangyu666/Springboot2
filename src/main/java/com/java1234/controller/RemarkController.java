package com.java1234.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.java1234.entity.*;
import com.java1234.service.IRemarkService;
import com.java1234.util.*;
import io.jsonwebtoken.Claims;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**留言Controller控制器*/
@RestController
@RequestMapping("/remark")
public class RemarkController {

    @Autowired
    private IRemarkService remarkService;

    //创建新留言，返回留言id
    @RequestMapping("/createRemark")
    @Transactional
    public R create(@RequestBody Remark remark){
        //添加留言到数据库
        remark.setTime(new Date());
        remarkService.save(remark);
        System.out.println(remark.getId());
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("remarkId",remark.getId());
        return R.ok(resultMap);       //返回商品id
    }


    //获取商品留言
    @GetMapping("/getRemark")
    public R getRemark(Integer id){
        Page<Remark> page=new Page<>(0,20);
        Page<Remark> pageRemark = remarkService.page(page, new QueryWrapper<Remark>().eq("product_id", id).orderByDesc("time"));
        List<Remark> RemarkList = pageRemark.getRecords();
        Map<String,Object> map=new HashMap<>();
        map.put("message",RemarkList);
        return R.ok(map);
    }

    //删除商品留言
    @GetMapping("/delete")
    public R delete(Integer id){
        remarkService.removeById(id);
        return R.ok("删除成功");
    }
}
