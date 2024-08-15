package org.yunathan.backendsmiles.service;

import org.yunathan.backendsmiles.model.FlightModel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

@Service
public class FlightService {

    private static final String API_URL_TEMPLATE = "https://api-air-flightsearch-green.smiles.com.br/v1/airlines/search?cabin=ALL&originAirportCode=%s&destinationAirportCode=%s&departureDate=%s&adults=%d";
    private static final OkHttpClient client = new OkHttpClient();

    public List<FlightModel> searchFlights(String departureAirport, String arrivalAirport, String departureTime, Integer passengers) {
        String url = String.format(API_URL_TEMPLATE, departureAirport, arrivalAirport, departureTime, passengers);

        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "Mozilla/5.0")
                .addHeader("X-Api-Key", "aJqPU7xNHl9qN3NVZnPaJ208aPo2Bh2p2ZV844tw")
                .addHeader("Origin", "https://www.smiles.com.br")
                .addHeader("Region", "BRASIL")
                .addHeader("Referer", "https://www.smiles.com.br/")
                .addHeader("Accept-Encoding", "gzip, deflate, br, zstd")
                .addHeader("Language", "pt-BR")
                .addHeader("Channel", "Web")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody;
                if ("gzip".equalsIgnoreCase(response.header("Content-Encoding"))) {
                    // Decompress the content
                    try (GZIPInputStream gzip = new GZIPInputStream(response.body().byteStream());
                         BufferedReader br = new BufferedReader(new InputStreamReader(gzip))) {
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line);
                        }
                        responseBody = sb.toString();
                    }
                } else {
                    responseBody = response.body().string();
                }

                // Print the JSON response
                System.out.println("JSON Response: " + responseBody);

                // Process the JSON response
                return ParseFlightJsonService.parseJsonResponse(responseBody);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while searching for flights");
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}