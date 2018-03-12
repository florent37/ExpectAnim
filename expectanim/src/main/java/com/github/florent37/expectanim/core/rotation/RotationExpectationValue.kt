package com.github.florent37.expectanim.core.rotation

import android.view.View

import com.github.florent37.expectanim.core.alpha.AlphaAnimExpectation

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
