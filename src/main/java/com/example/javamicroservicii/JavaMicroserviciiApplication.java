package com.example.javamicroservicii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
//@EnableConfigServer
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class JavaMicroserviciiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaMicroserviciiApplication.class, args);
    }

}
