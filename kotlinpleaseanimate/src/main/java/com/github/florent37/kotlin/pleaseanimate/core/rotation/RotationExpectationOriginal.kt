package com.github.florent37.kotlin.pleaseanimate.core.rotation

import android.view.View

class RotationExpectationOriginal : RotationExpectation() {

    override fun getCalculatedRotation(viewToMove: View): Float? {
        return 0f
    }

    override fun getCalculatedRotationX(viewToMove: View): Float? {
        return 0f
    }

    override fun getCalculatedRotationY(viewToMove: View): Float? {
        return 0f
    }
}
