package com.java1234.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import java.util.Date;

/**商品大类 */
@TableName("t_remark")
@Data
public class Remark {

    private Integer id; // 编号

    private Integer user_id; //发表留言的用户id

    private Integer product_id; //商品id

    private String content; // 留言内容

    @JsonSerialize(using=CustomDateTimeSerializer.class)
    private Date time;    //商品创建时间    1111111111111

    private String wxuserImg; //用户头像图片名

    private String user_name; // 用户昵称
}
