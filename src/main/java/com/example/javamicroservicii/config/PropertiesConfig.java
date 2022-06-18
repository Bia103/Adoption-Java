package com.example.javamicroservicii.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties("adoption")
@Getter
@Setter
public class PropertiesConfig {
    private int price;
    private String center;
    private String animal;
}
