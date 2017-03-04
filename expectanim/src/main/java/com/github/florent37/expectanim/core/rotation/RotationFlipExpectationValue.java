package com.github.florent37.expectanim.core.rotation;

import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Christian Ringshofer on 17/02/2017.
 * <p>
 * A container for storing the rotation values for the flip animation
 */
public class RotationFlipExpectationValue extends RotationExpectation {

    private final float mRotationX, mRotationY;

    /**
     * a new flip expectation value which can be used to flip the view on the x- and y- axis
     * at the same time
     *
     * @param rotationX the x-rotation value around the x axis in degrees
     * @param rotationY the y-rotation value around the y axis in degrees
     */
    public RotationFlipExpectationValue(float rotationX, float rotationY) {
        mRotationX = rotationX;
        mRotationY = rotationY;
    }

    @Override
    public Float getCalculatedRotation(View viewToMove) {
        return null;
    }

    @Override
    @Nullable
    public Float getCalculatedRotationX(View viewToMove) {
        return mRotationX;
    }

    @Override
    @Nullable
    public Float getCalculatedRotationY(View viewToMove) {
        return mRotationY;
    }

}
