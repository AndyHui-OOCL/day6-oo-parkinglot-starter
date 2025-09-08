package com.afs.parkinglot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends StandardParkingBoy {
    public SmartParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {
        if (car == null) {
            return null;
        }

        List<ParkingLot> sortedParkingLots = new ArrayList<>(managedParkingLot);
        sortedParkingLots.sort(Comparator.comparing(ParkingLot::getAvailableSpace).reversed());
        for (ParkingLot parkingLot : sortedParkingLots) {
            if (!parkingLot.isFull()) {
                return parkingLot.park(car);
            }
        }
        
        throw new ParkingLotException(ParkingLotException.NO_POSITION_ERROR);
    }
}
