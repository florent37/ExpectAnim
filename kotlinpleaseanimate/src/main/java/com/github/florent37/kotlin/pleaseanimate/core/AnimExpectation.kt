package com.github.florent37.kotlin.pleaseanimate.core

import android.view.View

import com.github.florent37.kotlin.pleaseanimate.ViewCalculator

abstract class AnimExpectation {
    var viewCalculator: ViewCalculator? = null

    var viewsDependencies: MutableList<View> =  mutableListOf()
}
