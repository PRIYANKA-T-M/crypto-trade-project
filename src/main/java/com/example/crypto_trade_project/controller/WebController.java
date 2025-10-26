package com.example.crypto_trade_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WebController {

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping("/buy")
    public String buy() {
        return "buy";
    }

    @GetMapping("/sell")
    public String sell() {
        return "sell";
    }

    @GetMapping("/trends")
    public String trends() {
        return "trends";
    }
}
