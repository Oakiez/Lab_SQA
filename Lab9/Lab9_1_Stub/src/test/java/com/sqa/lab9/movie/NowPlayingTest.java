package com.sqa.lab9.movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * ชื่อ: นายวงศธร ธน.ยอด
 * รหัสนักศึกษา: 673380425-2
 * Section : 2
 */

class NowPlayingTest {

    private NowPlaying nowPlaying;

    @BeforeEach
    void setUp() {
        MovieService stub = new MovieServiceStub();
        nowPlaying = new NowPlaying(stub);
    }

    @Test
    void shouldReturnOnlyVipCinemaTitles() {
        List<String> titles = nowPlaying.getTitlesByCinemaType(
                "Central World", "2026-08-23", "VIP");

        assertEquals(3, titles.size());
        assertTrue(titles.contains("The Odyssey"));
        assertTrue(titles.contains("Spider-Man: Brand New Day"));
        assertTrue(titles.contains("The End of Oak Street"));
        assertTrue(titles.stream().noneMatch(t -> t.equals("A Quiet Town")));
    }

    @Test
    void shouldReturnOnlyImaxWithLaserTitles() {
        List<String> titles = nowPlaying.getTitlesByCinemaType(
                "Central World", "2026-08-23", "IMAX with Laser");

        assertEquals(3, titles.size());
        assertTrue(titles.contains("The Odyssey"));
        assertTrue(titles.contains("Spider-Man: Brand New Day"));
        assertTrue(titles.contains("The End of Oak Street"));
    }

    @Test
    void shouldReturnEmptyListWhenCinemaTypeDoesNotExist() {
        List<String> titles = nowPlaying.getTitlesByCinemaType(
                "Central World", "2026-08-23", "4DX");

        assertTrue(titles.isEmpty());
    }

    @Test
    void shouldReturnFullShowtimeDetailsForVip() {
        List<Movie> vipShowtimes = nowPlaying.getShowtimesByCinemaType(
                "Central World", "2026-08-23", "VIP");

        assertEquals(3, vipShowtimes.size());
        assertTrue(vipShowtimes.stream()
                .allMatch(m -> m.getCinemaType().equals("VIP")));
    }
}
