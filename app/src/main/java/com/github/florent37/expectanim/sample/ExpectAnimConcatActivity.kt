package com.github.florent37.expectanim.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.github.florent37.expectanim.ExpectAnim
import com.github.florent37.expectanim.animation

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
                    animate(image1) {
                        withCameraDistance(500f)
                        flippedHorizontally()
                    }
                },
                animation(duration = 500L) {
                    animate(image2) {
                        withCameraDistance(1000f)
                        flippedVertically()
                    }
                },
                animation(duration = 300L) {
                    animate(image3) {
                        withCameraDistance(1500f)
                        flippedVertically()
                    }
                },
                animation(duration = 1000L) {
                    animate(image4) {
                        withCameraDistance(2000f)
                        flippedHorizontallyAndVertically()
                    }
                }).start()

        findViewById(R.id.content).setOnClickListener {
            expectAnimMove!!.setPercent(0f)
            expectAnimMove!!.start()
        }
    }

}
