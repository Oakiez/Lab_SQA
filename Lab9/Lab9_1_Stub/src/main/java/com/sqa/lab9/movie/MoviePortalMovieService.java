package com.sqa.lab9.movie;

import java.util.List;

/**
 * ชื่อ: นายวงศธร ธน.ยอด
 * รหัสนักศึกษา: 673380425-2
 * Section : 2
 */

public class MoviePortalMovieService implements MovieService {

    private final String moviePortalBaseUrl;

    public MoviePortalMovieService(String moviePortalBaseUrl) {
        this.moviePortalBaseUrl = moviePortalBaseUrl;
    }

    @Override
    public List<Movie> getMovies(String location, String date) {
        // In a real system this would call out to MoviePortal, e.g.:
        //   GET {moviePortalBaseUrl}/movies?location={location}&date={date}
        // and parse the JSON/XML response into Movie objects.
        //
        // This lab focuses on Test Doubles, so the real network call is
        // intentionally left unimplemented - production code would replace
        // this with an actual HTTP client call (e.g. HttpClient, RestTemplate).
        throw new UnsupportedOperationException(
                "MoviePortalMovieService calls the real external MoviePortal at "
                        + moviePortalBaseUrl
                        + " and is not exercised in unit/integration tests. Use MovieServiceStub instead.");
    }
}
