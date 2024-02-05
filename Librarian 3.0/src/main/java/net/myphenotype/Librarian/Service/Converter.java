package net.myphenotype.Librarian.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class Converter {

    public static double amountInRupee(String CURRENCY_CONVERTER_ENDPOINT, String foreignCurrencyCode, float amountInForeignCurrency) throws IOException {

        // Setting URL
        String url_str = CURRENCY_CONVERTER_ENDPOINT +foreignCurrencyCode;
        System.out.println("The URL is : " + url_str);
        // Making Request
        try {
            URL url = new URL(url_str);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            // Convert to JSON
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();

            // Accessing object
            String req_result = jsonobj.get("result").getAsString();
            JsonObject rateArray = (JsonObject)jsonobj.get("rates");

            return rateArray.get("INR").getAsDouble()*amountInForeignCurrency;
        } catch (Exception e){
            return 0;
        }
    }
}
