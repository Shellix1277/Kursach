package com.example.pr8;

public class Flight {
    private String destination;
    private String airline;
    private String departureTime;

    public Flight(String destination, String airline, String departureTime) {
        this.destination = destination;
        this.airline = airline;
        this.departureTime = departureTime;
    }

    public String getDestination() {
        return destination;
    }

    public String getAirline() {
        return airline;
    }

    public String getDepartureTime() {
        return departureTime;
    }
}
