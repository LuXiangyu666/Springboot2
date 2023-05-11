package com.java1234.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**商品*/
@TableName("t_complaint")
@Data
public class Complaint {

    private Integer id; // 编号

    private Integer buyerId; // 买家id

    private Integer sellerId; // 卖家id

    private String content;   // 投诉内容

    private String pic; // 投诉图片

    private Integer state;   //投诉审核状态   0 审核未通过  1 未审核  2 审核通过

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")            //加入时间格式注解
    private Date publishTime;    //发布投诉时间

    @TableField(select = false,exist = false)
    private ComplaintImage[] complaintImg; // 轮播图片

}
