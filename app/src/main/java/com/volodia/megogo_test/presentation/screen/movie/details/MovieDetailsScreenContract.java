package com.volodia.megogo_test.presentation.screen.movie.details;

import com.volodia.megogo_test.presentation.base.mvp.BasePresenter;
import com.volodia.megogo_test.presentation.screen.movie.details.models.MovieDetails;

/**
 * Created by Volodia on 12.11.2017.
 */

public interface MovieDetailsScreenContract {

    interface View {
        void showLoadingProgress();

        void showMovieDetails(MovieDetails movieDetails);

        void showError();

        void hideContent();

        void hideError();

        void hideProgress();
    }

    interface Presenter extends BasePresenter<View> {
    }
}
