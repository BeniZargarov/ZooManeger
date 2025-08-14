package com.example.zoo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

// ============   Do‑Not‑Touch ====================
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/{spring:\\w+}")
                .setViewName("forward:/index.html");
        registry.addViewController("/**/{spring:\\w+}")
                .setViewName("forward:/index.html");
        registry.addViewController("/{spring:\\w+}/**{spring:?!(\\.js|\\.css|\\.png|\\.jpg|\\.svg|\\.ico)$}")
                .setViewName("forward:/index.html");
    }
}

