package com.volodia.megogo_test.presentation;

import android.arch.lifecycle.ViewModel;

import com.volodia.megogo_test.App;
import com.volodia.megogo_test.presentation.screen.movie.di.MoviesComponent;

/**
 * Created by Volodia on 13.11.2017.
 */

public class MoviesViewModel extends ViewModel {

    MoviesComponent moviesComponent;

    public MoviesComponent getMoviesComponent() {
        if (moviesComponent == null ){
            moviesComponent = App.getInstance().getAppComponent().plus();
        }
        return moviesComponent;
    }

    public void setMoviesComponent(MoviesComponent moviesComponent) {
        this.moviesComponent = moviesComponent;
    }

}
