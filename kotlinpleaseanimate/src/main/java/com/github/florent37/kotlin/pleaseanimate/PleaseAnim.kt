package com.github.florent37.kotlin.pleaseanimate

import android.animation.*
import android.view.View
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import com.github.florent37.kotlin.pleaseanimate.core.Expectations
import com.github.florent37.kotlin.pleaseanimate.core.anim3d.CameraDistanceExpectationValue
import java.util.concurrent.atomic.AtomicBoolean

class PleaseAnim {

    private val expectationList: MutableList<ViewExpectation> = mutableListOf()
    private var anyView: View? = null

    private var startDelay: Long = 5

    private val viewToMove: MutableList<View> = mutableListOf()
    private val viewCalculator: ViewCalculator = ViewCalculator()

    private var animatorSet: AnimatorSet? = null

    private val endListeners = mutableListOf<(PleaseAnim) -> Unit>()
    private val startListeners = mutableListOf<(PleaseAnim) -> Unit>()
    private val isPlaying = AtomicBoolean(false)

    private var interpolator: Interpolator? = null
    private var duration: Long = DEFAULT_DURATION

    private var firstAnim: PleaseAnim? = null
    private var nextAnim: PleaseAnim? = null

    fun animate(view: View, withCameraDistance: Float? = null, block: (Expectations.() -> Unit)? = null): ViewExpectation {
        this.anyView = view

        val viewExpectation = ViewExpectation(this, view)
        expectationList.add(viewExpectation)

        if (withCameraDistance != null) {
            viewExpectation.animExpectations.add(CameraDistanceExpectationValue(withCameraDistance!!))
        }

        if (block != null) {
            return viewExpectation.toBe(block)
        } else {
            return viewExpectation
        }
    }

    private fun calculate(): PleaseAnim {
        if (animatorSet == null) {
            animatorSet = AnimatorSet()

            if (interpolator != null) {
                animatorSet!!.interpolator = interpolator
            }

            animatorSet!!.duration = duration

            val animatorList = mutableListOf<Animator>()

            val expectationsToCalculate = mutableListOf<ViewExpectation>()

            //"ViewDependencies" = récupérer toutes les vues des "Expectations"
            expectationList.forEach { viewExpectation ->
                viewExpectation.calculateDependencies()
                viewToMove.add(viewExpectation.viewToMove)
                expectationsToCalculate.add(viewExpectation)

                viewCalculator.setExpectationForView(viewExpectation.viewToMove, viewExpectation)
            }

            while (!expectationsToCalculate.isEmpty()) {
                //pour chaque expectation dans "Expectations"
                val iterator = expectationsToCalculate.iterator()
                while (iterator.hasNext()) {
                    val viewExpectation = iterator.next()

                    //regarder si une de ces dépendance est dans "ViewDependencies"
                    if (!hasDependency(viewExpectation)) {
                        //si non
                        viewExpectation.calculate(viewCalculator)
                        animatorList.addAll(viewExpectation.getAnimations())

                        val view = viewExpectation.viewToMove
                        viewToMove.remove(view)
                        viewCalculator.wasCalculated(viewExpectation)

                        iterator.remove()
                    } else {
                        //si oui, attendre le prochain tour
                    }
                }
            }

            animatorSet!!.addListener(object : AnimatorListenerAdapter() {

                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    isPlaying.set(false)
                    notifyListenerEnd()
                }

                override fun onAnimationStart(animation: Animator) {
                    super.onAnimationStart(animation)
                    isPlaying.set(true)
                    notifyListenerStart()
                }

            })

            animatorSet!!.playTogether(animatorList)
        }
        return this
    }

    private fun notifyListenerStart() {
        startListeners.forEach { startListener ->
            startListener.invoke(this@PleaseAnim)
        }
    }

    private fun notifyListenerEnd() {
        endListeners.forEach { endListener ->
            endListener.invoke(this@PleaseAnim)
        }
    }

    fun setStartDelay(startDelay: Long): PleaseAnim {
        this.startDelay = startDelay
        return this
    }

    private fun hasDependency(viewExpectation: ViewExpectation): Boolean {
        val dependencies = viewExpectation.getDependencies()
        if (!dependencies.isEmpty()) {
            for (view in viewToMove) {
                if (dependencies.contains(view)) {
                    return true
                }
            }
        }
        return false
    }

    fun setPercent(percent: Float) {
        calculate()
        animatorSet?.childAnimations?.let { anims ->
            anims.forEach { animator ->
                if (animator is ValueAnimator) {
                    animator.currentPlayTime = (percent * animator.getDuration()).toLong()
                }
            }
        }
    }

    fun isPlaying(): Boolean {
        return isPlaying.get()
    }

    fun now() {
        executeAfterDraw(anyView, Runnable { setPercent(1f) })
    }

    fun executeAfterDraw(view: View?, runnable: Runnable) {
        view?.postDelayed(runnable, Math.max(5, startDelay))
    }

    fun reset() {
        val objectAnimator = ObjectAnimator.ofFloat(this, "percent", 1f, 0f)
        objectAnimator.duration = duration
        if (interpolator != null) {
            objectAnimator.interpolator = interpolator
        }
        objectAnimator.start()
    }

    fun setInterpolator(interpolator: Interpolator): PleaseAnim {
        this.interpolator = interpolator
        return this
    }

    fun setDuration(duration: Long): PleaseAnim {
        this.duration = duration
        return this
    }

    fun withEndAction(listener: (PleaseAnim) -> Unit): PleaseAnim {
        this.endListeners.add(listener)
        return this
    }

    fun withStartAction(listener: (PleaseAnim) -> Unit): PleaseAnim {
        this.startListeners.add(listener)
        return this
    }

    fun thenCouldYou(duration: Long = 300L, interpolator: Interpolator = LinearInterpolator(), block: (PleaseAnim.() -> Unit)): PleaseAnim {
        val pleaseAnim = PleaseAnim()
        pleaseAnim.setDuration(duration)
        pleaseAnim.setInterpolator(interpolator)
        if(this.firstAnim == null){
            this.firstAnim = this
        }
        pleaseAnim.firstAnim = this.firstAnim
        block.invoke(pleaseAnim)

        this.nextAnim = pleaseAnim

        withEndAction { PleaseAnim@ this.nextAnim?.startThen() }

        return pleaseAnim
    }

    private fun startThen() {
        executeAfterDraw(anyView, Runnable {
            calculate()
            animatorSet!!.start()
        })
    }

    fun start(): PleaseAnim {
        if(this.firstAnim != null){
            this.firstAnim?.startThen()
        } else {
            this.startThen()
        }
        return this
    }

    companion object {

        private val DEFAULT_DURATION = 300L

    }
}
