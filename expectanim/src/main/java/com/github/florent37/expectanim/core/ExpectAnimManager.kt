package com.github.florent37.expectanim.core

import android.animation.Animator
import android.view.View

import com.github.florent37.expectanim.ViewCalculator

abstract class ExpectAnimManager(
        protected val animExpectations: List<AnimExpectation>,
        protected val viewToMove: View,
        protected val viewCalculator: ViewCalculator) {

    abstract fun getAnimators(): List<Animator>

    abstract fun calculate()

}
