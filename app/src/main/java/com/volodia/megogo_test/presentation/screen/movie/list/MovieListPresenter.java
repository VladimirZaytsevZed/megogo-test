package com.volodia.megogo_test.presentation.screen.movie.list;

import android.util.Log;

import com.volodia.megogo_test.data.models.TopMoviesResponse;
import com.volodia.megogo_test.network.RetrofitApi;
import com.volodia.megogo_test.presentation.base.mvp.BasePresenterImpl;
import com.volodia.megogo_test.presentation.base.state.DataState;
import com.volodia.megogo_test.presentation.base.state.PagingDataState;
import com.volodia.megogo_test.presentation.screen.movie.di.MoviesScope;
import com.volodia.megogo_test.presentation.screen.movie.list.models.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.volodia.megogo_test.network.ServiceGenerator.API_KEY;

/**
 * Created by Volodia on 12.11.2017.
 */

@MoviesScope
public class MovieListPresenter extends BasePresenterImpl<MoviesScreenContract.View> implements MoviesScreenContract.Presenter {

    private static final int FIRST_PAGE = 1;
    RetrofitApi retrofitApi;

    private PagingDataState viewState = new PagingDataState();
    private PagingDataState requestState = new PagingDataState();

    private List<Movie> movies = new ArrayList<>();
    private List<Movie> lastPortion = new ArrayList<>();
    private int k = -1;

    @Inject
    public MovieListPresenter(RetrofitApi retrofitApi) {
        this.retrofitApi = retrofitApi;
    }

    private boolean isDataActual() {
        return viewState.equals(requestState);
    }

    private void checkForUpdateViewState() {
        if (isDataActual()) {
            return;
        }
        updateViewState();
    }

    public void updateViewState() {
        switch (viewState.getDataState()) {
            case READY:
                if (viewState.getPage() == 0) {
                    view.hideContent();
                }
                break;
            case ERROR:
                view.hideError(viewState.getPage() == 0);
                break;
            case PROGRESS:
                view.hideLoadingProgress(viewState.getPage() == 0);
                break;
        }

        if (viewState.getPage() != requestState.getPage()) {
            if (requestState.getPage() - viewState.getPage() == 1) {
                view.addMovies(lastPortion);
            } else {
                view.showMovies(movies);
            }
        }


        switch (requestState.getDataState()) {

            case ERROR:
                view.showError(requestState.getPage() == 0);
                break;
            case PROGRESS:
                view.showLoadingProgress(requestState.getPage() == 0);
                break;
        }

        viewState.setPage(requestState.getPage());
        viewState.setDataState(requestState.getDataState());

    }

    @Override
    public void onViewAttached(final MoviesScreenContract.View movieScreenView) {
        super.onViewAttached(movieScreenView);
        if (requestState.isInitState()) {
            loadItems(FIRST_PAGE);
        } else {
            checkForUpdateViewState();
        }
    }


    public void onScrollToEnd() {
        if (requestState.getDataState() == DataState.READY) {
            loadItems(requestState.getPage() + FIRST_PAGE);
        }
    }

    private void loadItems(final int page) {
        if (view == null) return;

        requestState.setDataState(DataState.PROGRESS);
        updateViewState();

        retrofitApi.getMovies(API_KEY, page).enqueue(new Callback<TopMoviesResponse>() {
            @Override
            public void onResponse(Call<TopMoviesResponse> call, Response<TopMoviesResponse> response) {
                //k++;   for testing error
                if (k % 2 == 0) {
                    onFailure(null, null);
                    return;
                }
                ///////////////////////////////////////

                List<Movie> movies = Movie.getFrom(response.body().getResults());

                requestState.setPage(page);
                lastPortion = movies;
                requestState.setDataState(DataState.READY);

                MovieListPresenter.this.movies.addAll(movies);

                if (view != null) {
                    updateViewState();
                }
            }

            @Override
            public void onFailure(Call<TopMoviesResponse> call, Throwable t) {
                requestState.setDataState(DataState.ERROR);

                if (view != null) {
                    updateViewState();
                }
            }
        });
    }


    @Override
    public void onViewDestroyed() {
        viewState.clear();
    }

    public void retryClicked() {
        if (requestState.getDataState() != DataState.PROGRESS) {
            loadItems(requestState.getPage() + 1);
        }
    }

    public void movieClicked(Movie movie) {
        view.showMovieDetails(movie.getId());
    }
}

