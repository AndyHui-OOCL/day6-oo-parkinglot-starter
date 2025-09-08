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
        if(isParkingLotFull()) {
             throw new Error("No available position");
        }
        if (car == null || parkingLot.containsValue(car)) {
            return null;
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingLot.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket ticket) {
        if(parkingLot.get(ticket) != null) {
            return parkingLot.remove(ticket);
        }
        throw new Error("Unrecognized parking ticket");
    }

    boolean isParkingLotFull() {
        return parkingLot.size() >= capacity;
    }
}
