package com.sqa.lab9.ticket;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * ชื่อ: นายวงศธร ธน.ยอด
 * รหัสนักศึกษา: 673380425-2
 * Section : 2
 */

@ExtendWith(MockitoExtension.class)
class GateCheckinServiceTest {

    @Mock
    private TicketScanRegistry scanRegistry;

    @Test
    void shouldAllowEntryAndReturnUpdatedScannedCount() {
        Ticket ticket = new Ticket("RSV-90001", "MOV-001", "VIP", "2026-08-23", 12);

        when(scanRegistry.getScannedCount("MOV-001", "VIP", "2026-08-23")).thenReturn(5);

        GateCheckinService service = new GateCheckinService(scanRegistry);

        CheckinResult result = service.checkIn(ticket, "VIP");

        assertTrue(result.isAllowed());
        assertEquals(5, result.getScannedCountForShowing());
        verify(scanRegistry).recordScan("RSV-90001", "MOV-001", "VIP", "2026-08-23");
        verify(scanRegistry).getScannedCount("MOV-001", "VIP", "2026-08-23");
    }

    @Test
    void shouldRejectEntryWhenHallTypeDoesNotMatchTicket() {
        Ticket ticket = new Ticket("RSV-90002", "MOV-001", "Standard", "2026-08-23", 5);

        when(scanRegistry.getScannedCount("MOV-001", "VIP", "2026-08-23")).thenReturn(10);

        GateCheckinService service = new GateCheckinService(scanRegistry);

        CheckinResult result = service.checkIn(ticket, "VIP");

        assertFalse(result.isAllowed());
        assertEquals(10, result.getScannedCountForShowing());
        verify(scanRegistry, never()).recordScan(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void shouldAccumulateCountAcrossMultipleScans() {
        Ticket ticket1 = new Ticket("RSV-1", "MOV-001", "Standard", "2026-08-23", 1);
        Ticket ticket2 = new Ticket("RSV-2", "MOV-001", "Standard", "2026-08-23", 2);

        when(scanRegistry.getScannedCount("MOV-001", "Standard", "2026-08-23"))
                .thenReturn(1)
                .thenReturn(2);

        GateCheckinService service = new GateCheckinService(scanRegistry);

        CheckinResult first = service.checkIn(ticket1, "Standard");
        CheckinResult second = service.checkIn(ticket2, "Standard");

        assertEquals(1, first.getScannedCountForShowing());
        assertEquals(2, second.getScannedCountForShowing());
        verify(scanRegistry, times(2)).getScannedCount("MOV-001", "Standard", "2026-08-23");
    }
}
