package com.github.florent37.kotlin.pleaseanimate.core.alpha

import android.view.View

import com.github.florent37.kotlin.pleaseanimate.core.AnimExpectation

abstract class AlphaAnimExpectation : AnimExpectation() {
    abstract fun getCalculatedAlpha(viewToMove: View): Float?
}
