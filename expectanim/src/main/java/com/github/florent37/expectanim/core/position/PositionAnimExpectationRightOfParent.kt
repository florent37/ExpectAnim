package com.github.florent37.expectanim.core.position

import android.view.View
import android.view.ViewParent

class PositionAnimExpectationRightOfParent : PositionAnimExpectation() {

    init {
        isForPositionX = true
    }

    override fun getCalculatedValueX(viewToMove: View): Float? {
        val viewParent = viewToMove.parent
        if (viewParent is View) {
            val parentView = viewParent as View
            return parentView.width.toFloat() - getMargin(viewToMove) - viewCalculator!!.finalWidthOfView(viewToMove)
        }
        return null
    }

    override fun getCalculatedValueY(viewToMove: View): Float? {
        return null
    }
}
