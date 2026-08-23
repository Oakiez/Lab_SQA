package com.sqa.lab9.ticket;

/**
 * ชื่อ: นายวงศธร ธน.ยอด
 * รหัสนักศึกษา: 673380425-2
 * Section : 2
 */

public class Ticket {

    private final String reservationNumber;
    private final String movieId;
    private final String hallType;
    private final String date;
    private final int seatNumber;

    public Ticket(String reservationNumber, String movieId, String hallType, String date, int seatNumber) {
        this.reservationNumber = reservationNumber;
        this.movieId = movieId;
        this.hallType = hallType;
        this.date = date;
        this.seatNumber = seatNumber;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getHallType() {
        return hallType;
    }

    public String getDate() {
        return date;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
}
