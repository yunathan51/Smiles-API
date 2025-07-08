package org.yunathan.backendsmiles.dto;

import lombok.Data;

@Data
public class FlightDto {

    private String departureAirport;
    private String arrivalAirport;
    private String departureTime;
    private String arrivalTime;
    private Integer passengers;
    private Double miles;
    private Double costTaxInBRL;

}