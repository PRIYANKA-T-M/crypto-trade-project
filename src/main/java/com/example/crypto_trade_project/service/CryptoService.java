package com.example.crypto_trade_project.service;

import com.example.crypto_trade_project.model.CryptoPrice;
import com.example.crypto_trade_project.repository.CryptoPriceRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class CryptoService {

    private final CryptoPriceRepository cryptoPriceRepository;

    public CryptoService(CryptoPriceRepository cryptoPriceRepository) {
        this.cryptoPriceRepository = cryptoPriceRepository;
    }

    public Map<String, Double> getRealTimePrices(List<String> symbols) {
        try {
            String ids = String.join(",", symbols);
            String url = "https://api.coingecko.com/api/v3/simple/price?ids=" + ids + "&vs_currencies=usd";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            return symbols.stream()
                    .collect(Collectors.toMap(
                            symbol -> symbol,
                            symbol -> {
                                double price = json.get(symbol).getAsJsonObject().get("usd").getAsDouble();
                                cryptoPriceRepository.save(new CryptoPrice(symbol, BigDecimal.valueOf(price)));
                                return price;
                            }
                    ));
        } catch (Exception e) {
            e.printStackTrace();
            return Map.of();
        }
    }
}
