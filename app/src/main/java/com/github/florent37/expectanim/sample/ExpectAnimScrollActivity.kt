package com.github.florent37.expectanim.sample

import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import com.github.florent37.expectanim.ExpectAnim
import com.github.florent37.expectanim.animation
import kotlinx.android.synthetic.main.expectanim_activity_scroll.*


class ExpectAnimScrollActivity : AppCompatActivity() {

    internal var height: Int = 0

    private var expectAnimMove: ExpectAnim? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expectanim_activity_scroll)

        height = resources.getDimensionPixelOffset(R.dimen.height)

        this.expectAnimMove = animation {
            animate(avatar) {
                topOfParent(marginDp = 20f)
                leftOfParent(marginDp = 20f)
                scale(0.5f, 0.5f)
            }

            animate(username) {
                toRightOf(avatar, marginDp = 16f)
                sameCenterVerticalAs(avatar)

                alpha(0.5f)
            }

            animate(follow) {
                rightOfParent(marginDp = 20f)
                sameCenterVerticalAs(avatar)
            }

            animate(background) {
                height(height).withGravity(Gravity.LEFT, Gravity.TOP)
            }
        }

        scrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            val percent = scrollY * 1f / v.maxScrollAmount
            expectAnimMove!!.setPercent(percent)
        })
    }

}
