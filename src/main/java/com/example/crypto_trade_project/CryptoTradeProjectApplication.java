package com.example.crypto_trade_project;

import com.example.crypto_trade_project.view.JavaFxApplication;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CryptoTradeProjectApplication {

    private static String[] savedArgs;

    public static void main(String[] args) {
        savedArgs = args;
        Application.launch(JavaFxApplication.class, args);
    }
}
