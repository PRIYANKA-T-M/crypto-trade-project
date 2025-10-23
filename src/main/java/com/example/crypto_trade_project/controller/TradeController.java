package com.example.crypto_trade_project.controller;

import com.example.crypto_trade_project.model.Trade;
import com.example.crypto_trade_project.model.User;
import com.example.crypto_trade_project.service.CryptoService;
import com.example.crypto_trade_project.service.TradeService;
import com.example.crypto_trade_project.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
public class TradeController {

    private final TradeService tradeService;
    private final UserService userService;
    private final CryptoService cryptoService;

    @FXML
    private ComboBox<String> cryptoComboBox;

    @FXML
    private ComboBox<Trade.TradeType> tradeTypeComboBox;

    @FXML
    private TextField quantityField;

    @FXML
    private Button submitButton;

    public TradeController(TradeService tradeService, UserService userService, CryptoService cryptoService) {
        this.tradeService = tradeService;
        this.userService = userService;
        this.cryptoService = cryptoService;
    }

    @FXML
    public void initialize() {
        cryptoComboBox.getItems().addAll("bitcoin", "ethereum", "dogecoin");
        tradeTypeComboBox.getItems().addAll(Trade.TradeType.values());

        submitButton.setOnAction(event -> submitTrade());
    }

    private void submitTrade() {
        // For simplicity, we'll use the first user
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            System.err.println("No users found in the database.");
            return;
        }
        User user = users.get(0);

        String cryptoName = cryptoComboBox.getValue();
        Map<String, Double> prices = cryptoService.getRealTimePrices(List.of(cryptoName));
        Double price = prices.get(cryptoName);

        if (price == null) {
            System.err.println("Could not get price for " + cryptoName);
            return;
        }

        Trade trade = new Trade(
                user,
                cryptoName,
                tradeTypeComboBox.getValue(),
                new BigDecimal(quantityField.getText()),
                BigDecimal.valueOf(price)
        );
        tradeService.createTrade(trade);
    }
}
