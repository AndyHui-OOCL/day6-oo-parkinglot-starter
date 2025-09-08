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
}
