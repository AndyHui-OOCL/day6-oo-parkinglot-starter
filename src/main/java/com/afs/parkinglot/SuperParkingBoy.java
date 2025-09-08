package com.afs.parkinglot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SuperParkingBoy extends StandardParkingBoy{
    SuperParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {
        if(car == null) {
            return null;
        }

        List<ParkingLot> sortedParkingLots = new ArrayList<>(managedParkingLot);
        sortedParkingLots.sort(Comparator.comparing(ParkingLot::getAvailabilityRate).reversed());
        for (ParkingLot parkingLot : sortedParkingLots) {
            if (!parkingLot.isFull()) {
                return parkingLot.park(car);
            }
        }

        throw new ParkingLotException(ParkingLotException.NO_POSITION_ERROR);
    }
}
