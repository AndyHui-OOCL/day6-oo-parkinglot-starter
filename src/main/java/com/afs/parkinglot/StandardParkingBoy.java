package com.afs.parkinglot;

public class StandardParkingBoy {
    private ParkingLot managedParkingLot;

    StandardParkingBoy() {
        managedParkingLot = new ParkingLot();
    }

    public ParkingTicket park(Car car){
        return managedParkingLot.park(car);
    }
}
