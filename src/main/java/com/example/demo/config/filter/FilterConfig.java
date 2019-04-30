package com.example.demo.config.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 过滤器
 */
@Configuration
public class FilterConfig extends WebMvcConfigurerAdapter {




    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }
}
