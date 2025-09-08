package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParkingLotTest {
    @Test
    public void should_return_parking_ticker_when_parkCar_given_valid_car_parkinglot_not_full(){
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car();

        ParkingTicket ticket = parkingLot.park(car);

        assertEquals(new ParkingTicket(car), ticket);
    }

    @Test
    public void should_return_no_parking_ticker_when_parkCar_given_valid_car_and_parking_lot_full(){
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car();
        Car car2 = new Car();

        ParkingTicket ticket1 = parkingLot.park(car1);
        ParkingTicket ticket2 = parkingLot.park(car2);

        assertNull(ticket2);
    }

    @Test
    public void should_return_no_parking_ticker_when_parkCar_given_invalid_car_and_parking_lot_not_full(){
        ParkingLot parkingLot = new ParkingLot(1);

        ParkingTicket ticket = parkingLot.park(null);

        assertNull(ticket);
    }
}
