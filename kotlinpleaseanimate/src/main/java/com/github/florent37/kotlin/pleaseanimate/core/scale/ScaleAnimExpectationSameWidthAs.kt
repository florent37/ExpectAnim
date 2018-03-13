package com.github.florent37.kotlin.pleaseanimate.core.scale

import android.view.View

/**
 * Created by florentchampigny on 20/02/2017.
 */
class ScaleAnimExpectationSameWidthAs(otherView: View, gravityHorizontal: Int?, gravityVertical: Int?) : ScaleAnimExpectationViewDependant(otherView, gravityHorizontal, gravityVertical) {

    override fun getCalculatedValueScaleX(viewToMove: View): Float? {
        val viewToMoveWidth = viewToMove.width

        val otherViewWidth = viewCalculator!!.finalWidthOfView(otherView)

        return if (otherViewWidth == 0f || viewToMoveWidth.toFloat() == 0f) {
            0f
        } else otherViewWidth / viewToMoveWidth
    }

    override fun getCalculatedValueScaleY(viewToMove: View): Float? {
        return if (keepRatio) {
            getCalculatedValueScaleX(viewToMove)
        } else null
    }
}
