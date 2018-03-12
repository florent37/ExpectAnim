package com.github.florent37.expectanim.core.custom

import android.animation.Animator
import android.view.View

import com.github.florent37.expectanim.ViewCalculator
import com.github.florent37.expectanim.core.AnimExpectation
import com.github.florent37.expectanim.core.ExpectAnimManager

import java.util.ArrayList

class ExpectAnimCustomManager(animExpectations: List<AnimExpectation>, viewToMove: View, viewCalculator: ViewCalculator) : ExpectAnimManager(animExpectations, viewToMove, viewCalculator) {

    val animations: MutableList<Animator> = mutableListOf()

    override fun calculate() {
        animExpectations.forEach { animExpectation ->
            if (animExpectation is CustomAnimExpectation) {
                animExpectation.viewCalculator = viewCalculator
                animExpectation.getAnimator(viewToMove)?.let { animator ->
                    animations.add(animator)
                }
            }
        }
    }

    override fun getAnimators(): List<Animator> {
        return animations
    }
}
