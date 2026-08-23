package com.sqa.lab9.ticket;

import java.util.ArrayList;
import java.util.List;

/**
 * ชื่อ: นายวงศธร ธน.ยอด
 * รหัสนักศึกษา: 673380425-2
 * Section : 2
 */
public class SeatReservationService {

    private final SeatInventory seatInventory;

    public SeatReservationService(SeatInventory seatInventory) {
        this.seatInventory = seatInventory;
    }

    public List<Integer> checkAvailableSeats(String movieId, String showtime) {
        return seatInventory.getAvailableSeats(movieId, showtime);
    }


    public ReservationResult reserveSeats(String movieId, String showtime, List<Integer> requestedSeats) {
        if (requestedSeats == null || requestedSeats.isEmpty()) {
            throw new IllegalArgumentException("Must request at least one seat");
        }

        List<Integer> availableSeats = seatInventory.getAvailableSeats(movieId, showtime);
        for (Integer seat : requestedSeats) {
            if (!availableSeats.contains(seat)) {
                throw new SeatUnavailableException("Seat " + seat + " is not available");
            }
        }

        List<Integer> reservedSeats = new ArrayList<>();
        for (Integer seat : requestedSeats) {
            boolean ok = seatInventory.reserveSeat(movieId, showtime, seat);
            if (!ok) {
                throw new SeatUnavailableException("Seat " + seat + " could not be reserved");
            }
            reservedSeats.add(seat);
        }

        String reservationNumber = seatInventory.generateReservationNumber();
        return new ReservationResult(reservationNumber, reservedSeats);
    }

    public static class SeatUnavailableException extends RuntimeException {
        public SeatUnavailableException(String message) {
            super(message);
        }
    }
}
