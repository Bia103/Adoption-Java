package com.example.javamicroservicii.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Discount {
    private String version;
    private String center;
    private int price;
}