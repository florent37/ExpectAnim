package com.github.florent37.expectanim.core.custom

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.drawable.Drawable
import android.support.v4.graphics.drawable.DrawableCompat
import android.util.Log
import android.view.View
import android.widget.TextView

class ViewBackgroundAlphaAnimExpectation(private val alpha: Float) : CustomAnimExpectation() {

    override fun getAnimator(viewToMove: View): Animator? {
        viewToMove.background?.let { background ->
            return ValueAnimator.ofFloat(1f, alpha).apply {
                addUpdateListener { animation ->
                    val value = animation.animatedValue as Float
                    val alpha = (255 * value).toInt()
                    background.alpha = alpha
                }
            }
        }
        return null
    }
}
