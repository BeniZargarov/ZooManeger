package com.example.zoo.wrapper;

import com.example.zoo.ass3.exceptions.GeneralException;
import com.example.zoo.ass3.manage.Manage;
import com.example.zoo.wrapper.interfaces.IZoo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZooApplication {
    public static IZoo manage;

    public static void main(String[] args) throws GeneralException {
        SpringApplication.run(ZooApplication.class, args);
        manage = new Manage();
        manage.init();
    }
}
