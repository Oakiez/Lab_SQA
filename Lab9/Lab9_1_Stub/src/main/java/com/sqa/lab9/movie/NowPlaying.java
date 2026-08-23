package com.sqa.lab9.movie;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ชื่อ: นายวงศธร ธน.ยอด
 * รหัสนักศึกษา: 673380425-2
 * Section : 2
 */

public class NowPlaying {

    private final MovieService movieService;

    public NowPlaying(MovieService movieService) {
        this.movieService = movieService;
    }

    public List<String> getTitlesByCinemaType(String location, String date, String cinemaType) {
        List<Movie> allMovies = movieService.getMovies(location, date);

        return allMovies.stream()
                .filter(movie -> movie.getCinemaType().equalsIgnoreCase(cinemaType))
                .map(Movie::getTitle)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Movie> getShowtimesByCinemaType(String location, String date, String cinemaType) {
        return movieService.getMovies(location, date).stream()
                .filter(movie -> movie.getCinemaType().equalsIgnoreCase(cinemaType))
                .collect(Collectors.toList());
    }
}
