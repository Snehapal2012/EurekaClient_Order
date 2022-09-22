package com.example.order;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@Slf4j
@EnableEurekaClient
public class OrderApplication {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
        System.out.println("......................Welcome to Order Application........................!");
        System.out.println("----------------------------------------------------------------------------");
        log.info("!!!.............Hello Logger...........!!!");
    }

}
