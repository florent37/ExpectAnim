package com.github.florent37.expectanim.core.position

import android.view.View
import android.view.ViewParent

class PositionAnimExpectationBottomOfParent : PositionAnimExpectation() {

    init {
        isForPositionY = true
    }

    override fun getCalculatedValueX(viewToMove: View): Float? {
        return null
    }

    override fun getCalculatedValueY(viewToMove: View): Float? {
        val viewParent = viewToMove.parent
        if (viewParent is View) {
            val parentView = viewParent as View
            return parentView.height.toFloat() - getMargin(viewToMove) - viewCalculator!!.finalHeightOfView(viewToMove)
        }
        return null
    }
}
