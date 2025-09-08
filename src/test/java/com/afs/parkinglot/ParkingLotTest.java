package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    public void should_return_parking_ticker_when_parkCar_given_valid_car_parkinglot_not_full(){
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car();

        ParkingTicket ticket = parkingLot.park(car);

        assertNotNull(ticket);
    }

    @Test
    public void should_return_error_when_parkCar_given_valid_car_and_parking_lot_full(){
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car();
        Car car2 = new Car();

        ParkingTicket ticket1 = parkingLot.park(car1);
        try {
            ParkingTicket ticket2 = parkingLot.park(car2);
        } catch (Error error) {
            assertEquals("No available position", error.getMessage());
        }
    }

    @Test
    public void should_return_no_parking_ticker_when_parkCar_given_invalid_car_and_parking_lot_not_full(){
        ParkingLot parkingLot = new ParkingLot(10);

        ParkingTicket ticket = parkingLot.park(null);

        assertNull(ticket);
    }

    @Test
    public void should_return_no_parking_ticker_when_parkCar_given_parked_car(){
        ParkingLot parkingLot = new ParkingLot(10);
        Car car1 = new Car();

        ParkingTicket ticket1 = parkingLot.park(car1);
        ParkingTicket ticket2 = parkingLot.park(car1);

        assertNull(ticket2);
    }

    @Test
    public void should_return_car_when_fetchCar_given_a_valid_parking_ticket() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car1 = new Car();
        ParkingTicket ticket1 = parkingLot.park(car1);

        Car fetchedCar = parkingLot.fetch(ticket1);

        assertEquals(car1, fetchedCar);
    }

    @Test
    public void should_return_error_message_when_fetchCar_given_a_nonvalid_parking_ticket() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car1 = new Car();
        ParkingTicket ticket1 = parkingLot.park(car1);

        try {
            Car fetchedCar = parkingLot.fetch(new ParkingTicket());
        } catch (Error error) {
            assertEquals("Unrecognized parking ticket", error.getMessage());
        }
    }

    @Test
    public void should_return_error_when_fetchCar_given_null_ticker() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car1 = new Car();
        ParkingTicket ticket1 = parkingLot.park(car1);

        try {
            Car fetchedCar = parkingLot.fetch(null);
        } catch (Error error) {
            assertEquals("Unrecognized parking ticket", error.getMessage());
        }
    }

    @Test
    public void should_return_error_when_fetchCar_given_used_ticker() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car1 = new Car();
        ParkingTicket ticket1 = parkingLot.park(car1);


        try {
            parkingLot.fetch(ticket1);
            parkingLot.fetch(ticket1);
        } catch (Error error) {
            assertEquals("Unrecognized parking ticket", error.getMessage());
        }
    }
}
