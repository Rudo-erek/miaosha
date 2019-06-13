package com.ryan.miaosha;

import com.ryan.miaosha.controller.DemoController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoController.class, args);
    }
}
