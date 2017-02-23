package com.github.florent37.expectanim.core.rotation;

import android.view.View;

import com.github.florent37.expectanim.core.alpha.AlphaAnimExpectation;

/**
 * Created by florentchampigny on 17/02/2017.
 */
public class RotationExpectationValue extends RotationExpectation {

    private final float rotation;

    public RotationExpectationValue(float rotation) {
        this.rotation = rotation;
    }

    @Override
    public Float getCalculatedRotation(View viewToMove) {
        return rotation;
    }
}
