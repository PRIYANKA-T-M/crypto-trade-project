package com.example.crypto_trade_project.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "crypto_prices")
public class CryptoPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cryptoName;

    private BigDecimal price;

    private LocalDateTime timestamp;

    public CryptoPrice(String cryptoName, BigDecimal price) {
        this.cryptoName = cryptoName;
        this.price = price;
        this.timestamp = LocalDateTime.now();
    }
}
