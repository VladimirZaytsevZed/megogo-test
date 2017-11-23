package com.volodia.megogo_test;


import com.volodia.megogo_test.presentation.screen.movie.di.MoviesComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Volodia on 12.11.2017.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    MoviesComponent plus();

}
