package org.yunathan.backendsmiles.controller;

import org.yunathan.backendsmiles.dto.FlightDto;
import org.yunathan.backendsmiles.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/search")
    public List<FlightDto> searchFlights(
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport,
            @RequestParam String departureTime,
            @RequestParam Integer passengers) {

        return flightService.searchFlights(departureAirport, arrivalAirport, departureTime, passengers);
    }

}   