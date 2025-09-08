package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuperParkingBoyTest {
    @Test
    public void should_park_highest_availability_rate_lot_when_parkCar_given_two_parking_lots() {;
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(5);
        parkingLot1.park(new Car()); parkingLot1.park(new Car());
        SuperParkingBoy parkingBoy = new SuperParkingBoy(parkingLot1, parkingLot2);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);

        assertEquals(car, parkingLot2.fetch(ticket));
    }

    @Test
    public void should_park_sequentially_when_parkCar_given_all_same_availability_rate() {
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(4);

        SuperParkingBoy parkingBoy = new SuperParkingBoy(parkingLot1, parkingLot2);

        Car car1 = new Car(); Car car2 = new Car(); Car car3 = new Car(); Car car4 = new Car();
        ParkingTicket ticket1 = parkingBoy.park(car1);
        ParkingTicket ticket2 = parkingBoy.park(car2);
        ParkingTicket ticket3 = parkingBoy.park(car3);
        ParkingTicket ticket4 = parkingBoy.park(car4);

        assertEquals(car1, parkingLot1.fetch(ticket1));
        assertEquals(car2, parkingLot2.fetch(ticket2));
        assertEquals(car3, parkingLot2.fetch(ticket3));
        assertEquals(car4, parkingLot1.fetch(ticket4));
    }

    @Test
    public void should_return_cars_when_fetchCar_given_2_parking_lots_and_2_valid_tickers() {
        SuperParkingBoy parkingBoy = new SuperParkingBoy(new ParkingLot(3), new ParkingLot(1));
        Car car1 = new Car(); Car car2 = new Car();
        ParkingTicket ticket1 = parkingBoy.park(car1);
        ParkingTicket ticket2 = parkingBoy.park(car2);

        assertEquals(car1, parkingBoy.fetch(ticket1));
        assertEquals(car2, parkingBoy.fetch(ticket2));
    }

    @Test
    public void should_return_error_when_fetchCar_given_2_parking_lots_and_null_ticket() {
        SuperParkingBoy parkingBoy = new SuperParkingBoy(new ParkingLot(), new ParkingLot());
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
        SuperParkingBoy parkingBoy = new SuperParkingBoy(new ParkingLot(), new ParkingLot());
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
        SuperParkingBoy parkingBoy = new SuperParkingBoy(new ParkingLot(1), new ParkingLot(1));
        parkingBoy.park(new Car()); parkingBoy.park(new Car());
        try {
            parkingBoy.park(new Car());
        } catch (Error error) {
            assertEquals("No available position", error.getMessage());
        }
    }
}
