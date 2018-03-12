package com.github.florent37.expectanim.sample

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import com.github.florent37.expectanim.ExpectAnim
import com.github.florent37.expectanim.animation


class ExpectAnimSampleActivity : AppCompatActivity() {

    lateinit var name: View
    lateinit var avatar: View
    lateinit var subname: View
    lateinit var follow: View
    lateinit var message: View

    lateinit var bottomLayout: View
    lateinit var content: View

    private var expectAnimMove: ExpectAnim? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expectanim_activity_sample)

        name = findViewById(R.id.name)
        avatar = findViewById(R.id.avatar)
        subname = findViewById(R.id.subname)
        follow = findViewById(R.id.follow)
        message = findViewById(R.id.message)
        bottomLayout = findViewById(R.id.bottomLayout)
        content = findViewById(R.id.content)

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
                bottomOfParent(marginDp = 36f)
                leftOfParent(marginDp = 16f)
                width(40, keepRatio = true, toDp = true)
            }

            animate(name) {
                toRightOf(avatar, marginDp = 16f)
                sameCenterVerticalAs(avatar)
                toHaveTextColor(Color.WHITE)
            }

            animate(subname) {
                toRightOf(name, marginDp = 5f)
                sameCenterVerticalAs(name)
                toHaveTextColor(Color.WHITE)
            }

            animate(follow) {
                rightOfParent(marginDp = 4f)
                bottomOfParent(marginDp = 12f)
                toHaveBackgroundAlpha(0f)
            }

            animate(message) {
                aboveOf(follow, marginDp = 4f)
                rightOfParent(marginDp = 4f)
                toHaveBackgroundAlpha(0f)
            }

            animate(bottomLayout) {
                atItsOriginalPosition()
            }

            animate(content) {
                atItsOriginalPosition()
                visible()
            }

        }

        findViewById(R.id.message).setOnClickListener { expectAnimMove!!.start() }

        findViewById(R.id.follow).setOnClickListener { expectAnimMove!!.reset() }
    }
}
