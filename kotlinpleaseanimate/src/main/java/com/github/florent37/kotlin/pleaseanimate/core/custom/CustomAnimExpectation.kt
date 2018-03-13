package com.github.florent37.kotlin.pleaseanimate.core.custom

import android.animation.Animator
import android.view.View

import com.github.florent37.kotlin.pleaseanimate.core.AnimExpectation

abstract class CustomAnimExpectation : AnimExpectation() {
    abstract fun getAnimator(viewToMove: View): Animator?
}
