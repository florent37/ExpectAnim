package com.github.florent37.expectanim.core.position;

import android.view.View;

/**
 * Created by florentchampigny on 17/02/2017.
 */

public class PositionAnimExpectationSameCenterAs extends PositionAnimationViewDependant {

    private final boolean horizontal;
    private final boolean vertical;

    public PositionAnimExpectationSameCenterAs(View otherView, boolean horizontal, boolean vertical) {
        super(otherView);
        setForPositionX(true);
        setForPositionY(true);
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    @Override
    public Float getCalculatedValueX(View viewToMove) {
        if(horizontal) {

            final float x = viewCalculator.finalPositionLeftOfView(otherView);
            final float myWidth = viewToMove.getWidth() / 2f;
            final float hisWidth = viewCalculator.finalWidthOfView(otherView) / 2f;

            if (myWidth > hisWidth) {
                return x - myWidth + hisWidth;
            } else {
                return x - hisWidth + myWidth;
            }
        } else return null;
    }

    @Override
    public Float getCalculatedValueY(View viewToMove) {
        if(vertical) {

            final float y = viewCalculator.finalPositionTopOfView(otherView);
            final float myHeight = viewToMove.getHeight() / 2f;
            final float hisHeight = viewCalculator.finalHeightOfView(otherView) / 2f;

            if (myHeight > hisHeight) {
                return y + myHeight - hisHeight;
            } else {
                return y + hisHeight - myHeight;
            }

        } else return null;
    }

}
