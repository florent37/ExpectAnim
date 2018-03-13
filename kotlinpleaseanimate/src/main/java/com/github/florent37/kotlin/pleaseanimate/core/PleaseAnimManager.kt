package com.github.florent37.kotlin.pleaseanimate.core

import android.animation.Animator
import android.view.View

import com.github.florent37.kotlin.pleaseanimate.ViewCalculator

abstract class PleaseAnimManager(
        protected val animExpectations: List<AnimExpectation>,
        protected val viewToMove: View,
        protected val viewCalculator: ViewCalculator) {

    abstract fun getAnimators(): List<Animator>

    abstract fun calculate()

}
