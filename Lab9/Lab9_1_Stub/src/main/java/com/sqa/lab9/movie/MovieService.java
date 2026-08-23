package com.sqa.lab9.movie;

import java.util.List;

/**
 * ชื่อ: นายวงศธร ธน.ยอด
 * รหัสนักศึกษา: 673380425-2
 * Section : 2
 */

public interface MovieService {
    List<Movie> getMovies(String location, String date);
}
