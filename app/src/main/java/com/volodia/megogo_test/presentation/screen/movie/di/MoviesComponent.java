package com.volodia.megogo_test.presentation.screen.movie.di;

import com.volodia.megogo_test.presentation.screen.movie.details.MovieDetailsFragment;
import com.volodia.megogo_test.presentation.screen.movie.list.MovieListFragment;

import dagger.Subcomponent;

@MoviesScope
@Subcomponent()
public interface MoviesComponent {

    void inject(MovieListFragment movieListFragment);

    void inject(MovieDetailsFragment movieDetailsFragment);
}