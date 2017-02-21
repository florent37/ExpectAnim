package com.github.florent37.expectanim.core.custom;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by florentchampigny on 21/02/2017.
 */

public class ViewBackgroundAlphaAnimExpectation extends CustomAnimExpectation {

    private final float alpha;

    public ViewBackgroundAlphaAnimExpectation(float alpha) {
        this.alpha = alpha;
    }


    @Override
    public Animator getAnimator(View viewToMove) {
        final Drawable background = viewToMove.getBackground();
        if (background != null) {
            final ValueAnimator valueAnimator = ValueAnimator.ofFloat(1f, alpha);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    final float value = (float)animation.getAnimatedValue();
                    final int alpha = (int)(255*value);
                    background.setAlpha(alpha);
                }
            });
            return valueAnimator;
        }
        return null;
    }
}
