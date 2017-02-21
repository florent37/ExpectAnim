package com.github.florent37.expectanim.core.scale;

import android.view.View;

import com.github.florent37.expectanim.core.position.PositionAnimExpectation;

/**
 * Created by florentchampigny on 20/02/2017.
 */
public class ScaleAnimExpectationWidth extends ScaleAnimExpectation {
    private int width;

    public ScaleAnimExpectationWidth(int width, Integer gravityHorizontal, Integer gravityVertical) {
        super(gravityHorizontal, gravityVertical);
        this.width = width;
    }

    @Override
    public Float getCalculatedValueScaleX(View viewToMove) {
        if(toDp){
            this.width = dpToPx(this.width, viewToMove);
        }

        final int viewToMoveWidth = viewToMove.getWidth();
        if (this.width == 0 || viewToMoveWidth == 0f) {
            return 0f;
        }
        return 1f * this.width / viewToMoveWidth;
    }

    @Override
    public Float getCalculatedValueScaleY(View viewToMove) {
        if(keepRatio){
            return getCalculatedValueScaleX(viewToMove);
        }
        return null;
    }

}
