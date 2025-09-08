package com.afs.parkinglot;

import java.util.List;

public class StandardParkingBoy {
    protected List<ParkingLot> managedParkingLot;

    StandardParkingBoy() {
        managedParkingLot = List.of(new ParkingLot());
    }

    StandardParkingBoy(ParkingLot... parkingLots) {
        managedParkingLot = List.of(parkingLots);
    }

    public ParkingTicket park(Car car){
        if (car == null) {
            return null;
        }

        for(ParkingLot currentLot: managedParkingLot) {
            if(!currentLot.isFull()) {
                return currentLot.park(car);
            }
        }
        throw new ParkingLotException(ParkingLotException.NO_POSITION_ERROR);
    }

    public Car fetch(ParkingTicket ticket) {
        for (ParkingLot parkingLot : managedParkingLot) {
            try {
                return parkingLot.fetch(ticket);
            } catch (ParkingLotException ignored) {

            }
        }
        throw new ParkingLotException(ParkingLotException.UNRECOGNIZED_TICKET_ERROR);
    }
}
