package com.github.florent37.kotlin.pleaseanimate.core.custom

import android.animation.Animator
import android.animation.ValueAnimator
import android.view.View

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
