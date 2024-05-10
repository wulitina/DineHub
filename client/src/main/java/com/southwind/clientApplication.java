package com.southwind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@ServletComponentScan注解使得拦截器生效
@SpringBootApplication
@EnableFeignClients
@ServletComponentScan
public class clientApplication {
    public static void main(String[] args) {
        SpringApplication.run(clientApplication.class, args);
    }
}
