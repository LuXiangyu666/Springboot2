package com.java1234.config;

//import com.java1234.interceptor.SysInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** web项目配置类*/
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE","OPTIONS")
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //虚拟路径映射
        registry.addResourceHandler("/image/swiper/**").addResourceLocations("file:D:\\DasiX\\swiperImgs\\");
        registry.addResourceHandler("/image/bigType/**").addResourceLocations("file:D:\\DasiX\\bigTypeImgs\\");
        registry.addResourceHandler("/image/product/**").addResourceLocations("file:D:\\DasiX\\productImgs\\");
        registry.addResourceHandler("/image/productSwiperImgs/**").addResourceLocations("file:D:\\DasiX\\\\productImgs\\");
        // registry.addResourceHandler("/image/productIntroImgs/**").addResourceLocations("file:D:\\DasiX\\productIntroImgs\\");
        // registry.addResourceHandler("/image/productParaImgs/**").addResourceLocations("file:D:\\DasiX\\productParaImgs\\");

    }

//    @Bean
//    public SysInterceptor sysInterceptor(){
//        return new SysInterceptor();
//    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        String[] patterns=new String[]{"/adminLogin","/product/**","/bigType/**","/user/wxlogin","/weixinpay/**"};
//        registry.addInterceptor(sysInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns(patterns);
//    }

    public static void main(String[] args) {
        for(int i=0;i<10000;i++){
            System.out.println("测试="+i);
        }
    }
}
