package com.github.florent37.kotlin.pleaseanimate.core.anim3d

import android.view.View

/**
 * Created by Christian Ringshofer on 17/02/2017.
 *
 * A container for storing the rotation values for the flip animation
 */
class CameraDistanceExpectationValue(private val mCameraDistance: Float) : CameraDistanceExpectation() {

    /**
     * a new camera distance expectation value
     *
     * @param cameraDistance the cameraDistance in densityPixels to use for the view perspective useful for
     * animations around the x-axis or y-axis in 3d space
     */

    override fun getCalculatedCameraDistance(viewToMove: View): Float? {
        return mCameraDistance * viewToMove.resources.displayMetrics.density
    }

}
