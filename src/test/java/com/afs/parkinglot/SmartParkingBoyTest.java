package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmartParkingBoyTest {
    @Test
    public void should_park_most_available_plot_when_parkCar_given_two_parking_lots() {;
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(5);
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);

        assertEquals(car, parkingLot2.fetch(ticket));
    }

    @Test
    public void should_park_most_available_lot_when_parkCar_given_first_others_not_full() {
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingLot parkingLot3 = new ParkingLot(3);
        StandardParkingBoy parkingBoy = new StandardParkingBoy(parkingLot1, parkingLot2, parkingLot3);

        Car car1 = new Car(); Car car2 = new Car();
        ParkingTicket ticket1 = parkingBoy.park(car1);
        ParkingTicket ticket2 = parkingBoy.park(car2);

        assertEquals(car1, parkingLot2.fetch(ticket1));
        assertEquals(car2, parkingLot2.fetch(ticket2));
    }

    @Test
    public void should_return_cars_when_fetchCar_given_2_parking_lots_and_2_valid_tickers() {
        StandardParkingBoy parkingBoy = new StandardParkingBoy(new ParkingLot(3), new ParkingLot(1));
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
