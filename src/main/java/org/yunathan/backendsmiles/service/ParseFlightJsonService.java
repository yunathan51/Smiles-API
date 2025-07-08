package org.yunathan.backendsmiles.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;
import org.yunathan.backendsmiles.dto.FlightDto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParseFlightJsonService {

    public static List<FlightDto> parseJsonResponse (String responseBody) {
        JsonObject json = new Gson().fromJson(responseBody, JsonObject.class);
        JsonArray flightSegmentList = json.getAsJsonArray("requestedFlightSegmentList");


        List<FlightDto> flights = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        for (JsonElement segmentElement : flightSegmentList) {
            JsonObject segment = segmentElement.getAsJsonObject();
            JsonArray flightList = segment.getAsJsonArray("flightList");

            for (JsonElement flightElement : flightList) {
                JsonObject flight = flightElement.getAsJsonObject();
                JsonObject departure = flight.getAsJsonObject("departure");
                JsonObject arrival = flight.getAsJsonObject("arrival");
                JsonArray fareList = flight.getAsJsonArray("fareList"); // Numerous types of fares for the same flight, we want the second one in this case
                JsonObject passengers = json.getAsJsonObject("passenger");

                String departureAirport = departure.getAsJsonObject("airport").get("code").getAsString();
                String arrivalAirport = arrival.getAsJsonObject("airport").get("code").getAsString();
                String departureTime = LocalDateTime.parse(departure.get("date").getAsString()).format(formatter);
                String arrivalTime = LocalDateTime.parse(arrival.get("date").getAsString()).format(formatter);
                Integer passengerCount = passengers.get("adults").getAsInt();
                if (!fareList.isEmpty()) {
                    JsonObject secondFare = fareList.get(1).getAsJsonObject();
                    Double miles = secondFare.get("miles").getAsDouble();
                    Double costTax = secondFare.getAsJsonObject("g3").get("costTax").getAsDouble();

                    FlightDto flightModel = new FlightDto();
                    flightModel.setDepartureAirport(departureAirport);
                    flightModel.setArrivalAirport(arrivalAirport);
                    flightModel.setDepartureTime(departureTime);
                    flightModel.setArrivalTime(arrivalTime);
                    flightModel.setPassengers(passengerCount);
                    flightModel.setMiles(miles);
                    flightModel.setCostTaxInBRL(costTax);
                    flights.add(flightModel);
                }
            }
        }

        return flights;
    }
}