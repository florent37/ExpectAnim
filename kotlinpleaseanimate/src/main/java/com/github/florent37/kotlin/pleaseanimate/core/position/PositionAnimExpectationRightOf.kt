package com.github.florent37.kotlin.pleaseanimate.core.position

import android.view.View

class PositionAnimExpectationRightOf(otherView: View) : PositionAnimationViewDependant(otherView) {

    init {
        isForPositionX = true
    }

    override fun getCalculatedValueX(viewToMove: View): Float? {
        return viewCalculator!!.finalPositionRightOfView(otherView) + getMargin(viewToMove)
    }

    override fun getCalculatedValueY(viewToMove: View): Float? {
        return null
    }
}
