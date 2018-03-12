package com.github.florent37.expectanim.core.anim3d

import android.view.View

import com.github.florent37.expectanim.core.AnimExpectation

abstract class CameraDistanceExpectation : AnimExpectation() {
    abstract fun getCalculatedCameraDistance(viewToMove: View): Float?
}
