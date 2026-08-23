package com.sqa.lab9.ticket;

/**
 * ชื่อ: นายวงศธร ธน.ยอด
 * รหัสนักศึกษา: 673380425-2
 * Section : 2
 */

public class GateCheckinService {

    private final TicketScanRegistry scanRegistry;

    public GateCheckinService(TicketScanRegistry scanRegistry) {
        this.scanRegistry = scanRegistry;
    }

    public CheckinResult checkIn(Ticket ticket, String hallTypeAtGate) {
        if (!ticket.getHallType().equalsIgnoreCase(hallTypeAtGate)) {
            int currentCount = scanRegistry.getScannedCount(
                    ticket.getMovieId(), hallTypeAtGate, ticket.getDate());
            return new CheckinResult(
                    false,
                    "Ticket is for hall type '" + ticket.getHallType()
                            + "' but customer is trying to enter '" + hallTypeAtGate + "'",
                    currentCount);
        }

        scanRegistry.recordScan(
                ticket.getReservationNumber(), ticket.getMovieId(), hallTypeAtGate, ticket.getDate());

        int updatedCount = scanRegistry.getScannedCount(
                ticket.getMovieId(), hallTypeAtGate, ticket.getDate());

        return new CheckinResult(true, "Welcome", updatedCount);
    }
}
