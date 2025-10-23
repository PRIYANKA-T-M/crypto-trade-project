package com.example.crypto_trade_project.config;

import com.example.crypto_trade_project.model.CryptoPrice;
import com.example.crypto_trade_project.model.User;
import com.example.crypto_trade_project.repository.CryptoPriceRepository;
import com.example.crypto_trade_project.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("!test")
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CryptoPriceRepository cryptoPriceRepository;

    public DataLoader(UserRepository userRepository, CryptoPriceRepository cryptoPriceRepository) {
        this.userRepository = userRepository;
        this.cryptoPriceRepository = cryptoPriceRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
        loadCryptoPriceData();
    }

    private void loadUserData() {
        if (userRepository.count() == 0) {
            userRepository.save(new User("testuser", "test@example.com", "password", new BigDecimal("10000")));
        }
    }

    private void loadCryptoPriceData() {
        if (cryptoPriceRepository.count() == 0) {
            cryptoPriceRepository.save(new CryptoPrice("bitcoin", new BigDecimal("60000")));
            cryptoPriceRepository.save(new CryptoPrice("ethereum", new BigDecimal("3000")));
            cryptoPriceRepository.save(new CryptoPrice("dogecoin", new BigDecimal("0.5")));
        }
    }
}
