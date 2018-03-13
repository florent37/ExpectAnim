package com.github.florent37.kotlin.pleaseanimate.core.anim3d

import android.view.View

import com.github.florent37.kotlin.pleaseanimate.core.AnimExpectation

abstract class CameraDistanceExpectation : AnimExpectation() {
    abstract fun getCalculatedCameraDistance(viewToMove: View): Float?
}
