package com.github.florent37.kotlin.pleaseanimate.sample

import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import com.github.florent37.kotlin.pleaseanimate.please
import kotlinx.android.synthetic.main.activity_scroll.*


class AnimScrollActivity : AppCompatActivity() {

    internal var h: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll)

        h = resources.getDimensionPixelOffset(R.dimen.height)

        val animation = please {
            animate(avatar) toBe {
                topOfHisParent(marginDp = 20f)
                leftOfHisParent(marginDp = 20f)
                scale(0.5f, 0.5f)
            }

            animate(username) toBe {
                rightOf(avatar, marginDp = 16f)
                sameCenterVerticalAs(avatar)

                alpha(0.5f)
            }

            animate(revert) toBe {
                rightOfHisParent(marginDp = 20f)
                sameCenterVerticalAs(avatar)
            }

            animate(background) toBe {
                height(h, horizontalGravity = Gravity.LEFT, verticalGravity = Gravity.TOP)
            }
        }

        scrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            val percent = scrollY * 1f / v.maxScrollAmount
            animation.setPercent(percent)
        })
    }

}
