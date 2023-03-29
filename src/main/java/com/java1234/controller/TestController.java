package com.java1234.controller;

import com.java1234.entity.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**@author java1234_小锋*/
@RestController
@RequestMapping("/java1234")
public class TestController {

    /**测试 */
    @GetMapping("/test")
    public R test(){
        return R.ok("流弊java1234");
    }
}
