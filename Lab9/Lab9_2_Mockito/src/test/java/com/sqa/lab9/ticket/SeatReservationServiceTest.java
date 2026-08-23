package com.sqa.lab9.ticket;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * ชื่อ: นายวงศธร ธน.ยอด
 * รหัสนักศึกษา: 673380425-2
 * Section : 2
 */

@ExtendWith(MockitoExtension.class)
class SeatReservationServiceTest {

    @Mock
    private SeatInventory seatInventory;

    @Test
    void shouldReturnAvailableSeatsFromTicketSystem() {
        when(seatInventory.getAvailableSeats("MOV-001", "18:00"))
                .thenReturn(List.of(12, 13, 14, 20, 21));

        SeatReservationService service = new SeatReservationService(seatInventory);

        List<Integer> availableSeats = service.checkAvailableSeats("MOV-001", "18:00");

        assertEquals(List.of(12, 13, 14, 20, 21), availableSeats);
        verify(seatInventory, times(1)).getAvailableSeats("MOV-001", "18:00");
    }

    @Test
    void shouldReturnEmptyListWhenNoSeatsAvailable() {
        when(seatInventory.getAvailableSeats("MOV-002", "20:00"))
                .thenReturn(List.of());

        SeatReservationService service = new SeatReservationService(seatInventory);

        List<Integer> availableSeats = service.checkAvailableSeats("MOV-002", "20:00");

        assertTrue(availableSeats.isEmpty());
    }

    @Test
    void shouldReserveRequestedSeatsAndReturnReservationNumber() {
        when(seatInventory.getAvailableSeats("MOV-001", "18:00"))
                .thenReturn(List.of(12, 13, 14));
        when(seatInventory.reserveSeat("MOV-001", "18:00", 12)).thenReturn(true);
        when(seatInventory.reserveSeat("MOV-001", "18:00", 13)).thenReturn(true);
        when(seatInventory.generateReservationNumber()).thenReturn("RSV-90001");

        SeatReservationService service = new SeatReservationService(seatInventory);

        ReservationResult result = service.reserveSeats("MOV-001", "18:00", List.of(12, 13));

        assertEquals("RSV-90001", result.getReservationNumber());
        assertEquals(List.of(12, 13), result.getReservedSeats());
        verify(seatInventory).reserveSeat("MOV-001", "18:00", 12);
        verify(seatInventory).reserveSeat("MOV-001", "18:00", 13);
        verify(seatInventory).generateReservationNumber();
    }

    @Test
    void shouldThrowWhenRequestedSeatIsNotAvailable() {
        when(seatInventory.getAvailableSeats("MOV-001", "18:00"))
                .thenReturn(List.of(12, 13));

        SeatReservationService service = new SeatReservationService(seatInventory);

        assertThrows(SeatReservationService.SeatUnavailableException.class,
                () -> service.reserveSeats("MOV-001", "18:00", List.of(99)));

        verify(seatInventory, never()).reserveSeat(anyString(), anyString(), eq(99));
        verify(seatInventory, never()).generateReservationNumber();
    }
}
