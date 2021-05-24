package com.kcl.config;

import com.kcl.Interceptors.JwtInterceptors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 项目名： jwt
 * 包名:    com.kcl.config
 * 文件名   InterceptorConfig
 * 创建者
 * 创建时间: 2021/5/24 2:49 PM
 * 描述  ${TODO}
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptors())
                .addPathPatterns("/test") //拦截
                .excludePathPatterns("/user/login"); //不拦截
    }
}

