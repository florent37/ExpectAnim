package com.github.florent37.expectanim.core.scale

import android.view.View

import com.github.florent37.expectanim.GravityScaleHorizontalIntDef
import com.github.florent37.expectanim.GravityScaleVerticalIntDef
import com.github.florent37.expectanim.core.AnimExpectation
import com.github.florent37.expectanim.core.Expectations
import com.github.florent37.expectanim.core.position.PositionAnimExpectation

abstract class ScaleAnimExpectation(gravityHorizontal: Int?, gravityVertical: Int?) : AnimExpectation() {

    var toDp = false
    var keepRatio = false
    var gravityHorizontal: Int? = null
        private set
    var gravityVertical: Int? = null
        private set

    init {
        if (gravityHorizontal != null) {
            this.gravityHorizontal = gravityHorizontal
        }
        if (gravityVertical != null) {
            this.gravityVertical = gravityVertical
        }
    }

    protected fun dpToPx(value: Float, view: View): Int {
        val v = PositionAnimExpectation.dpToPx(view.context, value).toInt()
        toDp = false
        return v
    }

    abstract fun getCalculatedValueScaleX(viewToMove: View): Float?

    abstract fun getCalculatedValueScaleY(viewToMove: View): Float?

    fun withGravity(@GravityScaleHorizontalIntDef gravityHorizontal: Int?, @GravityScaleVerticalIntDef gravityVertical: Int?): ScaleAnimExpectation {
        if (gravityHorizontal != null) {
            this.gravityHorizontal = gravityHorizontal
        }
        if (gravityVertical != null) {
            this.gravityVertical = gravityVertical
        }
        return this
    }
}
