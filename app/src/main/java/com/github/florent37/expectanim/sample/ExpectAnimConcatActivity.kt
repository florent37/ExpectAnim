package com.github.florent37.expectanim.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.florent37.expectanim.PleaseAnim
import com.github.florent37.expectanim.please
import kotlinx.android.synthetic.main.expectanim_activity_flip.*

class ExpectAnimConcatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expectanim_activity_flip)

        val animation = PleaseAnim.concat( //TODO change
                please(duration = 1000L) {
                    animate(image1, withCameraDistance = 500f) toBe {
                        flippedHorizontally()
                    }
                },
                please(duration = 500L) {
                    animate(image2, withCameraDistance = 1000f) toBe {
                        flippedVertically()
                    }
                },
                please(duration = 300L) {
                    animate(image3, withCameraDistance = 1500f) toBe {
                        flippedVertically()
                    }
                },
                please(duration = 1000L) {
                    animate(image4, withCameraDistance = 2000f) toBe {
                        flippedHorizontallyAndVertically()
                    }
                })
                .start()

        findViewById(R.id.content).setOnClickListener {
            animation.setPercent(0f)
            animation.start()
        }
    }

}
