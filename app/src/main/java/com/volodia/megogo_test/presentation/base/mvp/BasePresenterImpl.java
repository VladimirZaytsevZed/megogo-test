package com.volodia.megogo_test.presentation.base.mvp;

import android.support.annotation.CallSuper;


/**
 * @author Vladimir Zaytsev
 *         Created on 09.08.17.
 */

public class BasePresenterImpl<VIEW> implements BasePresenter<VIEW> {

    public VIEW view;

    @CallSuper
    @Override
    public void onViewAttached(final VIEW view) {
        this.view = view;
    }

    @CallSuper
    @Override
    public void onViewDetached() {
        view = null;
    }

    @Override
    public void onViewDestroyed() {}
}