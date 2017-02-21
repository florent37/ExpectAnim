package com.github.florent37.expectanim.core.custom;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.TextView;

/**
 * Created by florentchampigny on 21/02/2017.
 */

public class TextColorAnimExpectation extends CustomAnimExpectation {

    private final int textColor;

    public TextColorAnimExpectation(int textColor) {
        this.textColor = textColor;
    }


    @Override
    public Animator getAnimator(View viewToMove) {
        if (viewToMove instanceof TextView) {
            final ObjectAnimator objectAnimator = ObjectAnimator.ofInt(viewToMove, "textColor", ((TextView) viewToMove).getCurrentTextColor(), textColor);
            objectAnimator.setEvaluator(new ArgbEvaluator());
            return objectAnimator;
        } else {
            return null;
        }
    }
}
