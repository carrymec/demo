package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @CLASSNAME MyWebAppConfigurer
 * @DESCRIPTION 图片上传资源配置
 * @AUTHOR chen
 * @DATE 2018-12-04 14:36
 **/
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("file:/files/");
//        registry.addResourceHandler("/image/**").addResourceLocations("file:d:/profile/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins( "*" ).allowCredentials( true )
                        .allowedMethods( ORIGINS ).maxAge( 3600 );
            }
        };
    }
    // 跨域请求
    static final String ORIGINS[] = new String[]{"GET", "POST", "PUT", "DELETE"};


}
