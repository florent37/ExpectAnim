package com.github.florent37.kotlin.pleaseanimate.core.scale

import android.view.View

/**
 * Created by florentchampigny on 17/02/2017.
 */
class ScaleAnimExpectationOriginalScale : ScaleAnimExpectation(null, null) {

    override fun getCalculatedValueScaleX(viewToMove: View): Float? {
        return 1f
    }

    override fun getCalculatedValueScaleY(viewToMove: View): Float? {
        return 1f
    }
}
