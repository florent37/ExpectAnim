package com.github.florent37.kotlin.pleaseanimate.core.rotation

import android.view.View

import com.github.florent37.kotlin.pleaseanimate.core.AnimExpectation

abstract class RotationExpectation : AnimExpectation() {
    abstract fun getCalculatedRotation(viewToMove: View): Float?
    abstract fun getCalculatedRotationX(viewToMove: View): Float?
    abstract fun getCalculatedRotationY(viewToMove: View): Float?
}
