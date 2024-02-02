package com.example.jpanext.shop.controller;


import com.example.jpanext.shop.service.ParentService;
import com.example.jpanext.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shop")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;
    private final ParentService parentService;

    @GetMapping("create-order")
    public String createOrder() {
        shopService.createOrder();
        return "done";
    }

    @GetMapping("propagation")
    public void propagation() {
        parentService.none();
    }
}
