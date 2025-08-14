package com.example.zoo.wrapper;

import com.example.zoo.ass1.manage.Manage;
import com.example.zoo.wrapper.interfaces.ZooService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// ============   Do‑Not‑Touch ====================
@SpringBootApplication
public class ZooApplication {
    public static ZooService manage;

    public static void main(String[] args) {
        SpringApplication.run(ZooApplication.class, args);
        manage = new Manage();
        manage.init();
    }
}
