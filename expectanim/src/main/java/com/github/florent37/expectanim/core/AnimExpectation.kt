package com.github.florent37.expectanim.core

import android.view.View

import com.github.florent37.expectanim.ViewCalculator

abstract class AnimExpectation {
    var viewCalculator: ViewCalculator? = null

    var viewsDependencies: MutableList<View> =  mutableListOf()
}
