package com.volodia.megogo_test.network;

import com.volodia.megogo_test.data.models.MovieDetailsResponse;
import com.volodia.megogo_test.data.models.TopMoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitApi {

    @GET("/3/movie/top_rated")
    Call<TopMoviesResponse> getMovies(@Query("api_key") String apiKey, @Query("page") int page);


    @GET("3/movie/{movie_id}")
    Call<MovieDetailsResponse> getMovieDetails(@Path("movie_id") int movieId,
                                               @Query("api_key") String apiKey,
                                               @Query("language") String language);


}
