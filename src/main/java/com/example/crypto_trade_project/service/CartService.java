package com.example.crypto_trade_project.service;

import com.example.crypto_trade_project.model.Cart;
import com.example.crypto_trade_project.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Optional<Cart> getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public void addToCart(Long userId, String cryptocurrency, Double amount) {
        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.getItems().merge(cryptocurrency, amount, Double::sum);
            cartRepository.save(cart);
        }
    }
}
