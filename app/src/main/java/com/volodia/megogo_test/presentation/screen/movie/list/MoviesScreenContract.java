package com.volodia.megogo_test.presentation.screen.movie.list;

import com.volodia.megogo_test.presentation.base.mvp.BasePresenter;
import com.volodia.megogo_test.presentation.screen.movie.list.models.Movie;

import java.util.List;

/**
 * Created by Volodia on 12.11.2017.
 */

public interface MoviesScreenContract {

    interface View {

        void showMovies(List<Movie> movies);

        void showContent();

        void hideContent();

        void addMovies(List<Movie> movies);

        void showLoadingProgress(boolean firstPage);

        void hideLoadingProgress(boolean firstPage);

        void showError(boolean firstPage);

        void hideError(boolean firstPage);

        void showMovieDetails(int id);
    }

    interface Presenter extends BasePresenter<View> {
    }
}
