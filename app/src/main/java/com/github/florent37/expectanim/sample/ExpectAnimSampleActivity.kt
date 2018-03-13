package com.github.florent37.expectanim.sample

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import com.github.florent37.expectanim.PleaseAnim
import com.github.florent37.expectanim.please
import kotlinx.android.synthetic.main.expectanim_activity_sample.*


class ExpectAnimSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expectanim_activity_sample)

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

        message.setOnClickListener { animation.start() }

        follow.setOnClickListener { animation.reset() }
    }
}
