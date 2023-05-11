package com.java1234.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java1234.entity.ComplaintImage;
import com.java1234.mapper.ComplaintImageMapper;
import com.java1234.service.IComplaintImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**商品大类Service实现类*/
@Service("complaintImageService")
public class IComplaintImageServiceImpl extends ServiceImpl<ComplaintImageMapper,ComplaintImage> implements IComplaintImageService {

    @Autowired
    private ComplaintImageMapper complaintImageMapper;
}
