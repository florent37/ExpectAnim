package com.github.florent37.expectanim.core.custom

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.widget.TextView

class TextColorAnimExpectation(private val textColor: Int) : CustomAnimExpectation() {

    override fun getAnimator(viewToMove: View): Animator? {
        if (viewToMove is TextView) {
            return ObjectAnimator.ofInt(viewToMove, "textColor", viewToMove.currentTextColor, textColor).apply {
                setEvaluator(ArgbEvaluator())
            }
        } else {
            return null
        }
    }
}
