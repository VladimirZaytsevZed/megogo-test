package com.volodia.megogo_test.presentation.screen.movie.list.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.volodia.megogo_test.data.models.MovieInfo;

import java.util.ArrayList;
import java.util.List;

import static com.volodia.megogo_test.network.ServiceGenerator.BACKDROP_SIZE_W300;
import static com.volodia.megogo_test.network.ServiceGenerator.RES_BASE_URL;

/**
 * Created by Volodia on 23.11.2017.
 */

public class Movie {

    private int id; //:19404

    private float voteAverage;   //:9.1

    private String posterImageUrl; //:/2gvbZMtV1Zsl7FedJa5ysbpBx2G.jpg

    private String originalTitle; //:Dilwale Dulhania Le Jayenge

    public static Movie getFrom(MovieInfo movieInfo) {
        Movie movie = new Movie();
        movie.setId(movieInfo.getId());
        movie.setOriginalTitle(movieInfo.getOriginalTitle());
        movie.setPosterImageUrl(RES_BASE_URL + BACKDROP_SIZE_W300 + movieInfo.getPosterPath());
        movie.setVoteAverage(movieInfo.getVoteAverage());
        return movie;
    }

    @NonNull
    public static List<Movie> getFrom(@Nullable List<MovieInfo> movieInfoList) {
        List<Movie> movies = new ArrayList<>();
        if (movieInfoList != null)
            for (MovieInfo movieInfo : movieInfoList) {
                movies.add(Movie.getFrom(movieInfo));
            }
        return movies;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setPosterImageUrl(String posterImageUrl) {
        this.posterImageUrl = posterImageUrl;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public int getId() {
        return id;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getPosterImageUrl() {
        return posterImageUrl;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }
}
