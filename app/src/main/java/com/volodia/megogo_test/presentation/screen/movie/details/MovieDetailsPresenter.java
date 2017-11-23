package com.volodia.megogo_test.presentation.screen.movie.details;

import android.util.Log;

import com.volodia.megogo_test.data.models.MovieDetailsResponse;
import com.volodia.megogo_test.network.RetrofitApi;
import com.volodia.megogo_test.presentation.base.mvp.BasePresenterImpl;
import com.volodia.megogo_test.presentation.base.state.DataState;
import com.volodia.megogo_test.presentation.screen.movie.details.models.MovieDetails;
import com.volodia.megogo_test.presentation.screen.movie.di.MoviesScope;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.volodia.megogo_test.network.ServiceGenerator.API_KEY;
import static com.volodia.megogo_test.network.ServiceGenerator.LANGUAGE;

/**
 * Created by Volodia on 12.11.2017.
 */

@MoviesScope
public class MovieDetailsPresenter extends BasePresenterImpl<MovieDetailsScreenContract.View> implements MovieDetailsScreenContract.Presenter {

    RetrofitApi retrofitApi;

    private DataState viewState = DataState.NONE;
    private DataState requestState = DataState.NONE;

    MovieDetails movieDetails;
    private int k = -1;
    private int movieId = -1;

    @Inject
    public MovieDetailsPresenter(RetrofitApi retrofitApi) {
        this.retrofitApi = retrofitApi;
    }

    public void setMovieId(int movieId) {
        if (this.movieId != movieId) {
            viewState = DataState.NONE;
            requestState = DataState.NONE;
            this.movieId = movieId;
        }
    }

    public boolean isDataActual() {
        return viewState == requestState;
    }

    public void checkForUpdateViewState() {
        if (!isDataActual()) {
            updateViewState();
        }
    }


    private void updateViewState() {
        switch (requestState) {
            case READY:
                view.showMovieDetails(movieDetails);
                break;
            case ERROR:
                view.showError();
                break;
            case PROGRESS:
                view.showLoadingProgress();
                break;
        }

        switch (viewState) {
            case READY:
                view.hideContent();
                break;
            case ERROR:
                view.hideError();
                break;
            case PROGRESS:
                view.hideProgress();
                break;
        }

        viewState = requestState;
    }

    @Override
    public void onViewAttached(final MovieDetailsScreenContract.View movieScreenView) {
        super.onViewAttached(movieScreenView);
        if (requestState == DataState.NONE) {
            loadMovieDetails();
        } else {
            checkForUpdateViewState();
        }
    }

    private void loadMovieDetails() {
        if (view == null) return;
        requestState = DataState.PROGRESS;

        updateViewState();

        retrofitApi.getMovieDetails(movieId, API_KEY, LANGUAGE).enqueue(new Callback<MovieDetailsResponse>() {
            @Override
            public void onResponse(Call<MovieDetailsResponse> call, Response<MovieDetailsResponse> response) {
                //k++;
                if (k % 2 == 0) {
                    onFailure(null, null);
                    return;
                }

                requestState = DataState.READY;
                movieDetails = MovieDetails.getFrom(response.body());

                if (view != null) {
                    updateViewState();
                }
            }

            @Override
            public void onFailure(Call<MovieDetailsResponse> call, Throwable t) {
                requestState = DataState.ERROR;

                if (view != null) {
                    updateViewState();
                }
            }
        });
    }

    @Override
    public void onViewDestroyed() {
        viewState = DataState.NONE;
    }

    public void retryClicked() {
        if (requestState != DataState.PROGRESS) {
            loadMovieDetails();
        }
    }


}
