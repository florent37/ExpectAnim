package com.github.florent37.expectanim.sample

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import com.github.florent37.expectanim.ExpectAnim
import com.github.florent37.expectanim.animation
import kotlinx.android.synthetic.main.expectanim_activity_sample.*


class ExpectAnimSampleActivity : AppCompatActivity() {

    private var expectAnimMove: ExpectAnim? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expectanim_activity_sample)

        animation {
            animate(bottomLayout) {
                outOfScreen(Gravity.BOTTOM)
            }
            animate(content) {
                outOfScreen(Gravity.BOTTOM)
                invisible()
            }
        }.setNow()

        this.expectAnimMove = animation(duration = 1500L) {

            animate(avatar) {
                bottomOfHisParent(marginDp = 36f)
                leftOfHisParent(marginDp = 16f)
                visible()
                width(40, keepRatio = true, toDp = true)
            }

            animate(name) {
                rightOf(avatar, marginDp = 16f)
                sameCenterVerticalAs(avatar)
                textColor(Color.WHITE)
            }

            animate(subname) {
                rightOf(name, marginDp = 5f)
                sameCenterVerticalAs(name)
                textColor(Color.WHITE)
            }

            animate(follow) {
                rightOfHisParent(marginDp = 4f)
                bottomOfHisParent(marginDp = 12f)
                backgroundAlpha(0f)
            }

            animate(message) {
                aboveOf(follow, marginDp = 4f)
                rightOfHisParent(marginDp = 4f)
                backgroundAlpha(0f)
            }

            animate(bottomLayout) {
                originalPosition()
            }

            animate(content) {
                originalPosition()
                visible()
            }

        }

        message.setOnClickListener { expectAnimMove!!.start() }

        follow.setOnClickListener { expectAnimMove!!.reset() }
    }
}
