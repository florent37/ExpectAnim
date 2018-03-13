package com.github.florent37.kotlin.pleaseanimate.core.position

import android.view.View

abstract class PositionAnimationViewDependant(protected var otherView: View) : PositionAnimExpectation() {

    init {
        viewsDependencies.add(otherView)
    }

}
