package com.github.florent37.expectanim.sample

import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import com.github.florent37.expectanim.ExpectAnim
import com.github.florent37.expectanim.animation


class ExpectAnimScrollActivity : AppCompatActivity() {

    lateinit var username: View
    lateinit var avatar: View
    lateinit var follow: View
    lateinit var backbground: View
    lateinit var scrollView: NestedScrollView

    internal var height: Int = 0

    private var expectAnimMove: ExpectAnim? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expectanim_activity_scroll)

        username = findViewById(R.id.username)
        avatar = findViewById(R.id.avatar)
        follow = findViewById(R.id.follow)
        backbground = findViewById(R.id.background)
        scrollView = findViewById(R.id.scrollview) as NestedScrollView

        height = resources.getDimensionPixelOffset(R.dimen.height)

        this.expectAnimMove = animation {
            animate(avatar) {
                topOfParent().withMarginDp(20f)
                leftOfParent().withMarginDp(20f)
                scale(0.5f, 0.5f)
            }

            animate(username) {
                toRightOf(avatar).withMarginDp(16f)
                sameCenterVerticalAs(avatar)

                alpha(0.5f)
            }

            animate(follow) {
                rightOfParent().withMarginDp(20f)
                sameCenterVerticalAs(avatar)
            }

            animate(backbground) {
                height(height).withGravity(Gravity.LEFT, Gravity.TOP)
            }
        }

        scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            val percent = scrollY * 1f / v.maxScrollAmount
            expectAnimMove!!.setPercent(percent)
        })
    }

}
