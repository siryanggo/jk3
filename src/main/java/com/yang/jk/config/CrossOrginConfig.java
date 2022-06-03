package com.yang.jk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @auther yhjStart
 * @create 2022-03-27 20:58
 * CROSS CONFIGRATION
 */
@Configuration
public class CrossOrginConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //addMapping all cros
        registry.addMapping("/**")
//                all header info
                .allowedHeaders("*")
                .allowCredentials(true)
                .allowedOrigins("/**")
                .allowedMethods("GET","POST");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("file:/ASM/code/jk/");
    }
}
