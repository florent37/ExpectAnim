package com.github.florent37.expectanim.core.scale

import android.view.View

import com.github.florent37.expectanim.core.position.PositionAnimExpectation

/**
 * Created by florentchampigny on 20/02/2017.
 */
class ScaleAnimExpectationWidth(private var width: Int, gravityHorizontal: Int?, gravityVertical: Int?) : ScaleAnimExpectation(gravityHorizontal, gravityVertical) {

    override fun getCalculatedValueScaleX(viewToMove: View): Float? {
        if (toDp) {
            this.width = dpToPx(this.width.toFloat(), viewToMove)
        }

        val viewToMoveWidth = viewToMove.width
        return if (this.width == 0 || viewToMoveWidth.toFloat() == 0f) {
            0f
        } else 1f * this.width / viewToMoveWidth
    }

    override fun getCalculatedValueScaleY(viewToMove: View): Float? {
        return if (keepRatio) {
            getCalculatedValueScaleX(viewToMove)
        } else null
    }

}
