package com.github.florent37.expectanim.core.position;

import android.view.View;

/**
 * Created by florentchampigny on 17/02/2017.
 */

public class PositionAnimExpectationAboveOf extends PositionAnimationViewDependant {

    public PositionAnimExpectationAboveOf(View otherView) {
        super(otherView);

        setForPositionY(true);
    }

    @Override
    public Float getCalculatedValueX(View viewToMove) {
        return null;
    }

    @Override
    public Float getCalculatedValueY(View viewToMove) {
        return viewCalculator.finalPositionTopOfView(otherView) - getMargin(viewToMove) - viewToMove.getHeight();
    }
}
