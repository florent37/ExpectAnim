package com.github.florent37.kotlin.pleaseanimate.core.position

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.WindowManager

class PositionAnimExpectationOutOfScreen(private val gravities: IntArray) : PositionAnimExpectation() {

    private var windowManager: WindowManager? = null

    init {
        isForPositionX = true
        isForPositionY = true
    }

    private operator fun contains(gravity: Int): Boolean {
        return gravities.contains(gravity)
    }

    override fun getCalculatedValueX(viewToMove: View): Float? {
        if (contains(Gravity.RIGHT) || contains(Gravity.END)) {
            if (windowManager == null) {
                windowManager = viewToMove.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            }
            return windowManager!!.defaultDisplay.width.toFloat()
        } else return if (contains(Gravity.LEFT) || contains(Gravity.START)) {
            -1f * viewToMove.width
        } else
            null
    }

    override fun getCalculatedValueY(viewToMove: View): Float? {
        if (contains(Gravity.BOTTOM)) {
            if (windowManager == null) {
                windowManager = viewToMove.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            }
            return windowManager!!.defaultDisplay.height.toFloat()
        } else return if (contains(Gravity.TOP)) {
            -1f * viewToMove.height
        } else
            null
    }
}
