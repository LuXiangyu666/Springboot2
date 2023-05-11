package com.java1234.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**产品轮播图片*/
@TableName("t_complaintimage")
@Data
public class ComplaintImage {

    private Integer id; // 编号

    private String image; // 图片名称

    private Integer sort; // 排列序号 从小到大排序

    private Integer complaintId; // 所属投诉

}
