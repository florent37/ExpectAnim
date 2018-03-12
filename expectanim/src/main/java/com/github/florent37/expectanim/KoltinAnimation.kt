package com.github.florent37.expectanim

import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator

fun animation(duration: Long = 300L, interpolator: Interpolator = LinearInterpolator(), block: (ExpectAnim.() -> Unit)) : ExpectAnim {
    val expectAnim = ExpectAnim()
    expectAnim.setDuration(duration)
    expectAnim.setInterpolator(interpolator)
    block.invoke(expectAnim)
    return expectAnim
}