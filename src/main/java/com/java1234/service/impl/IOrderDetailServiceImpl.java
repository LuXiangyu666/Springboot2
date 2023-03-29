package com.java1234.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java1234.entity.OrderDetail;
import com.java1234.mapper.OrderDetailMapper;
import com.java1234.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**订单详情Service实现类*/
@Service("orderDetailService")
public class IOrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper,OrderDetail> implements IOrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;
}
