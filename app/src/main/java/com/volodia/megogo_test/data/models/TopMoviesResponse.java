package com.volodia.megogo_test.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Volodia on 12.11.2017.
 */

public class TopMoviesResponse {

    int page;

    @SerializedName("total_results")
    int totalResults; //:7319

    @SerializedName("total_pages")
    int totalPages;    //:366

    List<MovieInfo> results; //

    public List<MovieInfo> getResults() {
        return results;
    }
}
