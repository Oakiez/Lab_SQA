package com.sqa.lab9.ticket;

import java.util.List;

/**
 * ชื่อ: นายวงศธร ธน.ยอด
 * รหัสนักศึกษา: 673380425-2
 * Section : 2
 */

public class ReservationResult {

    private final String reservationNumber;
    private final List<Integer> reservedSeats;

    public ReservationResult(String reservationNumber, List<Integer> reservedSeats) {
        this.reservationNumber = reservationNumber;
        this.reservedSeats = reservedSeats;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public List<Integer> getReservedSeats() {
        return reservedSeats;
    }
}
