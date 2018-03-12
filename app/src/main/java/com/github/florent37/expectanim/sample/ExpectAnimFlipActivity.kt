package com.github.florent37.expectanim.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.florent37.expectanim.ExpectAnim
import com.github.florent37.expectanim.animation
import kotlinx.android.synthetic.main.expectanim_activity_flip.*

class ExpectAnimFlipActivity : AppCompatActivity() {

    private var expectAnimMove: ExpectAnim? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expectanim_activity_flip)

        this.expectAnimMove = animation(duration = 1500L) {
            animate(image1) {
                withCameraDistance(500f)
                flippedHorizontally()
            }
            animate(image2) {
                withCameraDistance(1000f)
                flippedVertically()
            }
            animate(image3) {
                withCameraDistance(1500f)
                flippedVertically()
            }
            animate(image4) {
                withCameraDistance(2000f)
                flippedHorizontallyAndVertically()
            }
        }


        content.setOnClickListener {
            expectAnimMove!!.setPercent(0f)
            expectAnimMove!!.start()
        }
    }

}
