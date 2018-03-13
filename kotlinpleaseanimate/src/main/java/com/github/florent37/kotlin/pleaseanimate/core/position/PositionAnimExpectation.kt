package com.github.florent37.kotlin.pleaseanimate.core.position

import android.content.Context
import android.util.TypedValue
import android.view.View
import com.github.florent37.kotlin.pleaseanimate.core.AnimExpectation

abstract class PositionAnimExpectation : AnimExpectation() {

    companion object {
        fun dpToPx(context: Context, dp: Float): Float {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)
        }
    }

    var isForPositionY: Boolean = false
        protected set
    var isForPositionX: Boolean = false
        protected set
    var isForTranslationX: Boolean = false
        protected set
    var isForTranslationY: Boolean = false
        protected set

    var margin: Float? = null
    var marginDp: Float? = null

    abstract fun getCalculatedValueX(viewToMove: View): Float?
    abstract fun getCalculatedValueY(viewToMove: View): Float?

    fun getMargin(view: View): Float {
        return when {
            this.margin != null -> this.margin!!
            this.marginDp != null -> dpToPx(view.context, marginDp!!)
            else -> 0f
        }
    }

}
