package com.github.florent37.kotlin.pleaseanimate.core.position

import android.view.View

class PositionAnimExpectationSameCenterAs(otherView: View, private val horizontal: Boolean, private val vertical: Boolean) : PositionAnimationViewDependant(otherView) {

    init {
        isForPositionX = true
        isForPositionY = true
    }

    override fun getCalculatedValueX(viewToMove: View): Float? {
        if (horizontal) {
            val x = viewCalculator!!.finalPositionLeftOfView(otherView)
            val myWidth = viewToMove.width / 2f
            val hisWidth = viewCalculator!!.finalWidthOfView(otherView) / 2f

            return if (myWidth > hisWidth) {
                x - myWidth + hisWidth
            } else {
                x - hisWidth + myWidth
            }
        } else
            return null
    }

    override fun getCalculatedValueY(viewToMove: View): Float? {
        if (vertical) {
            val y = viewCalculator!!.finalPositionTopOfView(otherView)
            val myHeight = viewToMove.height / 2f
            val hisHeight = viewCalculator!!.finalHeightOfView(otherView) / 2f

            return if (myHeight > hisHeight) {
                y + myHeight - hisHeight
            } else {
                y + hisHeight - myHeight
            }
        } else
            return null
    }

}
