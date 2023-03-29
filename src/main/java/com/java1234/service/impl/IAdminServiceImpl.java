package com.java1234.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java1234.entity.Admin;
import com.java1234.mapper.AdminMapper;
import com.java1234.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**管理员Service实现类*/
@Service("adminService")
public class IAdminServiceImpl extends ServiceImpl<AdminMapper,Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;
}
