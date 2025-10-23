package com.example.crypto_trade_project.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "trades")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String cryptoName;

    @Enumerated(EnumType.STRING)
    private TradeType tradeType;

    private BigDecimal quantity;

    private BigDecimal price;

    private LocalDateTime tradeDate;

    public enum TradeType {
        BUY, SELL
    }

    public Trade(User user, String cryptoName, TradeType tradeType, BigDecimal quantity, BigDecimal price) {
        this.user = user;
        this.cryptoName = cryptoName;
        this.tradeType = tradeType;
        this.quantity = quantity;
        this.price = price;
        this.tradeDate = LocalDateTime.now();
    }
}
