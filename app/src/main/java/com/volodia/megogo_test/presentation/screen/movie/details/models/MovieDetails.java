package com.volodia.megogo_test.presentation.screen.movie.details.models;

import com.volodia.megogo_test.data.models.Genre;
import com.volodia.megogo_test.data.models.MovieDetailsResponse;

import java.util.List;

import static com.volodia.megogo_test.network.ServiceGenerator.BACKDROP_SIZE_W300;
import static com.volodia.megogo_test.network.ServiceGenerator.RES_BASE_URL;

/**
 * Created by Volodia on 22.11.2017.
 */

public class MovieDetails {

    private String title;
    private String subtitle;
    private String imageUrl;
    private String overview;

    public static MovieDetails getFrom(MovieDetailsResponse movieDetailsResponse) {
        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setTitle(movieDetailsResponse.getTitle());

        StringBuilder sbSubtitle = new StringBuilder();

        sbSubtitle.append(movieDetailsResponse.getReleaseDate());
        List<Genre> genres = movieDetailsResponse.getGenres();
        if (genres != null && genres.size() > 0){
            for(Genre genre: genres){
                sbSubtitle.append(", ");
                sbSubtitle.append(genre.getName());
            }
            sbSubtitle.append(".");
        }
        movieDetails.setSubtitle(sbSubtitle.toString());
        movieDetails.setImageUrl(RES_BASE_URL + BACKDROP_SIZE_W300 + movieDetailsResponse.getBackdropPath());
        movieDetails.setOverview(movieDetailsResponse.getOverview());
        return movieDetails;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getOverview() {
        return overview;
    }
}
