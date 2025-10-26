package com.example.crypto_trade_project.controller;

import com.example.crypto_trade_project.model.Cart;
import com.example.crypto_trade_project.model.Portfolio;
import com.example.crypto_trade_project.service.CartService;
import com.example.crypto_trade_project.service.CryptoService;
import com.example.crypto_trade_project.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class WebController {

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private CryptoService cryptoService;

    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String cart(Model model) {
        Optional<Cart> cart = cartService.getCartByUserId(1L); // Hardcoded user ID
        if (cart.isPresent()) {
            model.addAttribute("cart", cart.get());
        }
        return "cart";
    }

    @GetMapping("/buy")
    public String buy(Model model) {
        List<String> cryptos = List.of("bitcoin", "ethereum", "ripple");
        Map<String, Double> prices = cryptoService.getRealTimePrices(cryptos);
        model.addAttribute("prices", prices);
        return "buy";
    }

    @PostMapping("/buy")
    public String buy(@RequestParam String cryptocurrency, @RequestParam Double amount) {
        portfolioService.buyCrypto(1L, cryptocurrency, amount); // Hardcoded user ID
        return "redirect:/portfolio";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam String cryptocurrency, @RequestParam Double amount) {
        cartService.addToCart(1L, cryptocurrency, amount); // Hardcoded user ID
        return "redirect:/buy";
    }

    @GetMapping("/sell")
    public String sell(Model model) {
        Optional<Portfolio> portfolio = portfolioService.getPortfolioByUserId(1L); // Hardcoded user ID
        if (portfolio.isPresent()) {
            model.addAttribute("portfolio", portfolio.get());
        }
        return "sell";
    }

    @PostMapping("/sell")
    public String sell(@RequestParam String cryptocurrency, @RequestParam Double amount) {
        portfolioService.sellCrypto(1L, cryptocurrency, amount); // Hardcoded user ID
        return "redirect:/portfolio";
    }

    @GetMapping("/trends")
    public String trends() {
        return "trends";
    }

    @GetMapping("/portfolio")
    public String portfolio(Model model) {
        Optional<Portfolio> portfolio = portfolioService.getPortfolioByUserId(1L); // Hardcoded user ID
        if (portfolio.isPresent()) {
            model.addAttribute("portfolio", portfolio.get());
        }
        return "portfolio";
    }
}
