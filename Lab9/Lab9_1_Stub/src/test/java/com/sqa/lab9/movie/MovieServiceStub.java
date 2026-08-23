package com.sqa.lab9.movie;

import java.util.List;

/**
 * ชื่อ: นายวงศธร ธน.ยอด
 * รหัสนักศึกษา: 673380425-2
 * Section : 2
 */

public class MovieServiceStub implements MovieService {

    @Override
    public List<Movie> getMovies(String location, String date) {
        return List.of(
                new Movie("The Odyssey", "IMAX with Laser", "13:00"),
                new Movie("Spider-Man: Brand New Day", "IMAX with Laser", "15:30"),
                new Movie("The End of Oak Street", "IMAX with Laser", "18:00"),
                new Movie("The Odyssey", "VIP", "12:30"),
                new Movie("Spider-Man: Brand New Day", "VIP", "16:00"),
                new Movie("The End of Oak Street", "VIP", "19:15"),
                new Movie("A Quiet Town", "Standard", "14:00"),
                new Movie("A Quiet Town", "Standard", "20:00")
        );
    }
}
