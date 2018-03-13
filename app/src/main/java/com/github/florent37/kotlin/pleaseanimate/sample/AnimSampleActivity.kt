package com.github.florent37.kotlin.pleaseanimate.sample

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import com.github.florent37.kotlin.pleaseanimate.please
import kotlinx.android.synthetic.main.activity_sample.*


class AnimSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        please {
            animate(bottomLayout) {
                outOfScreen(Gravity.BOTTOM)
            }
            animate(content) {
                outOfScreen(Gravity.BOTTOM)
                invisible()
            }
        }.now()

        val animation = please(duration = 1500L) {

            animate(avatar) toBe {
                bottomOfHisParent(marginDp = 36f)
                leftOfHisParent(marginDp = 16f)
                visible()
                width(40, keepRatio = true, toDp = true)
            }

            animate(name) toBe {
                rightOf(avatar, marginDp = 16f)
                sameCenterVerticalAs(avatar)
                textColor(Color.WHITE)
            }

            animate(subname) toBe {
                rightOf(name, marginDp = 5f)
                sameCenterVerticalAs(name)
                textColor(Color.WHITE)
            }

            animate(revert) toBe {
                rightOfHisParent(marginDp = 4f)
                bottomOfHisParent(marginDp = 12f)
                backgroundAlpha(0f)
            }

            animate(start) toBe {
                aboveOf(revert, marginDp = 4f)
                rightOfHisParent(marginDp = 4f)
                backgroundAlpha(0f)
            }

            animate(bottomLayout) toBe {
                originalPosition()
            }

            animate(content) toBe {
                originalPosition()
                visible()
            }

        }

        start.setOnClickListener { animation.start() }

        revert.setOnClickListener { animation.reset() }
    }
}
