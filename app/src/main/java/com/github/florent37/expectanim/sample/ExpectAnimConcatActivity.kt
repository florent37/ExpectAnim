package com.github.florent37.expectanim.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.github.florent37.expectanim.ExpectAnim
import com.github.florent37.expectanim.animation
import com.github.florent37.expectanim.core.Expectations.*

class ExpectAnimConcatActivity : AppCompatActivity() {

    lateinit var image1: View
    lateinit var image2: View
    lateinit var image3: View
    lateinit var image4: View

    private var expectAnimMove: ExpectAnim? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expectanim_activity_flip)

        image1 = findViewById(R.id.image_1)
        image2 = findViewById(R.id.image_2)
        image3 = findViewById(R.id.image_3)
        image4 = findViewById(R.id.image_4)

        this.expectAnimMove = ExpectAnim.concat(
                animation(duration = 1000L) {
                    expect(image1)
                            .toBe(
                                    withCameraDistance(500f),
                                    flippedHorizontally()
                            )
                },
                animation(duration = 500L) {
                    expect(image2)
                            .toBe(
                                    withCameraDistance(1000f),
                                    flippedVertically()
                            )
                },
                animation(duration = 300L) {
                    expect(image3)
                            .toBe(
                                    withCameraDistance(1500f),
                                    flippedVertically()
                            )
                },
                animation(duration = 1000L) {
                    expect(image4)
                            .toBe(
                                    withCameraDistance(2000f),
                                    flippedHorizontallyAndVertically()
                            )
                }
        )
                .start()

        findViewById(R.id.content).setOnClickListener {
            expectAnimMove!!.setPercent(0f)
            expectAnimMove!!.start()
        }
    }

}
