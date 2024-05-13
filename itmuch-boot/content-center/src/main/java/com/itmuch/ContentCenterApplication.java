package com.itmuch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ContentCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentCenterApplication.class, args);
    }

    // 在Spring容器中，创建一个对象，类型是RestTemplate，名称/id是restTemplate
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}