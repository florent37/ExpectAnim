package com.github.florent37.kotlin.pleaseanimate.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.florent37.kotlin.pleaseanimate.please
import kotlinx.android.synthetic.main.activity_rotation.*

class AnimRotationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rotation)

        val animation = please(duration = 1500L) {
            animate(text1) toBe {
                topOfHisParent()
                leftOfHisParent()
                toBeRotated(90f)
            }
            animate(text2) toBe {
                alignLeft(text1)
                belowOf(text1)
            }
            animate(text3) toBe {
                alignTop(text1)
                rightOf(text1)
            }
            animate(text4) toBe {
                belowOf(text3)
                alignLeft(text3)
            }
        }.thenCouldYou {
            animate(text1) toBe {
                topOfHisParent()
                leftOfHisParent()
                toBeRotated(90f)
            }
        }

        content.setOnClickListener {
            if (text1.rotation == 0f) {
                animation.start()
            } else {
                animation.reset()
            }
        }
    }

}
