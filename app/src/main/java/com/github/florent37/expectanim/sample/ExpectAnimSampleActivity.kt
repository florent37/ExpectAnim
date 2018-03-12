package com.github.florent37.expectanim.sample

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import com.github.florent37.expectanim.ExpectAnim
import com.github.florent37.expectanim.animation
import com.github.florent37.expectanim.core.Expectations.*


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
            expect(bottomLayout)
                    .toBe(
                            outOfScreen(Gravity.BOTTOM)
                    )
            expect(content)
                    .toBe(
                            outOfScreen(Gravity.BOTTOM),
                            invisible()
                    )
        }.setNow()

        this.expectAnimMove = animation(duration = 1500L) {

            expect(avatar)
                    .toBe(
                            bottomOfParent().withMarginDp(36f),
                            leftOfParent().withMarginDp(16f),
                            width(40).toDp().keepRatio()
                    )

            expect(name)
                    .toBe(
                            toRightOf(avatar).withMarginDp(16f),
                            sameCenterVerticalAs(avatar),
                            toHaveTextColor(Color.WHITE)
                    )

            expect(subname)
                    .toBe(
                            toRightOf(name).withMarginDp(5f),
                            sameCenterVerticalAs(name),
                            toHaveTextColor(Color.WHITE)
                    )

            expect(follow)
                    .toBe(
                            rightOfParent().withMarginDp(4f),
                            bottomOfParent().withMarginDp(12f),
                            toHaveBackgroundAlpha(0f)
                    )

            expect(message)
                    .toBe(
                            aboveOf(follow).withMarginDp(4f),
                            rightOfParent().withMarginDp(4f),
                            toHaveBackgroundAlpha(0f)
                    )

            expect(bottomLayout)
                    .toBe(
                            atItsOriginalPosition()
                    )

            expect(content)
                    .toBe(
                            atItsOriginalPosition(),
                            visible()
                    )

        }

        findViewById(R.id.message).setOnClickListener { expectAnimMove!!.start() }

        findViewById(R.id.follow).setOnClickListener { expectAnimMove!!.reset() }
    }
}
