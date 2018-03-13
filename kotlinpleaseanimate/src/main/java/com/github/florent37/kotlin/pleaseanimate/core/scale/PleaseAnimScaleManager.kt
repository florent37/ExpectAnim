package com.github.florent37.kotlin.pleaseanimate.core.scale

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.Gravity
import android.view.View
import com.github.florent37.kotlin.pleaseanimate.ViewCalculator
import com.github.florent37.kotlin.pleaseanimate.core.AnimExpectation

class PleaseAnimScaleManager(
        private val animExpectations: List<AnimExpectation>,
        private val viewToMove: View?,
        private val viewCalculator: ViewCalculator) {

    var scaleX: Float? = null
        private set
    var scaleY: Float? = null
        private set

    private var pivotX: Float? = null
    private var pivotY: Float? = null

    val animators: List<Animator>
        get() {
            val animations = mutableListOf<Animator>()

            viewToMove?.let { viewToMove ->
                pivotX?.let {
                    viewToMove.pivotX = it
                }
                pivotY?.let {
                    viewToMove.pivotY = it
                }

                scaleX?.let {
                    animations.add(ObjectAnimator.ofFloat(viewToMove, View.SCALE_X, it))
                }

                scaleY?.let {
                    animations.add(ObjectAnimator.ofFloat(viewToMove, View.SCALE_Y, it))
                }
            }

            return animations
        }

    fun calculate() {
        viewToMove?.let { viewToMove ->

            animExpectations.forEach { animExpectation ->
                if (animExpectation is ScaleAnimExpectation) {

                    animExpectation.viewCalculator = viewCalculator

                    animExpectation.getCalculatedValueScaleX(viewToMove)?.let {
                        this.scaleX = it
                    }
                    animExpectation.getCalculatedValueScaleY(viewToMove)?.let {
                        this.scaleY = it
                    }

                    animExpectation.gravityHorizontal?.let { gravityHorizontal ->
                        when (gravityHorizontal) {
                            Gravity.LEFT, Gravity.START -> pivotX = viewToMove.left.toFloat()
                            Gravity.RIGHT, Gravity.END -> pivotX = viewToMove.right.toFloat()
                            Gravity.CENTER_HORIZONTAL, Gravity.CENTER -> pivotX = viewToMove.left + viewToMove.width / 2f
                        }
                    }

                    animExpectation.gravityVertical?.let { gravityVertical ->
                        when (gravityVertical) {
                            Gravity.TOP -> pivotY = viewToMove.top.toFloat()
                            Gravity.BOTTOM -> pivotY = viewToMove.bottom.toFloat()
                            Gravity.CENTER_VERTICAL, Gravity.CENTER -> pivotY = viewToMove.top + viewToMove.height / 2f
                        }
                    }

                }
            }
        }
    }
}
