package com.github.florent37.kotlin.pleaseanimate.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.florent37.kotlin.pleaseanimate.please
import kotlinx.android.synthetic.main.activity_flip.*

class AnimConcatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flip)

        val animation =
                please(duration = 1000L) {
                    animate(image1, withCameraDistance = 500f) toBe {
                        flippedHorizontally()
                    }
                    withEndAction{

                    }
                }.thenCouldYou(duration = 500L) {
                    animate(image2, withCameraDistance = 1000f) toBe {
                        flippedVertically()
                    }
                }.thenCouldYou(duration = 300L) {
                    animate(image3, withCameraDistance = 1500f) toBe {
                        flippedVertically()
                    }
                }.thenCouldYou(duration = 1000L) {
                    animate(image4, withCameraDistance = 2000f) toBe {
                        flippedHorizontallyAndVertically()
                    }
                }

        content.setOnClickListener{
            animation.setPercent(0f)
            animation.start()
        }
    }

}
