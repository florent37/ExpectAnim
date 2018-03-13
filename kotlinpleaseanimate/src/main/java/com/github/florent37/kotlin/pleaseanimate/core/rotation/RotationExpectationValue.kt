package com.github.florent37.kotlin.pleaseanimate.core.rotation

import android.view.View

class RotationExpectationValue(private val rotation: Float) : RotationExpectation() {
    override fun getCalculatedRotation(viewToMove: View): Float? {
        return rotation
    }

    override fun getCalculatedRotationX(viewToMove: View): Float? {
        return null
    }

    override fun getCalculatedRotationY(viewToMove: View): Float? {
        return null
    }
}
