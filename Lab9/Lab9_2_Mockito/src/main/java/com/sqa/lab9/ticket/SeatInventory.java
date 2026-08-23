package com.sqa.lab9.ticket;

import java.util.List;

/**
 * ชื่อ: นายวงศธร ธน.ยอด
 * รหัสนักศึกษา: 673380425-2
 * Section : 2
 */

public interface SeatInventory {


    List<Integer> getAvailableSeats(String movieId, String showtime);

    boolean reserveSeat(String movieId, String showtime, int seatNumber);

    String generateReservationNumber();
}
