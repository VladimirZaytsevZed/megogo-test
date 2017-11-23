package com.volodia.megogo_test.utils;

import android.animation.Animator;
import android.view.View;
import android.view.ViewPropertyAnimator;

/**
 * Created by Volodia on 21.11.2017.
 */

public class UiUtils {

    public static void hideView(View view, int duration, boolean withAnim, int finalVisibility) {
        clearAnim(view);
        if (withAnim) {
            fadeAnimation(view, 1, 0, duration, 0, finalVisibility);
        } else {
            view.setVisibility(finalVisibility);
        }
    }

    public static void showView(View view, int duration, boolean withAnim) {
        clearAnim(view);
        if (view.getVisibility() == View.VISIBLE && view.getAlpha() == 1) {
            return;
        }
        view.setVisibility(View.VISIBLE);
        //withAnim = false;
        if (withAnim) {
            fadeAnimation(view, 0, 1, duration, 0, View.VISIBLE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void showView(View view, boolean withAnim) {
        showView(view, 300, withAnim);
    }

    public static void showView(View view) {
        showView(view, 300, true);
    }

    private static void clearAnim(View view) {
        view.animate().cancel();
    }

    public static void fadeAnimation(final View view, final float from, float to, int duration,
                                     int offset, final int finalVisibility) {
        if ((view.getVisibility() == View.INVISIBLE || view.getVisibility() == View.GONE)
                && from == 1) {
            return;
        }

        view.setAlpha(from);
        final ViewPropertyAnimator vpAnimator = view.animate().setDuration(duration).alpha(to).
                setStartDelay(offset).setListener(new SimpleAnimatorListener() {

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(finalVisibility);
                view.setAlpha(1);
                animation.removeAllListeners();
            }

        });

        vpAnimator.start();
    }

    public static void hideView(View view) {
        hideView(view, 300, true);
    }

    public static void hideView(View view, boolean withAnim) {
        hideView(view, 300, withAnim);
    }

    public static void hideView(View view, int duration, boolean withAnim) {
        hideView(view, duration, withAnim, View.INVISIBLE);
    }


}
