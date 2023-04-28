package com.java1234.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.java1234.constant.SystemConstant;
import com.java1234.entity.R;
import com.java1234.entity.WxUserInfo;
import com.java1234.properties.WeixinProperties;
import com.java1234.service.IWxUserInfoService;
import com.java1234.util.HttpClientUtil;
import com.java1234.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*微信用户Controller*/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private WeixinProperties weixinProperties;

    @Autowired
    private HttpClientUtil httpClientUtil;

    @Autowired
    private IWxUserInfoService wxUserInfoService;

    /*微信用户登录*/
    @RequestMapping("/wxlogin")
    public R wxLogin(@RequestBody WxUserInfo wxUserInfo){
        //System.out.println(wxUserInfo.getCode());

        //拼接获取openID的请求地址，
        //String jscode2sessionUrl=weixinProperties.getJscode2sessionUrl()+"?appid="+weixinProperties.getAppid()+"&secret="+weixinProperties.getSecret()+"&js_code="+wxUserInfo.getCode()+"&grant_type=authorization_code";

        //获取不了配置的WeixinProperties文件数据，所以先写死

        String jscode2sessionUrl="https://api.weixin.qq.com/sns/jscode2session"+"?appid="+"wxc09eb7f50c6af81e"
                +"&secret="+"4b34842cfb35dacec34217af278926aa"+"&js_code="+wxUserInfo.getCode()+
                "&grant_type=authorization_code";
        String result =  httpClientUtil.sendHttpGet(jscode2sessionUrl);
        JSONObject jsonobject = JSON.parseObject(result);
        String openid = jsonobject.get("openid").toString();    //将json转换为字符串格式

        //如果数据库中用户不存在，插入用户信息；  若用户存在，则更新用户信息
        WxUserInfo resultWxUserInfo = wxUserInfoService.getOne(new QueryWrapper<WxUserInfo>().eq("openid", openid));
        if(resultWxUserInfo==null){
            System.out.println("不存在 插入用户");
            wxUserInfo.setOpenid(openid);
            wxUserInfo.setRegisterDate(new Date());
            wxUserInfo.setLastLoginDate(new Date());
            wxUserInfoService.save(wxUserInfo);
        }else{
            System.out.println("存在 更新用户");
            resultWxUserInfo.setNickName(wxUserInfo.getNickName());
            resultWxUserInfo.setAvatarUrl(wxUserInfo.getAvatarUrl());
            resultWxUserInfo.setWxuserImg(wxUserInfo.getWxuserImg());
            resultWxUserInfo.setLastLoginDate(new Date());
            wxUserInfoService.updateById(resultWxUserInfo);     //由于插入的记录缺少id,所以没法更新。
        }
        // 利用jwt生成token返回到前端
        String token = JwtUtils.createJWT(openid, wxUserInfo.getNickName(), SystemConstant.JWT_TTL);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("token",token);
        WxUserInfo User = wxUserInfoService.getOne(new QueryWrapper<WxUserInfo>().eq("openid", openid));
        resultMap.put("id",User.getId());
        return R.ok(resultMap);
    }
}