package com.itmuch;

import com.itmuch.configuration.NacosWeightedRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ContentCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentCenterApplication.class, args);
    }

    // 在Spring容器中，创建一个对象，类型是RestTemplate，名称/id是restTemplate
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // 这种方式是全局配置的，即在content调取其它服务是，都是使用随机算法
//    @Bean
//    public IRule randomRule() {
//        return  new RandomRule();
//    }

//    @Bean
    public IRule nacosWeightedRule() {
        return new NacosWeightedRule();
    }
}