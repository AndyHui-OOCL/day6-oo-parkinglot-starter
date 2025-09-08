package com.afs.parkinglot;

public class ParkingTicket {
    private Car parkedCar;

    ParkingTicket(Car parkedCar) {
        this.parkedCar = parkedCar;
    }

    public Car getParkedCar() {
        return parkedCar;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || obj.getClass() != getClass()) return false;
        return (this.parkedCar == ((ParkingTicket) obj).parkedCar);
    }
}
