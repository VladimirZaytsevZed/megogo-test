package com.volodia.megogo_test.presentation.base.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

public abstract class VHItem<T> extends RecyclerView.ViewHolder {

    public VHItem(View itemView) {
        this(itemView, null);
    }

    public VHItem(View itemView,@Nullable final ItemClickedListener itemClickedListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        if (itemClickedListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickedListener.onItemClicked(getAdapterPosition());
                }
            });
        }
    }

    public abstract void applyData(T item);

}