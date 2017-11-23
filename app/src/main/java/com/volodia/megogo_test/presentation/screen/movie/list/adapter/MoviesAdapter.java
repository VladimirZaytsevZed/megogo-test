package com.volodia.megogo_test.presentation.screen.movie.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.volodia.megogo_test.R;
import com.volodia.megogo_test.presentation.base.adapter.ItemClickedListener;
import com.volodia.megogo_test.presentation.base.adapter.VHItem;
import com.volodia.megogo_test.presentation.screen.movie.list.models.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Volodia on 21.11.2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<VHItem> implements ItemClickedListener {

    public static final int TYPE_ITEM = R.layout.item_movie;
    private static final int TYPE_FOOTER_PROGRESS = R.layout.item_footer_progress;
    private static final int TYPE_FOOTER_ERROR = R.layout.item_footer_error;

    private List<Movie> data;
    private boolean showFooter;
    private int footerType;
    private final MovieListListener movieListListener;

    public MoviesAdapter(List<Movie> data, MovieListListener movieListListener) {
        this.data = data;
        this.movieListListener = movieListListener;
    }

    public MoviesAdapter(MovieListListener movieListListener) {
        data = new ArrayList<>();
        this.movieListListener = movieListListener;
    }

    @Override
    public VHItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false);
        switch (viewType) {
            case TYPE_ITEM:
                return new MovieVH(view, this);
            case TYPE_FOOTER_PROGRESS:
                return new ProgressFooterVH(view);
            case TYPE_FOOTER_ERROR:
                return new ErrorFooterVH(view, this);
            default:
                throw new Error("Invalid view holder type!");
        }

    }

    @Override
    public void onBindViewHolder(VHItem holder, int position) {
        if (position == data.size()) return;
        holder.applyData(data.get(position));
    }


    @Override
    public int getItemViewType(int position) {
        if (isFooterShowing() && position == data.size()) {
            return footerType;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + (isFooterShowing() ? 1 : 0);
    }

    private boolean isFooterShowing() {
        return showFooter;
    }

    public void showErrorFooter() {
        footerAdded(TYPE_FOOTER_ERROR);
    }

    public void showProgressFooter() {
        footerAdded(TYPE_FOOTER_PROGRESS);
    }

    private void footerAdded(int footerType) {
        this.footerType = footerType;
        if (isFooterShowing()){
            notifyItemChanged(data.size());
        } else {
            showFooter = true;
            notifyItemInserted(data.size());
        }
    }

    public void hideFooter() {
        if (showFooter){
            showFooter = false;
            notifyItemRemoved(data.size());
        }
    }

    @Override
    public void onItemClicked(int position) {
        if (position == data.size()) {
            movieListListener.retryClicked();
        } else {
            movieListListener.movieClicked(data.get(position));
        }

    }

    public void addData(List<Movie> movies) {
        data.addAll(movies);
        notifyItemRangeInserted(data.size() - movies.size(), movies.size());
    }

    public void setData(List<Movie> movies) {
        data.clear();
        data.addAll(movies);
        notifyDataSetChanged();
    }


    public static class MovieVH extends VHItem<Movie> {

        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_rating)
        TextView tvRating;

        MovieVH(View itemView, final ItemClickedListener itemClickedListener) {
            super(itemView, itemClickedListener);
        }

        @Override
        public void applyData(Movie item) {
            tvTitle.setText(String.valueOf(item.getOriginalTitle()));
            tvRating.setText(String.valueOf(item.getVoteAverage()));
            Glide.with(itemView.getContext())
                    .load(item.getPosterImageUrl())
                    .apply(new RequestOptions().placeholder(R.drawable.ic_no_photo_gray_big_test))
                    .into(ivImage);
        }
    }

    public static class ProgressFooterVH extends VHItem {

        ProgressFooterVH(View itemView) {
            super(itemView);
        }

        @Override
        public void applyData(Object item) {
        }
    }


    public static class ErrorFooterVH extends VHItem {

        ErrorFooterVH(View itemView, final ItemClickedListener itemClickedListener) {
            super(itemView, itemClickedListener);
        }

        @Override
        public void applyData(Object item) {
        }
    }

}

