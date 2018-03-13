package com.github.florent37.kotlin.pleaseanimate

import android.view.View

import java.lang.Math.abs
import java.lang.Math.cos
import java.lang.Math.sin

class ViewCalculator {
    private val expectationForView: MutableMap<View, ViewExpectation> = mutableMapOf()

    private val enableRotation = true
    private val enableScale = true

    fun setExpectationForView(view: View, viewExpectation: ViewExpectation) {
        expectationForView.put(view, viewExpectation)
    }

    fun wasCalculated(viewExpectation: ViewExpectation) {
        //no-op
    }

    @JvmOverloads
    fun finalPositionLeftOfView(view: View, itsMe: Boolean = false): Float {
        var finalX: Float? = null

        val viewExpectation = expectationForView[view]
        if (viewExpectation != null) {
            val futurPositionX = viewExpectation.getFuturPositionX()
            if (futurPositionX != null) {
                finalX = futurPositionX
            }
        }

        if (finalX == null) {
            finalX = view.x
        }

        if (itsMe) {
            finalX -= (view.width - this.finalWidthOfView(view)) / 2f
        }

        return finalX
    }

    fun finalPositionRightOfView(view: View): Float {
        return finalPositionLeftOfView(view) + finalWidthOfView(view)
    }

    @JvmOverloads
    fun finalPositionTopOfView(view: View, itsMe: Boolean = false): Float {
        var finalTop: Float? = null

        val viewExpectation = expectationForView[view]
        if (viewExpectation != null) {
            val futurPositionY = viewExpectation.getFuturPositionY()
            if (futurPositionY != null) {
                finalTop = futurPositionY
            }
        }

        if (finalTop == null) {
            finalTop = 1f * view.top
        }

        if (itsMe) {
            finalTop -= (view.height - this.finalHeightOfView(view)) / 2f
        }

        return finalTop
    }

    fun finalPositionBottomOfView(view: View): Float {
        return finalPositionTopOfView(view) + finalHeightOfView(view)
    }

    fun finalCenterXOfView(view: View): Float {
        return if (expectationForView.containsKey(view)) {
            expectationForView[view]!!.getFuturPositionX()!! + finalWidthOfView(view) / 2f
        } else {
            view.left + view.width / 2f
        }
    }

    fun finalCenterYOfView(view: View): Float {
        return if (expectationForView.containsKey(view)) {
            expectationForView[view]!!.getFuturPositionY()!! + finalHeightOfView(view) / 2f
        } else {
            view.top + view.height / 2f
        }
    }

    fun finalWidthOfView(view: View): Float {
        var with = view.width.toFloat()
        if (expectationForView.containsKey(view)) {
            val viewExpectation = expectationForView[view]

            with = widthScaled(viewExpectation!!, view, with)

            if (enableRotation) {
                with = widthRotated(viewExpectation, view, with)
            }
        }
        return with
    }

    private fun widthScaled(viewExpectation: ViewExpectation, view: View, width: Float): Float {
        var w = width
        val scaleX = viewExpectation.getWillHasScaleX()
        if (scaleX != 1f) {
            w = scaleX * width // + view.getPivotX() * scaleX;
        }
        return w
    }

    private fun widthRotated(viewExpectation: ViewExpectation, view: View, width: Float): Float {
        var w = width
        val willHaveRotationX = viewExpectation.getWillHaveRotation()
        if (willHaveRotationX != null) {
            val radians = Math.toRadians((90f - willHaveRotationX).toDouble())
            w = (abs(width * sin(radians)) + abs(heightScaled(viewExpectation, view, view.height.toFloat()) * cos(radians))).toFloat()
        }
        return w
    }

    fun finalHeightOfView(view: View): Float {
        var height = view.height.toFloat()
        if (expectationForView.containsKey(view)) {
            val viewExpectation = expectationForView[view]

            height = heightScaled(viewExpectation!!, view, height)

            if (enableRotation) {
                height = heightRotated(viewExpectation, view, height)
            }
        }
        return height
    }

    private fun heightScaled(viewExpectation: ViewExpectation, view: View, height: Float): Float {
        var h = height
        val scaleY = viewExpectation.getWillHasScaleY()
        if (scaleY != 1f) {
            h = scaleY * height // + view.getPivotY() * scaleY;
        }
        return h
    }

    private fun heightRotated(viewExpectation: ViewExpectation, view: View, height: Float): Float {
        var h = height
        val willHaveRotationX = viewExpectation.getWillHaveRotation()
        if (willHaveRotationX != null) {
            val radians = Math.toRadians(willHaveRotationX.toDouble())
            h = (abs(height * cos(radians)) + abs(widthScaled(viewExpectation, view, view.width.toFloat()) * sin(radians))).toFloat()
        }
        return h
    }


}
