package com.pomoravskivrbaci.cinemareservations.config;

import com.pomoravskivrbaci.cinemareservations.interceptor.InstAdminInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InstAdminInterceptor());
        super.addInterceptors(registry);
    }
}
