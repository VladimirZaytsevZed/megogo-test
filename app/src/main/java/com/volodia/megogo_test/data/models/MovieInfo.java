package com.volodia.megogo_test.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Volodia on 12.11.2017.
 */

public class MovieInfo {

    @SerializedName("vote_count")
    int voteCount; //731

    int id; //:19404
    boolean video; //:false

    @SerializedName("vote_average")
    float voteAverage;   //:9.1

    String title;   //:Dilwale Dulhania Le Jayenge
    double popularity; //:55.467664

    @SerializedName("poster_path")
    String posterPath; //:/2gvbZMtV1Zsl7FedJa5ysbpBx2G.jpg

    String original_language; //:hi

    @SerializedName("original_title")
    String originalTitle; //:Dilwale Dulhania Le Jayenge

    List genre_ids3; //Items

    @SerializedName("backdrop_path")
    String backdropPath; //:/nl79FQ8xWZkhL3rDr1v2RFFR6J0.jpg

    boolean adult; //:false
    String overview; //:Raj is a rich, carefree, happy
    String release_date; //:1995-10-20


    public String getPosterPath(){
        return posterPath;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public int getId() {
        return id;
    }
}
