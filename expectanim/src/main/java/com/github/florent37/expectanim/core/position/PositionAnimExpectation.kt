package com.github.florent37.expectanim.core.position

import android.content.Context
import android.support.annotation.DimenRes
import android.util.TypedValue
import android.view.View

import com.github.florent37.expectanim.core.AnimExpectation

import java.util.ArrayList

abstract class PositionAnimExpectation : AnimExpectation() {

    var isForPositionY: Boolean = false
        protected set
    var isForPositionX: Boolean = false
        protected set
    var isForTranslationX: Boolean = false
        protected set
    var isForTranslationY: Boolean = false
        protected set

    var margin: Float? = null
    var marginRes: Int? = null
    var marginDp: Float? = null

    abstract fun getCalculatedValueX(viewToMove: View): Float?
    abstract fun getCalculatedValueY(viewToMove: View): Float?

    fun getMargin(view: View): Float {
        return when {
            this.margin != null -> this.margin!!
            marginRes != null -> view.context.resources.getDimension(marginRes!!)
            marginDp != null -> dpToPx(view.context, marginDp!!)
            else -> 0f
        }
    }

    companion object {
        fun dpToPx(context: Context, dp: Float): Float {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)
        }
    }

}
