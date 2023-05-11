package com.java1234.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java1234.entity.Complaint;
import com.java1234.mapper.ComplaintMapper;
import com.java1234.service.IComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**商品大类Service实现类*/
@Service("complaintService")
public class IComplaintServiceImpl extends ServiceImpl<ComplaintMapper,Complaint> implements IComplaintService {

    @Autowired
    private ComplaintMapper complaintMapper;

    @Override
    public Long getTotal(Map<String, Object> map) {
        return complaintMapper.getTotal(map);
    }
}
