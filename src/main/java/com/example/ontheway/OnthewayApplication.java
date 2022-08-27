package com.example.ontheway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class OnthewayApplication {

    @PostConstruct
    public void init() {
        //解决netty启动冲突的问题
        // see Netty4Utile.setAvailableProcessors()
        System.setProperty("es.set.netty.runtime.available.processors","false");
    }

    public static void main(String[] args) {
        SpringApplication.run(OnthewayApplication.class, args);
    }

}
