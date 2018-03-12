package com.github.florent37.expectanim.core.position

import android.view.View

import com.github.florent37.expectanim.ViewCalculator

abstract class PositionAnimationViewDependant(protected var otherView: View) : PositionAnimExpectation() {

    init {
        viewsDependencies.add(otherView)
    }

}
