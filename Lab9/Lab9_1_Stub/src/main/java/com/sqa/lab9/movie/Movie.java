package com.sqa.lab9.movie;

/**
 * Represents a single movie showtime entry returned by MoviePortal.
 * cinemaType examples: "Standard", "VIP", "IMAX with Laser"
 */
public class Movie {

    private final String title;
    private final String cinemaType;
    private final String showtime;

    public Movie(String title, String cinemaType, String showtime) {
        this.title = title;
        this.cinemaType = cinemaType;
        this.showtime = showtime;
    }

    public String getTitle() {
        return title;
    }

    public String getCinemaType() {
        return cinemaType;
    }

    public String getShowtime() {
        return showtime;
    }

    @Override
    public String toString() {
        return title + " (" + cinemaType + ", " + showtime + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return title.equals(movie.title)
                && cinemaType.equals(movie.cinemaType)
                && showtime.equals(movie.showtime);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(title, cinemaType, showtime);
    }
}
