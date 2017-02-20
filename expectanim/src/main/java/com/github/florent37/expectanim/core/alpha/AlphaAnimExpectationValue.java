package com.github.florent37.expectanim.core.alpha;

import android.view.View;

/**
 * Created by florentchampigny on 17/02/2017.
 */
public class AlphaAnimExpectationValue extends AlphaAnimExpectation {

    private final float alpha;

    public AlphaAnimExpectationValue(float alpha) {
        this.alpha = alpha;
    }

    @Override
    public Float getCalculatedAlpha(View viewToMove) {
        return alpha;
    }
}
