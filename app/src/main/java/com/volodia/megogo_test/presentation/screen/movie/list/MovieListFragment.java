package com.volodia.megogo_test.presentation.screen.movie.list;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.volodia.megogo_test.R;
import com.volodia.megogo_test.presentation.MainActivity;
import com.volodia.megogo_test.presentation.MoviesViewModel;
import com.volodia.megogo_test.presentation.base.mvp.BaseViewFragment;
import com.volodia.megogo_test.presentation.screen.movie.list.adapter.MovieListListener;
import com.volodia.megogo_test.presentation.screen.movie.list.adapter.MoviesAdapter;
import com.volodia.megogo_test.presentation.screen.movie.list.models.Movie;
import com.volodia.megogo_test.utils.UiUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;
import static com.volodia.megogo_test.presentation.screen.movie.list.adapter.MoviesAdapter.TYPE_ITEM;

/**
 * Created by Volodia on 12.11.2017.
 */

public class MovieListFragment extends BaseViewFragment<MovieListPresenter> implements MoviesScreenContract.View, MovieListListener {

    private static final int SPAN_COUNT_PORTRAIT = 2;
    private static final int SPAN_COUNT_LANDSCAPE = 3;
    private static final int UPDATE_THRESHOLD = 3;

    @BindView(R.id.progress) View progressBar;
    @BindView(R.id.rv_movies) RecyclerView rvMovies;
    @BindView(R.id.ll_error) View llError;

    MoviesAdapter moviesAdapter;

    public static Fragment getInstance() {
        return new MovieListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup contentView = (ViewGroup) inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, contentView);
        getActivity().setTitle(R.string.title_movie_list);
        initViews();
        return contentView;
    }

    private void initViews() {
        moviesAdapter = new MoviesAdapter(this);
        final int spanCount = getActivity().getResources().getConfiguration().orientation
                == ORIENTATION_PORTRAIT ? SPAN_COUNT_PORTRAIT : SPAN_COUNT_LANDSCAPE;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), spanCount);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (moviesAdapter.getItemViewType(position)) {
                    case TYPE_ITEM:
                        return 1;
                    default:
                        return spanCount;
                }
            }
        });
        rvMovies.setLayoutManager(gridLayoutManager);
        rvMovies.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy < 0) return;
                int lastVisible = ((GridLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                if (lastVisible > recyclerView.getAdapter().getItemCount() - UPDATE_THRESHOLD) {
                    presenter.onScrollToEnd();
                }
            }
        });
        rvMovies.setAdapter(moviesAdapter);
    }

    @Override
    public void inject() {
        MoviesViewModel moviesViewModel = ViewModelProviders.of(getActivity()).get(MoviesViewModel.class);
        moviesViewModel.getMoviesComponent().inject(this);
    }

    @Override
    public void showMovies(List<Movie> movies) {
        moviesAdapter.setData(movies);
        showContent();
    }

    @Override
    public void addMovies(List<Movie> movies) {
        moviesAdapter.addData(movies);
    }

    @Override
    public void showContent() {
        UiUtils.showView(rvMovies);
    }

    @Override
    public void hideContent() {
        UiUtils.hideView(rvMovies);
    }


    @Override
    public void showLoadingProgress(boolean firstPage) {
        if (firstPage) {
            UiUtils.showView(progressBar);
        } else {
            moviesAdapter.showProgressFooter();
        }
    }

    @Override
    public void hideLoadingProgress(boolean firstPage) {
        if (firstPage) {
            UiUtils.hideView(progressBar);
        } else {
            moviesAdapter.hideFooter();
        }
    }

    @Override
    public void showError(boolean firstPage) {
        if (firstPage) {
            UiUtils.showView(llError);
        } else {
            moviesAdapter.showErrorFooter();
        }
    }

    @Override
    public void hideError(boolean firstPage) {
        if (firstPage) {
            UiUtils.hideView(llError);
        } else {
            moviesAdapter.hideFooter();
        }
    }

    @Override
    public void showMovieDetails(int id) {
        ((MainActivity)getActivity()).showMovieDetailsScreen(id);
    }

    @OnClick(R.id.btn_retry)
    public void btnRetryClicked() {
        presenter.retryClicked();
    }


    @Override
    public void retryClicked() {
        presenter.retryClicked();
    }

    @Override
    public void movieClicked(Movie movie) {
        presenter.movieClicked(movie);
    }

}
