package com.sqa.lab9.ticket;

/**
 * ชื่อ: นายวงศธร ธน.ยอด
 * รหัสนักศึกษา: 673380425-2
 * Section : 2
 */

public interface TicketScanRegistry {

    void recordScan(String reservationNumber, String movieId, String hallType, String date);
    int getScannedCount(String movieId, String hallType, String date);
}
