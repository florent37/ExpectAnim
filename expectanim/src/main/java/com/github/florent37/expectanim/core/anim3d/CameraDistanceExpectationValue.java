package com.github.florent37.expectanim.core.anim3d;

import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Christian Ringshofer on 17/02/2017.
 * <p>
 * A container for storing the rotation values for the flip animation
 */
public class CameraDistanceExpectationValue extends CameraDistanceExpectation {

    private final float mCameraDistance;

    /**
     * a new camera distance expectation value
     *
     * @param cameraDistance the cameraDistance in densityPixels to use for the view perspective useful for
     *                       animations around the x-axis or y-axis in 3d space
     */
    public CameraDistanceExpectationValue(float cameraDistance) {
        mCameraDistance = cameraDistance;
    }

    @Override
    @Nullable
    public Float getCalculatedCameraDistance(View viewToMove) {
        return mCameraDistance * viewToMove.getResources().getDisplayMetrics().density;
    }

}
