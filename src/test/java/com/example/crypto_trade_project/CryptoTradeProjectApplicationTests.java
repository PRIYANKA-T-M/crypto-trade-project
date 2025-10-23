package com.example.crypto_trade_project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CryptoTradeProjectApplicationTests {

    @Test
    void contextLoads() {
        // This test will verify that the application context loads successfully
    }
}
