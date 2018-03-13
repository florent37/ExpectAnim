package com.github.florent37.kotlin.pleaseanimate

import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator

fun please(duration: Long = 300L, interpolator: Interpolator = LinearInterpolator(), block: (PleaseAnim.() -> Unit)) : PleaseAnim {
    val expectAnim = PleaseAnim()
    expectAnim.setDuration(duration)
    expectAnim.setInterpolator(interpolator)
    block.invoke(expectAnim)
    return expectAnim
}