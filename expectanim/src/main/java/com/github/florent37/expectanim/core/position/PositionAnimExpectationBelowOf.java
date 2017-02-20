package com.github.florent37.expectanim.core.position;

import android.view.View;

/**
 * Created by florentchampigny on 17/02/2017.
 */

public class PositionAnimExpectationBelowOf extends PositionAnimationViewDependant {

    public PositionAnimExpectationBelowOf(View otherView) {
        super(otherView);

        setForPositionY(true);
    }

    @Override
    public Float getCalculatedValueX(View viewToMove) {
        return null;
    }

    @Override
    public Float getCalculatedValueY(View viewToMove) {
        return viewCalculator.finalPositionBottomOfView(otherView) + getMargin(viewToMove);
    }
}
