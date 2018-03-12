package com.github.florent37.expectanim.core

import android.view.View

import com.github.florent37.expectanim.ViewCalculator

import java.util.ArrayList

abstract class AnimExpectation {
    var viewCalculator: ViewCalculator? = null

    var viewsDependencies: List<View> =  mutableListOf()
}
