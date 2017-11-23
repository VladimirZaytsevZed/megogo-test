package com.volodia.megogo_test.presentation.screen.movie.list.adapter;

import com.volodia.megogo_test.presentation.screen.movie.list.models.Movie;

/**
 * Created by Volodia on 21.11.2017.
 */

public interface MovieListListener {

    void retryClicked();

    void movieClicked(Movie movieInfo);

}
