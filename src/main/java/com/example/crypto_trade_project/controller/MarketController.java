package com.example.crypto_trade_project.controller;

import com.example.crypto_trade_project.model.CryptoPrice;
import com.example.crypto_trade_project.service.CryptoService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

@Component
public class MarketController {

    private final CryptoService cryptoService;

    @FXML
    private TableView<CryptoPrice> cryptoTable;

    @FXML
    private TableColumn<CryptoPrice, String> nameColumn;

    @FXML
    private TableColumn<CryptoPrice, BigDecimal> priceColumn;

    @FXML
    private TableColumn<CryptoPrice, LocalDateTime> timestampColumn;

    private final ObservableList<CryptoPrice> cryptoPrices = FXCollections.observableArrayList();

    public MarketController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("cryptoName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        timestampColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        cryptoTable.setItems(cryptoPrices);

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> refreshMarketData());
            }
        }, 0, 10000);
    }

    private void refreshMarketData() {
        List<String> cryptoIds = Arrays.asList("bitcoin", "ethereum", "dogecoin");
        List<CryptoPrice> prices = cryptoService.getRealTimePrices(cryptoIds).entrySet().stream()
                .map(entry -> new CryptoPrice(entry.getKey(), BigDecimal.valueOf(entry.getValue())))
                .collect(Collectors.toList());
        cryptoPrices.setAll(prices);
    }
}
