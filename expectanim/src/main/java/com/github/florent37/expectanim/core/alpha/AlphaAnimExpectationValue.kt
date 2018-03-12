package com.github.florent37.expectanim.core.alpha

import android.view.View

class AlphaAnimExpectationValue(private val alpha: Float) : AlphaAnimExpectation() {
    override fun getCalculatedAlpha(viewToMove: View): Float? {
        return alpha
    }
}
