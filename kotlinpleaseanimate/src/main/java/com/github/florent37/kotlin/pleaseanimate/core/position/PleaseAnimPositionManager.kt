package com.github.florent37.kotlin.pleaseanimate.core.position

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View

import com.github.florent37.kotlin.pleaseanimate.core.AnimExpectation
import com.github.florent37.kotlin.pleaseanimate.ViewCalculator
import com.github.florent37.kotlin.pleaseanimate.core.PleaseAnimManager

class PleaseAnimPositionManager(animExpectations: List<AnimExpectation>, viewToMove: View, viewCalculator: ViewCalculator) : PleaseAnimManager(animExpectations, viewToMove, viewCalculator) {

    private var positionX: Float? = null
    private var positionY: Float? = null

    private var translationX: Float? = null
    private var translationY: Float? = null

    fun getPositionX(): Float? {
        return if (translationX != null) {
            viewToMove.x + translationX!!
        } else {
            positionX
        }
    }

    fun getPositionY(): Float? {
        return if (translationX != null) {
            viewToMove.y + translationY!!
        } else {
            positionY
        }
    }

    override fun calculate() {
        animExpectations.forEach { animExpectation ->
            if (animExpectation is PositionAnimExpectation) {

                animExpectation.viewCalculator = viewCalculator

                animExpectation.getCalculatedValueX(viewToMove)?.let { calculatedValueX ->
                    if (animExpectation.isForPositionX) {
                        positionX = calculatedValueX
                    }
                    if (animExpectation.isForTranslationX) {
                        translationX = calculatedValueX
                    }
                }

                animExpectation.getCalculatedValueY(viewToMove)?.let { calculatedValueY ->
                    if (animExpectation.isForPositionY) {
                        positionY = calculatedValueY
                    }
                    if (animExpectation.isForTranslationY) {
                        translationY = calculatedValueY
                    }
                }
            }
        }
    }

    override fun getAnimators(): List<Animator> {
        val animations = mutableListOf<Animator>()

        if (positionX != null) {
            animations.add(ObjectAnimator.ofFloat(viewToMove, View.X, viewCalculator.finalPositionLeftOfView(viewToMove, true)))
        }

        if (positionY != null) {
            animations.add(ObjectAnimator.ofFloat(viewToMove, View.Y, viewCalculator.finalPositionTopOfView(viewToMove, true)))
        }

        translationX?.let {
            animations.add(ObjectAnimator.ofFloat(viewToMove, View.TRANSLATION_X, it))
        }

        translationY?.let {
            animations.add(ObjectAnimator.ofFloat(viewToMove, View.TRANSLATION_Y, it))
        }

        return animations
    }
}
