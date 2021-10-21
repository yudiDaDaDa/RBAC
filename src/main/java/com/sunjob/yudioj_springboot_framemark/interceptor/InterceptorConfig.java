package com.sunjob.yudioj_springboot_framemark.interceptor;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Bean
    HandlerInterceptor getInterceptor(){
        return new Interceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/userLogin")
                .excludePathPatterns("/**/**.html")
                .excludePathPatterns("/**.html")
                .excludePathPatterns("/layui/**")
                .excludePathPatterns("/jquery/**");
        //不拦截用户登录
        super.addInterceptors(registry);
    }

}
