package com.github.florent37.expectanim

import android.animation.*
import android.view.View
import android.view.animation.Interpolator
import com.github.florent37.expectanim.core.Expectations
import com.github.florent37.expectanim.listener.AnimationEndListener
import com.github.florent37.expectanim.listener.AnimationStartListener
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

class ExpectAnim {

    private val expectationList: MutableList<ViewExpectation> = mutableListOf()
    private var anyView: View? = null

    private var startDelay: Long = 5

    private val viewToMove: MutableList<View> = mutableListOf()
    private val viewCalculator: ViewCalculator = ViewCalculator()

    private var animatorSet: AnimatorSet? = null

    private val endListeners = mutableListOf<AnimationEndListener>()
    private val startListeners = mutableListOf<AnimationStartListener>()
    private val isPlaying = AtomicBoolean(false)

    private var interpolator: Interpolator? = null
    private var duration: Long = DEFAULT_DURATION

    fun animate(view: View, block: (Expectations.() -> Unit)): ViewExpectation {
        this.anyView = view

        val viewExpectation = ViewExpectation(this, view)
        expectationList.add(viewExpectation)

        val expectations = Expectations()
        block.invoke(expectations)
        viewExpectation.animExpectations.addAll(expectations.expectations)

        return viewExpectation
    }

    private fun calculate(): ExpectAnim {
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
            startListener.onAnimationStart(this@ExpectAnim)
        }
    }

    private fun notifyListenerEnd() {
        endListeners.forEach { endListener ->
            endListener.onAnimationEnd(this@ExpectAnim)
        }
    }

    fun setStartDelay(startDelay: Long): ExpectAnim {
        this.startDelay = startDelay
        return this
    }

    fun start(): ExpectAnim {
        executeAfterDraw(anyView, Runnable {
            calculate()
            animatorSet!!.start()
        })
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

    fun setNow() {
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

    fun setInterpolator(interpolator: Interpolator): ExpectAnim {
        this.interpolator = interpolator
        return this
    }

    fun setDuration(duration: Long): ExpectAnim {
        this.duration = duration
        return this
    }

    fun addEndListener(listener: AnimationEndListener): ExpectAnim {
        this.endListeners.add(listener)
        return this
    }

    fun addStartListener(listener: AnimationStartListener): ExpectAnim {
        this.startListeners.add(listener)
        return this
    }

    private fun concatWith(otherAnim: ExpectAnim) {
        addEndListener(AnimationEndListener { otherAnim.start() })
    }

    companion object {

        private val DEFAULT_DURATION = 300L

        fun concat(expectAnim: ExpectAnim, vararg expectAnims: ExpectAnim): ExpectAnim {
            if (expectAnims.size > 0) {
                expectAnim.concatWith(expectAnims[0])
                for (i in 0 until expectAnims.size - 1) {
                    expectAnims[i].concatWith(expectAnims[i + 1])
                }
            }
            return expectAnim
        }
    }
}
