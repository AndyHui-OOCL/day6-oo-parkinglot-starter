package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StandardParkingBoyTest {
    @Test
    public void should_return_parking_ticket_when_parkCar_given_a_car(){
        StandardParkingBoy parkingBoy = new StandardParkingBoy();

        ParkingTicket ticket = parkingBoy.park(new Car());

        assertNotNull(ticket);
    }

    @Test
    public void should_return_error_when_parkCar_given_valid_car_and_parking_lot_full(){
        StandardParkingBoy parkingBoy = new StandardParkingBoy();
        try {
            for(int i = 0; i < 11; i++) {
                parkingBoy.park(new Car());
            }
        } catch (Error error) {
            assertEquals("No available position", error.getMessage());
        }
    }

    @Test
    public void should_return_no_parking_ticker_when_parkCar_given_invalid_car_and_parking_lot_not_full(){
        StandardParkingBoy parkingBoy = new StandardParkingBoy();

        ParkingTicket ticket = parkingBoy.park(null);

        assertNull(ticket);
    }

    @Test
    public void should_return_no_parking_ticker_when_parkCar_given_parked_car(){
        StandardParkingBoy parkingBoy = new StandardParkingBoy();
        Car car1 = new Car();

        ParkingTicket ticket1 = parkingBoy.park(car1);
        ParkingTicket ticket2 = parkingBoy.park(car1);

        assertNull(ticket2);
    }

    @Test
    public void should_return_car_when_fetchCar_given_a_valid_parking_ticket() {
        StandardParkingBoy parkingBoy = new StandardParkingBoy();
        Car car1 = new Car();
        ParkingTicket ticket1 = parkingBoy.park(car1);

        Car fetchedCar = parkingBoy.fetch(ticket1);

        assertEquals(car1, fetchedCar);
    }

    @Test
    public void should_return_error_message_when_fetchCar_given_a_nonvalid_parking_ticket() {
        StandardParkingBoy parkingBoy = new StandardParkingBoy();
        Car car1 = new Car();
        parkingBoy.park(car1);

        try {
            parkingBoy.fetch(new ParkingTicket());
        } catch (Error error) {
            assertEquals("Unrecognized parking ticket", error.getMessage());
        }
    }

    @Test
    public void should_return_error_when_fetchCar_given_null_ticker() {
        StandardParkingBoy parkingBoy = new StandardParkingBoy();
        Car car1 = new Car();
        parkingBoy.park(car1);

        try {
            parkingBoy.fetch(null);
        } catch (Error error) {
            assertEquals("Unrecognized parking ticket", error.getMessage());
        }
    }

    @Test
    public void should_return_error_when_fetchCar_given_used_ticker() {
        StandardParkingBoy parkingBoy = new StandardParkingBoy();
        Car car1 = new Car();
        ParkingTicket ticket1 = parkingBoy.park(car1);

        try {
            parkingBoy.fetch(ticket1);
            parkingBoy.fetch(ticket1);
        } catch (Error error) {
            assertEquals("Unrecognized parking ticket", error.getMessage());
        }
    }
}
