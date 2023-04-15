package com.java1234.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java1234.entity.Remark;
import com.java1234.mapper.RemarkMapper;
import com.java1234.service.IRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**商品大类Service实现类*/
@Service("remarkService")
public class IRemarkServiceImpl extends ServiceImpl<RemarkMapper,Remark> implements IRemarkService {

    @Autowired
    private RemarkMapper remarkMapper;
}
