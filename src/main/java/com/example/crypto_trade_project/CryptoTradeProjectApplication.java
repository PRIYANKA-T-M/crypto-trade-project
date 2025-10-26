package com.example.crypto_trade_project;

import com.example.crypto_trade_project.model.Cart;
import com.example.crypto_trade_project.model.Portfolio;
import com.example.crypto_trade_project.model.User;
import com.example.crypto_trade_project.repository.CartRepository;
import com.example.crypto_trade_project.repository.PortfolioRepository;
import com.example.crypto_trade_project.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CryptoTradeProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptoTradeProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, PortfolioRepository portfolioRepository, CartRepository cartRepository) {
        return (args) -> {
            // Create a new user
            User user = new User();
            user.setUsername("testuser");
            user.setPassword("password");
            userRepository.save(user);

            // Create a new portfolio for the user
            Portfolio portfolio = new Portfolio();
            portfolio.setUser(user);
            portfolioRepository.save(portfolio);

            // Create a new cart for the user
            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        };
    }
}
