package com.kcxuao.serverUI.config;

import com.kcxuao.serverUI.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    // 初始化拦截器放入到ioc容器中
    public LoginInterceptor getLoginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                // 1： 拦截器注册
                .addInterceptor(getLoginInterceptor())
                // 2: 给拦截器配置并且定义规则
                .addPathPatterns("/**");
    }
}