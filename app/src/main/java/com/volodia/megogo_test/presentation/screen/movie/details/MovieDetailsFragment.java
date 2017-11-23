package com.volodia.megogo_test.presentation.screen.movie.details;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.volodia.megogo_test.R;
import com.volodia.megogo_test.presentation.base.mvp.BaseViewFragment;
import com.volodia.megogo_test.presentation.screen.movie.details.models.MovieDetails;
import com.volodia.megogo_test.presentation.MoviesViewModel;
import com.volodia.megogo_test.utils.UiUtils;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Volodia on 12.11.2017.
 */

public class MovieDetailsFragment extends BaseViewFragment<MovieDetailsPresenter> implements MovieDetailsScreenContract.View {

    private static final java.lang.String KEY_MOVIE_ID = "key_movie_id";

    @BindView(R.id.progress) ProgressBar progressBar;
    @BindView(R.id.ll_content) View llContent;
    @BindView(R.id.ll_error) View llError;

    @BindView(R.id.iv_header) ImageView ivHeader;
    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.tv_subtitle) TextView tvSubtitle;
    @BindView(R.id.tv_description) TextView tvDescription;

    public static MovieDetailsFragment getInstance(int movieId) {
        Bundle args = new Bundle();
        args.putInt(KEY_MOVIE_ID, movieId);
        MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
        movieDetailsFragment.setArguments(args);
        return movieDetailsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup contentView = (ViewGroup) inflater.inflate(R.layout.fragment_movie_details, container, false);
        ButterKnife.bind(this, contentView);
        getActivity().setTitle(R.string.title_movie_details);
        initViews();
        return contentView;
    }

    private void initViews() {

    }

    private void initToolbar() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            presenter.setMovieId(getArguments().getInt(KEY_MOVIE_ID));
        }
    }

    @Override
    public void inject() {
        MoviesViewModel moviesViewModel = ViewModelProviders.of(getActivity()).get(MoviesViewModel.class);
        Log.v("XXX", "inject moviesViewModel " + moviesViewModel);
        moviesViewModel.getMoviesComponent().inject(this);
        Log.v("XXX", "inject component " + moviesViewModel.getMoviesComponent());
    }

    @OnClick(R.id.btn_retry)
    public void btnRetryClicked() {
        presenter.retryClicked();
    }


    @Override
    public void showLoadingProgress() {
        UiUtils.showView(progressBar);
    }

    @Override
    public void showMovieDetails(MovieDetails movieDetails) {
        UiUtils.showView(llContent);
        tvTitle.setText(movieDetails.getTitle());
        tvSubtitle.setText(movieDetails.getSubtitle());
        tvDescription.setText(movieDetails.getOverview());

        Glide.with(getContext())
                .load(movieDetails.getImageUrl())
                .apply(new RequestOptions().placeholder(R.drawable.ic_no_photo_gray_big_test))
                .into(ivHeader);

    }

    @Override
    public void showError() {
        UiUtils.showView(llError);
    }

    @Override
    public void hideContent() {
        UiUtils.hideView(llContent);
    }

    @Override
    public void hideError() {
        UiUtils.hideView(llError);
    }

    @Override
    public void hideProgress() {
        UiUtils.hideView(progressBar);
    }


}

