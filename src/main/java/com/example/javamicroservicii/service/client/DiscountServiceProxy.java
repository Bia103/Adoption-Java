package com.example.javamicroservicii.service.client;

import com.example.javamicroservicii.model.Discount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "discount")
public interface DiscountServiceProxy {
    @GetMapping(value = "/discount", consumes = "application/json")
    Discount findDiscount();
}
