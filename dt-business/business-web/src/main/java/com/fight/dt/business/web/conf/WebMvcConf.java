package com.fight.dt.business.web.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by tpx on 2017/7/12.
 */
@Configuration
//@EnableWebMvc //无需使用该注解，否则会覆盖掉SpringBoot的默认配置值
public class WebMvcConf extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/talk").setViewName("talk");
        registry.addViewController("/excel").setViewName("excel");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**");
        registry.addMapping("/login");
        registry.addMapping("/talk");
        registry.addMapping("/reg");
        registry.addMapping("/excel");
    }
}
