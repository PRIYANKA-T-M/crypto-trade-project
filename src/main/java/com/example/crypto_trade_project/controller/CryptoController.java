package com.example.crypto_trade_project.controller;
import com.example.crypto_trade_project.services.CryptoService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/crypto")
public class CryptoController {

    private final CryptoService service;

    public CryptoController(CryptoService service) {
        this.service = service;
    }

    @GetMapping("/prices")
    public Map<String, Double> getPrices(@RequestParam List<String> ids) {
        return service.getRealTimePrices(ids);
    }
}
