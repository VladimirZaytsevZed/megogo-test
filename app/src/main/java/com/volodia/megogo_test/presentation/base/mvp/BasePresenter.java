package com.volodia.megogo_test.presentation.base.mvp;

public interface BasePresenter<V> {


    void onViewAttached(V view);


    void onViewDetached();


    void onViewDestroyed();

}