package org.yunathan.backendsmiles.model;

import lombok.Data;

@Data
public class FlightModel {

    private String departureAirport;
    private String arrivalAirport;
    private String departureTime;
    private String arrivalTime;
    private Integer passengers;
    private Double miles;
    private Double costTax;

}