package com.github.florent37.expectanim.core.rotation;

import android.view.View;

/**
 * Created by florentchampigny on 17/02/2017.
 */
public class RotationExpectationOriginal extends RotationExpectation {

    @Override
    public Float getCalculatedRotation(View viewToMove) {
        return 0f;
    }

    @Override
    public Float getCalculatedRotationX(View viewToMove) {
        return 0f;
    }

    @Override
    public Float getCalculatedRotationY(View viewToMove) {
        return 0f;
    }
}
