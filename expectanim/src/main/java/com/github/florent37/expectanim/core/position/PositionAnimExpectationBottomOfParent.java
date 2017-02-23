package com.github.florent37.expectanim.core.position;

import android.view.View;
import android.view.ViewParent;

/**
 * Created by florentchampigny on 17/02/2017.
 */

public class PositionAnimExpectationBottomOfParent extends PositionAnimExpectation {

    public PositionAnimExpectationBottomOfParent() {
        setForPositionY(true);
    }

    @Override
    public Float getCalculatedValueX(View viewToMove) {
        return null;
    }

    @Override
    public Float getCalculatedValueY(View viewToMove) {
        final ViewParent viewParent = viewToMove.getParent();
        if((viewParent instanceof View)){
            final View parentView = (View)viewParent;
            return parentView.getHeight() - getMargin(viewToMove) - viewCalculator.finalHeightOfView(viewToMove);
        }
        return null;
    }
}
