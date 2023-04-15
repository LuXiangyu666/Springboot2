package com.java1234.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java1234.entity.Remark;

/**商品大类Mapper接口*/
public interface RemarkMapper extends BaseMapper<Remark> {

    public Remark findById(Integer id);
}
