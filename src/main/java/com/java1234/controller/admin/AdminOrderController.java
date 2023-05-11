package com.java1234.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.java1234.entity.*;
import com.java1234.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**管理员-订单Controller控制器*/
@RestController
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Autowired
    private IWxUserInfoService wxUserInfoService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private IComplaintService complaintService;

    @Autowired
    private IComplaintImageService complaintImageService;


    /**查询待审核的投诉*/
    @RequestMapping("/complaintList")
    public R complaint(@RequestBody PageBean pageBean){
        Map<String,Object> map=new HashMap<>();
        map.put("name",pageBean.getQuery().trim());
        map.put("start",pageBean.getStart());
        map.put("pageSize",pageBean.getPageSize());
        System.out.println(888);
        System.out.println(map);
        System.out.println(888);
        List<Complaint> complaintList = complaintService.list(new QueryWrapper<Complaint>().eq("state",1));
        System.out.println(666);
        System.out.println(complaintList);
        System.out.println(666);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("complaintList",complaintList);
        return R.ok(resultMap);
    }

    /**更新投诉审核状态*/
    @PostMapping("/sh_complaint")
    public R sh_complaint(@RequestBody Complaint complaint){
        System.out.println("complaint="+complaint);
        Complaint resultComplaint = complaintService.getById(complaint.getId());
        resultComplaint.setState(complaint.getState());
        System.out.println("resultComplaint="+resultComplaint);
        complaintService.saveOrUpdate(resultComplaint);

        int sellerId = resultComplaint.getSellerId();
        WxUserInfo seller = wxUserInfoService.getById(sellerId);
        System.out.println("seller="+seller);
        int score = seller.getScore();
        if(complaint.getState()==2){
            score = score - 2;
        }
        seller.setScore(score);
        System.out.println("seller="+seller);
        wxUserInfoService.saveOrUpdate(seller);
        return R.ok("审核完成");
    }



    /**根据条件分页查询*/
    @RequestMapping("/list")
    public R list(@RequestBody PageBean pageBean){
        System.out.println(pageBean);
        Map<String,Object> map=new HashMap<>();
        map.put("orderNo",pageBean.getQuery().trim());
        map.put("start",pageBean.getStart());
        map.put("pageSize",pageBean.getPageSize());
        List<Order> orderList=orderService.list(map);
        Long total=orderService.getTotal(map);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("orderList",orderList);
        resultMap.put("total",total);
        return R.ok(resultMap);
    }

    /**更新订单状态*/
    @PostMapping("/updateStatus")
    public R updateStatus(@RequestBody Order order){
        System.out.println("更新订单状态"+order);
        Order resultOrder = orderService.getById(order.getId());
        resultOrder.setStatus(order.getStatus());
        //resultOrder.setStatus(order.getStatus());
        orderService.saveOrUpdate(resultOrder);
        System.out.println("更新订单后："+resultOrder);
        return R.ok();
    }

    /**删除*/
    @GetMapping("/delete/{id}")
    //通过 @PathVariable 可以将 URL 中占位符参数绑定到控制器处理方法的入参中
    public R delete(@PathVariable(value = "id")Integer id){
        // 删除订单细表数据
        orderDetailService.remove(new QueryWrapper<OrderDetail>().eq("mId",id));
        orderService.removeById(id);
        return R.ok();
    }

}
