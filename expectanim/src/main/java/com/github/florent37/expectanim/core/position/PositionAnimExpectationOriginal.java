package com.github.florent37.expectanim.core.position;

import android.view.View;

/**
 * Created by florentchampigny on 17/02/2017.
 */

public class PositionAnimExpectationOriginal extends PositionAnimExpectation {

    public PositionAnimExpectationOriginal() {
        setForTranslationX(true);
        setForTranslationY(true);
    }

    @Override
    public Float getCalculatedValueX(View viewToMove) {
        return 0f;
    }

    @Override
    public Float getCalculatedValueY(View viewToMove) {
        return 0f;
    }
}
