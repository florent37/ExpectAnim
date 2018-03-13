package com.github.florent37.kotlin.pleaseanimate.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.florent37.kotlin.pleaseanimate.please
import kotlinx.android.synthetic.main.activity_flip.*

class AnimFlipActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flip)

        val animation = please(duration = 1500L) {
            animate(image1, withCameraDistance = 500f) toBe {
                flippedHorizontally()
            }
            animate(image2, withCameraDistance = 1000f) toBe {
                flippedVertically()
            }
            animate(image3, withCameraDistance = 1500f) toBe {
                flippedVertically()
            }
            animate(image4, withCameraDistance = 2000f) toBe {
                flippedHorizontallyAndVertically()
            }
        }


        content.setOnClickListener {
            animation.setPercent(0f)
            animation.start()
        }
    }

}
