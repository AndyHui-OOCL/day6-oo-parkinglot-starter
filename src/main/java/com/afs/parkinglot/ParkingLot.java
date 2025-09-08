package com.afs.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int capacity = 10;
    private Map<ParkingTicket, Car> parkingLot = new HashMap<>();

    ParkingLot(){

    }

    ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingTicket park(Car car){
        if((isFull())) {
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

    boolean isFull() {
        return parkingLot.size() >= capacity;
    }
    
    public int getAvailableSpace() {
        return capacity - parkingLot.size();
    }
}
