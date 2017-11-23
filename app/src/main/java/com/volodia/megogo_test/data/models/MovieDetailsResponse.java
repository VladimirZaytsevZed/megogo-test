package com.volodia.megogo_test.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Volodia on 22.11.2017.
 */

public class MovieDetailsResponse {

    @SerializedName("release_date")
    String releaseDate;

    List<Genre> genres;

    String title;

    String overview;

    @SerializedName("backdrop_path")
    String backdropPath;

    @SerializedName("poster_path")
    String posterPath;


    public String getReleaseDate() {
        return releaseDate;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
