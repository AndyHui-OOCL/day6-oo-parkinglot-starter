package com.afs.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int capacity;
    private Map<ParkingTicket, Car> parkingLot = new HashMap<>();

    ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingTicket park(Car car){
        if(parkingLot.size() >= capacity) return null;
        ParkingTicket parkingTicket = new ParkingTicket(car);
        parkingLot.put(parkingTicket, car);
        return parkingTicket;
    }
}
