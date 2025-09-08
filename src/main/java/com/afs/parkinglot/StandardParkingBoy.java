package com.afs.parkinglot;

import java.util.List;

public class StandardParkingBoy {
    private List<ParkingLot> managedParkingLot;
    private int nextParkingIndex = 0;

    StandardParkingBoy() {
        managedParkingLot = List.of(new ParkingLot());
    }

    StandardParkingBoy(ParkingLot... parkingLots) {
        managedParkingLot = List.of(parkingLots);
    }

    public ParkingTicket park(Car car){
        int startIndex = nextParkingIndex;
        int currentIndex = startIndex;
        do {
            ParkingLot currentLot = managedParkingLot.get(currentIndex);
            if(!currentLot.isFull()) {
                ParkingTicket ticket = currentLot.park(car);
                nextParkingIndex = (currentIndex + 1) % managedParkingLot.size();
                return ticket;
            }
            currentIndex = (currentIndex + 1) % managedParkingLot.size();
        } while(currentIndex != startIndex);
        throw new Error("No available position");
    }

    public Car fetch(ParkingTicket ticket) {
        for(int i = 0; i < managedParkingLot.size(); i++) {
            try {
                Car parkedCar = managedParkingLot.get(i).fetch(ticket);
                nextParkingIndex = i;
                return parkedCar;
            } catch (Error ignored) {}
        }
        throw new Error("Unrecognized parking ticket");
    }
}
