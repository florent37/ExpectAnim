package com.github.florent37.kotlin.pleaseanimate

import android.animation.Animator
import android.view.View
import com.github.florent37.kotlin.pleaseanimate.core.AnimExpectation
import com.github.florent37.kotlin.pleaseanimate.core.Expectations
import com.github.florent37.kotlin.pleaseanimate.core.alpha.PleaseAnimAlphaManager
import com.github.florent37.kotlin.pleaseanimate.core.anim3d.PleaseAnimCameraDistanceManager
import com.github.florent37.kotlin.pleaseanimate.core.custom.PleaseAnimCustomManager
import com.github.florent37.kotlin.pleaseanimate.core.position.PleaseAnimPositionManager
import com.github.florent37.kotlin.pleaseanimate.core.rotation.PleaseAnimRotationManager
import com.github.florent37.kotlin.pleaseanimate.core.scale.PleaseAnimScaleManager

class ViewExpectation internal constructor(private val expectAnim: PleaseAnim, internal val viewToMove: View) {
    private val dependencies: MutableList<View> = mutableListOf()
    private val animations: MutableList<Animator> = mutableListOf()
    internal val animExpectations: MutableList<AnimExpectation> = mutableListOf()

    private var willHasScaleX: Float? = null
    private var willHasScaleY: Float? = null

    private var willHasPositionX: Float? = null
    private var willHasPositionY: Float? = null

    private var willHasRotationX: Float? = null

    private var willHaveRotationX: Float? = null
    private var willHaveRotationY: Float? = null
    private var willHaveCameraDistance: Float? = null

    private fun calculatePosition(viewCalculator: ViewCalculator) {
        val manager = PleaseAnimPositionManager(animExpectations, viewToMove, viewCalculator)
        manager.calculate()
        willHasPositionX = manager.getPositionX();
        willHasPositionY = manager.getPositionY();
        animations.addAll(manager.getAnimators())
    }

    private fun calculateScale(viewCalculator: ViewCalculator) {
        val manager = PleaseAnimScaleManager(animExpectations, viewToMove, viewCalculator)
        manager.calculate()
        willHasScaleX = manager.scaleX
        willHasScaleY = manager.scaleY
        animations.addAll(manager.animators)
    }

    private fun calculateAlpha(viewCalculator: ViewCalculator) {
        val manager = PleaseAnimAlphaManager(animExpectations, viewToMove, viewCalculator)
        manager.calculate()
        animations.addAll(manager.getAnimators())
    }

    private fun calculate3DTransforms(viewCalculator: ViewCalculator) {
        // camera distance animations
        val cameraDistanceManager = PleaseAnimCameraDistanceManager(animExpectations, viewToMove, viewCalculator)
        cameraDistanceManager.calculate()
        willHaveCameraDistance = cameraDistanceManager.cameraDistance
        animations.addAll(cameraDistanceManager.getAnimators())
    }

    private fun calculateRotation(viewCalculator: ViewCalculator) {
        val manager = PleaseAnimRotationManager(animExpectations, viewToMove, viewCalculator)
        manager.calculate()
        willHasRotationX = manager.rotation
        willHaveRotationX = manager.rotationX
        willHaveRotationY = manager.rotationY
        animations.addAll(manager.getAnimators())
    }

    private fun calculateCustom(viewCalculator: ViewCalculator) {
        val manager = PleaseAnimCustomManager(animExpectations, viewToMove, viewCalculator)
        manager.calculate()
        animations.addAll(manager.getAnimators())
    }

    internal fun start(): PleaseAnim {
        return expectAnim.start()
    }

    internal fun setPercent(percent: Float) {
        expectAnim.setPercent(percent)
    }

    internal fun calculate(viewCalculator: ViewCalculator) {
        calculate3DTransforms(viewCalculator)
        calculateRotation(viewCalculator)
        calculateScale(viewCalculator)
        calculatePosition(viewCalculator)
        calculateAlpha(viewCalculator)
        calculateCustom(viewCalculator)
    }

    internal fun getAnimations(): List<Animator> {
        return animations
    }

    internal fun calculateDependencies(): List<View> {
        dependencies.clear()
        for (animExpectation in animExpectations) {
            dependencies.addAll(animExpectation.viewsDependencies)
        }
        return dependencies
    }

    internal fun getDependencies(): List<View> {
        return dependencies
    }

    internal fun getWillHasScaleX(): Float {
        return if (willHasScaleX != null) {
            willHasScaleX!!
        } else {
            1f
        }
    }

    internal fun getWillHasScaleY(): Float {
        return if (willHasScaleY != null) {
            willHasScaleY!!
        } else {
            1f
        }
    }

    internal fun getWillHaveRotation(): Float? {
        return if (willHasRotationX != null) {
            willHasRotationX
        } else {
            null
        }
    }

    internal fun getFuturPositionX(): Float? {
        return willHasPositionX
    }

    internal fun getFuturPositionY(): Float? {
        return willHasPositionY
    }

    infix fun toBe(block: Expectations.() -> Unit): ViewExpectation {
        val expectations = Expectations(this.expectAnim)
        block.invoke(expectations)
        this.animExpectations.addAll(expectations.expectations)
        return this
    }
}
