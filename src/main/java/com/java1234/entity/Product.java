package com.java1234.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**商品*/
@TableName("t_product")
@Data
public class Product {

    private Integer id; // 编号

    private String name; // 名称

    private BigDecimal price; // 价格

//    private String productIntroImgs; // 商品介绍图片
//
//    private String productParaImgs;  // 商品规格参数图片

    private Integer stock; // 库存

    private String chooseMode;  //交易方式  11111111111111111111111

    private Integer typeId;     //商品类别id    1111111111111111

    private String proPic="default.jpg"; // 商品图片

    private boolean isHot=false; // 是否热门推荐商品

    private boolean isSwiper=false; // 是否轮播图片商品

    private Integer swiperSort=0; // 轮播排序

    private String swiperPic="default.jpg"; // 商品轮播图片

    private String description; // 描述

    private String address;   //卖家地址    1111111111111111

    private float longitude;  //卖家经度    11111111111111111

    private float latitude;   //卖家纬度    111111111111111

    private Integer sellerId;   //卖家的id     1111111111111

    private Integer state =1; // 商品状态 1 审核中 2 已上架 3 未发货 4 已发货 5 已卖出

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")            //加入时间格式注解
    private Date createDate;    //商品创建时间    1111111111111


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")           //加入时间格式注解
    @JsonSerialize(using=CustomDateTimeSerializer.class)
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Date hotDateTime; // 设置热门推荐日期时间

    @TableField(select = false)
    private List<ProductSwiperImage> productSwiperImageList;

    @TableField(select = false)
    private SmallType type; // 商品类别

    @TableField(select = false,exist = false)
    private ProductSwiperImage[] swiperTab; // 轮播图片       111111111

}
