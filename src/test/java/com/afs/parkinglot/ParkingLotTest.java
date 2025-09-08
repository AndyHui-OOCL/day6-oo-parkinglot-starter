package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingLotTest {
    @Test
    public void should_return_parking_ticker_when_parkCar_given_valid_car_parkinglot_not_full(){
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car();

        ParkingTicket ticket = parkingLot.park(car);

        assertEquals(new ParkingTicket(car), ticket);
    }
}
