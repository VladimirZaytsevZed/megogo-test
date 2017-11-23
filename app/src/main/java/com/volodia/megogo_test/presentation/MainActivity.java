package com.volodia.megogo_test.presentation;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.volodia.megogo_test.R;
import com.volodia.megogo_test.presentation.screen.movie.details.MovieDetailsFragment;
import com.volodia.megogo_test.presentation.screen.movie.list.MovieListFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) showMoviesScreen();
    }

    private void showMoviesScreen() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.fl_container, MovieListFragment.getInstance())
                .commit();
    }

    public void showMovieDetailsScreen(int movieId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                        R.anim.slide_in_left, R.anim.slide_out_right)
                .replace(R.id.fl_container, MovieDetailsFragment.getInstance(movieId))
                .addToBackStack(null)
                .commit();
    }


}
