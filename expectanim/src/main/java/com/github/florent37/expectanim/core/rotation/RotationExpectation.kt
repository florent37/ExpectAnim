package com.github.florent37.expectanim.core.rotation

import android.view.View

import com.github.florent37.expectanim.core.AnimExpectation
import com.github.florent37.expectanim.core.alpha.AlphaAnimExpectation

abstract class RotationExpectation : AnimExpectation() {
    abstract fun getCalculatedRotation(viewToMove: View): Float?
    abstract fun getCalculatedRotationX(viewToMove: View): Float?
    abstract fun getCalculatedRotationY(viewToMove: View): Float?
}
