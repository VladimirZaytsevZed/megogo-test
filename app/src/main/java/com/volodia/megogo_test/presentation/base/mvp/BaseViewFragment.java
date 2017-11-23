package com.volodia.megogo_test.presentation.base.mvp;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import javax.inject.Inject;


/**
 * @author Vladimir Zaytsev
 *         Created on 01.11.17.
 */

public abstract class BaseViewFragment<P extends BasePresenter> extends Fragment {

    @Inject
    public P presenter;

    public abstract void inject();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        inject();
        super.onCreate(savedInstanceState);
    }

    @CallSuper
    @Override
    public void onPause() {
        super.onPause();
        presenter.onViewDetached();
    }

    @CallSuper
    @Override
    public void onResume() {
        super.onResume();
        presenter.onViewAttached(this);
    }


    @CallSuper
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onViewDestroyed();
    }



}
