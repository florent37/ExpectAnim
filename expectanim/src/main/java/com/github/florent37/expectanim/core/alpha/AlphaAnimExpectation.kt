package com.github.florent37.expectanim.core.alpha

import android.view.View

import com.github.florent37.expectanim.core.AnimExpectation

abstract class AlphaAnimExpectation : AnimExpectation() {
    abstract fun getCalculatedAlpha(viewToMove: View): Float?
}
