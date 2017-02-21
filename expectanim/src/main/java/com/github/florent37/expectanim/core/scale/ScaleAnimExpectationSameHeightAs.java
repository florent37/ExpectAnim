package com.github.florent37.expectanim.core.scale;

import android.view.View;

/**
 * Created by florentchampigny on 20/02/2017.
 */
public class ScaleAnimExpectationSameHeightAs extends ScaleAnimExpectationViewDependant {

    public ScaleAnimExpectationSameHeightAs(View otherView, Integer gravityHorizontal, Integer gravityVertical) {
        super(otherView, gravityHorizontal, gravityVertical);
    }

    @Override
    public Float getCalculatedValueScaleX(View viewToMove) {
        if(keepRatio){
            return getCalculatedValueScaleY(viewToMove);
        }
        return null;
    }

    @Override
    public Float getCalculatedValueScaleY(View viewToMove) {
        final int viewToMoveHeight = viewToMove.getHeight();

        final float otherViewHeight = viewCalculator.finalHeightOfView(otherView);

        if (otherViewHeight == 0 || viewToMoveHeight == 0f) {
            return 0f;
        }
        return otherViewHeight / viewToMoveHeight;
    }
}
