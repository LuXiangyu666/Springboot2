package com.java1234.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.java1234.entity.*;
import com.java1234.service.IComplaintImageService;
import com.java1234.service.IComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**留言Controller控制器*/
@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    private IComplaintService complaintService;

    @Autowired
    private  IComplaintImageService complaintImageService;


    //创建新留言，返回留言id
    @RequestMapping("/createComplaint")
    @Transactional
    public R create(@RequestBody Complaint complaint){
        //添加留言到数据库
        complaint.setPublishTime(new Date());
        complaintService.save(complaint);

        ComplaintImage complaintImage[] = complaint.getComplaintImg();

        for(int i=0;i<complaintImage.length;i++){
            ComplaintImage compImgList =complaintImage[i];
            compImgList.setComplaintId(complaint.getId());
            complaintImageService.save(compImgList);
        }
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("投诉发布成功，投诉信息的id是",complaint.getId());
        return R.ok(resultMap);

    }


}
