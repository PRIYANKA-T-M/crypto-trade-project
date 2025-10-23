package com.example.crypto_trade_project.services;
import com.google.gson.*;
import org.springframework.stereotype.Service;
import java.net.http.*;
import java.net.URI;
import java.util.*;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;


@Service
public class CryptoService {

    public  Map<String, Double> getRealTimePrices(List<String> symbols) {
        Map<String, Double> priceMap = new HashMap<>();
        try {
            String ids = String.join(",", symbols);
            String url = "https://api.coingecko.com/api/v3/simple/price?ids=" + ids + "&vs_currencies=usd";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            for (String sym : symbols) {
                priceMap.put(sym, json.get(sym).getAsJsonObject().get("usd").getAsDouble());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return priceMap;
    }
}

