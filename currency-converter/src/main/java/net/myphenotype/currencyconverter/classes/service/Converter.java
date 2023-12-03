package net.myphenotype.currencyconverter.classes.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class Converter {

    public static float amountInRupee(String foreignCurrencyCode, float amountInForeignCurrency) throws IOException {

        // Setting URL
        String url_str = "https://open.er-api.com/v6/latest/"+foreignCurrencyCode;

        // Making Request
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        // Convert to JSON
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();

        // Accessing object
        String req_result = jsonobj.get("result").getAsString();
        System.out.println(req_result);
        JsonObject rateArray = (JsonObject)jsonobj.get("rates");
        System.out.println(rateArray.get("INR"));
        System.out.println((jsonobj.get("rates")).getClass().getSimpleName());

        return rateArray.get("INR").getAsFloat()*amountInForeignCurrency;
    }

    public static void printQuote() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://yahoo-finance15.p.rapidapi.com/api/v1/markets/stock/quotes?ticker=CCL.NS"))
                .header("X-RapidAPI-Key", "keyGoesHere")
                .header("X-RapidAPI-Host", "yahoo-finance15.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
