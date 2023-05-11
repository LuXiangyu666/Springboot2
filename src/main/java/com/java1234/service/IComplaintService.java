package com.java1234.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.java1234.entity.Complaint;

import java.util.Map;

/**商品大类Service接口*/
public interface IComplaintService extends IService<Complaint> {

    Long getTotal(Map<String,Object> map);
}
