package com.java1234.controller;

//新方法

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java1234.entity.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

//import com.wyf.po.Result;

@Controller
public class UploadController {

    /**商品图片上传,解决小程序端临时图片问题 */
    @RequestMapping("/shop/uploadImg")
    @ResponseBody
    public R uploadImg(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = req.getFile("file");
        // 获取上传文件原始名
        String originalName = multipartFile.getOriginalFilename();
        // 设置上传文件的保存路径
        String realPath = "D:\\DasiX\\productImgs";
        String newFileName = "";
        try {
            File dir = new File(realPath);
            // 如果保存文件的地址不存在，就先创建目录
            if (!dir.exists()) {
                dir.mkdir();
            }
            // 使用UUID重新命名上传的文件名 + 文件的后缀名
            newFileName = "shop_" + UUID.randomUUID() + originalName.substring(originalName.lastIndexOf("."));
            File file = new File(realPath, newFileName);
            // 使用multipartFile接口的方法上传文件到指定位置
            multipartFile.transferTo(file);



            return  R.ok(newFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return  R.ok("操作异常");
        }
    }


    /**用户头像图片上传,解决小程序端临时图片问题 */
    @RequestMapping("/shop/avatarUrl")
    @ResponseBody
    public String userImg(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = req.getFile("avatarImg");
        // 获取上传文件原始名
        //String originalName = multipartFile.getOriginalFilename();
        // 设置上传文件的保存路径
        String realPath = "D:\\DasiX\\\\wxuserImg";
        String newFileName = "";
        try {
            File dir = new File(realPath);
            // 如果保存文件的地址不存在，就先创建目录
            if (!dir.exists()) {
                dir.mkdir();
            }
            // 使用UUID重新命名上传的文件名 + 文件的后缀名
            newFileName = "shop_" + UUID.randomUUID()+".jpeg";
            File file = new File(realPath, newFileName);
            // 使用multipartFile接口的方法上传文件到指定位置
            multipartFile.transferTo(file);

            System.out.println(newFileName);

            return  newFileName;
        } catch (Exception e) {
            e.printStackTrace();
            return  ("操作异常");
        }
    }
}





