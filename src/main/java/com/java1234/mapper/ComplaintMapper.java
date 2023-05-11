package com.java1234.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java1234.entity.Complaint;

import java.util.Map;

/**商品大类Mapper接口*/
public interface ComplaintMapper extends BaseMapper<Complaint> {

    public Complaint findById(Integer id);

    Long getTotal(Map<String,Object> map);
}
