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
    public void should_return_error_when_fetchCar_given_null_ticket() {
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
    public void should_return_error_when_fetchCar_given_used_ticket() {
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

    @Test
    public void should_park_first_lot_when_park_car_given_two_parking_lots() {;
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        StandardParkingBoy parkingBoy = new StandardParkingBoy(parkingLot1, parkingLot2);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);

        assertEquals(car, parkingLot1.fetch(ticket));
    }

    @Test
    public void should_park_second_lot_when_park_car_given_first_full_second_not_full() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot();
        StandardParkingBoy parkingBoy = new StandardParkingBoy(parkingLot1, parkingLot2);

        Car car1 = new Car(); Car car2 = new Car();
        parkingBoy.park(car1);
        ParkingTicket ticket2 = parkingBoy.park(car2);

        assertEquals(car2, parkingLot2.fetch(ticket2));
    }

    @Test
    public void should_return_cars_when_fetchCar_given_2_parking_lots_and_2_valid_tickers() {
        StandardParkingBoy parkingBoy = new StandardParkingBoy(new ParkingLot(1), new ParkingLot(1));
        Car car1 = new Car(); Car car2 = new Car();
        ParkingTicket ticket1 = parkingBoy.park(car1);
        ParkingTicket ticket2 = parkingBoy.park(car2);

        assertEquals(car1, parkingBoy.fetch(ticket1));
        assertEquals(car2, parkingBoy.fetch(ticket2));
    }

    @Test
    public void should_return_error_when_fetchCar_given_2_parking_lots_and_null_ticket() {
        StandardParkingBoy parkingBoy = new StandardParkingBoy(new ParkingLot(), new ParkingLot());
        Car car1 = new Car();
        parkingBoy.park(car1);

        try {
            parkingBoy.fetch(null);
        } catch (Error error) {
            assertEquals("Unrecognized parking ticket", error.getMessage());
        }
    }

    @Test
    public void should_return_error_when_fetchCar_given_2_parking_lots_and_used_ticket() {
        StandardParkingBoy parkingBoy = new StandardParkingBoy(new ParkingLot(), new ParkingLot());
        Car car1 = new Car();
        ParkingTicket ticket1 = parkingBoy.park(car1);

        try {
            parkingBoy.fetch(ticket1);
            parkingBoy.fetch(ticket1);
        } catch (Error error) {
            assertEquals("Unrecognized parking ticket", error.getMessage());
        }
    }

    @Test
    public void should_return_error_when_fetchCar_given_2_full_parking_lots(){
        StandardParkingBoy parkingBoy = new StandardParkingBoy(new ParkingLot(1), new ParkingLot(1));
        parkingBoy.park(new Car()); parkingBoy.park(new Car());
        try {
            parkingBoy.park(new Car());
        } catch (Error error) {
            assertEquals("No available position", error.getMessage());
        }
    }
}
