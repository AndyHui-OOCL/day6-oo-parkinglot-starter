package com.afs.parkinglot;

public class ParkingLotException extends RuntimeException {
    public static final String NO_POSITION_ERROR = "No available position";
    public static final String UNRECOGNIZED_TICKET_ERROR = "Unrecognized parking ticket";

    public ParkingLotException(String message) {
        super(message);
    }
}
