package com.github.florent37.expectanim.core.position;

import android.view.View;

/**
 * Created by florentchampigny on 17/02/2017.
 */

public class PositionAnimExpectationAlignRight extends PositionAnimationViewDependant {

    public PositionAnimExpectationAlignRight(View otherView) {
        super(otherView);

        setForPositionX(true);
    }

    @Override
    public Float getCalculatedValueX(View viewToMove) {
        return viewCalculator.finalPositionRightOfView(otherView) - getMargin(viewToMove) - viewToMove.getWidth();
    }

    @Override
    public Float getCalculatedValueY(View viewToMove) {
        return null;
    }
}
