package com.github.florent37.expectanim.core.position;

import android.view.View;

/**
 * Created by florentchampigny on 17/02/2017.
 */

public class PositionAnimExpectationLeftOfParent extends PositionAnimExpectation {

    public PositionAnimExpectationLeftOfParent() {
        setForPositionX(true);
    }

    @Override
    public Float getCalculatedValueX(View viewToMove) {
        return getMargin(viewToMove);
    }

    @Override
    public Float getCalculatedValueY(View viewToMove) {
        return null;
    }
}
