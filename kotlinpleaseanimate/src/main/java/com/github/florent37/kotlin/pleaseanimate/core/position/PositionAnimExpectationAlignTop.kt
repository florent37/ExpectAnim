package com.github.florent37.kotlin.pleaseanimate.core.position

import android.view.View

class PositionAnimExpectationAlignTop(otherView: View) : PositionAnimationViewDependant(otherView) {

    init {
        isForPositionY = true
    }

    override fun getCalculatedValueX(viewToMove: View): Float? {
        return null
    }

    override fun getCalculatedValueY(viewToMove: View): Float? {
        return viewCalculator!!.finalPositionTopOfView(otherView) + getMargin(viewToMove)
    }
}
