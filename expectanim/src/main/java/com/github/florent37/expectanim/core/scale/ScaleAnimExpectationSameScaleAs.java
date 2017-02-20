package com.github.florent37.expectanim.core.scale;

import android.view.View;

/**
 * Created by florentchampigny on 17/02/2017.
 */
public class ScaleAnimExpectationSameScaleAs extends ScaleAnimExpectationViewDependant {

    public ScaleAnimExpectationSameScaleAs(View otherView) {
        super(otherView, null, null);
    }

    @Override
    public Float getCalculatedValueScaleX(View viewToMove) {
        return otherView.getScaleX();
    }

    @Override
    public Float getCalculatedValueScaleY(View viewToMove) {
        return otherView.getScaleY();
    }
}
