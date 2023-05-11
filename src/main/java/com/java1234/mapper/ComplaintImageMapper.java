package com.java1234.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java1234.entity.ComplaintImage;

/**商品大类Mapper接口*/
public interface ComplaintImageMapper extends BaseMapper<ComplaintImage> {

    public ComplaintImage findById(Integer id);
}
