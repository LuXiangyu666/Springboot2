package com.java1234.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;


@Configuration
public class TomcatConfig {
//    @Value("${spring.server.MaxFileSize}")
//    private String maxFileSize;
//    @Value("${spring.server.MaxRequestSize}")
//    private String maxRequestSize;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize(DataSize.parse("10MB"));
        /// 总上传数据大小
        factory.setMaxRequestSize(DataSize.parse("50MB"));
        return factory.createMultipartConfig();
    }
}


