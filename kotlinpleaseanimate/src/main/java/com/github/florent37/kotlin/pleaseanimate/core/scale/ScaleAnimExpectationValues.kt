package com.github.florent37.kotlin.pleaseanimate.core.scale

import android.view.View

/**
 * Created by florentchampigny on 17/02/2017.
 */
class ScaleAnimExpectationValues(private val scaleX: Float, private val scaleY: Float, gravityHorizontal: Int?, gravityVertical: Int?) : ScaleAnimExpectation(gravityHorizontal, gravityVertical) {

    override fun getCalculatedValueScaleX(viewToMove: View): Float? {
        return scaleX
    }

    override fun getCalculatedValueScaleY(viewToMove: View): Float? {
        return scaleY
    }
}
