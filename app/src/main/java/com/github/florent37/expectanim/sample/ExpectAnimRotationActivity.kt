package com.github.florent37.expectanim.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.github.florent37.expectanim.ExpectAnim
import com.github.florent37.expectanim.animation
import kotlinx.android.synthetic.main.expectanim_activity_rotation.*

class ExpectAnimRotationActivity : AppCompatActivity() {

    private var expectAnimMove: ExpectAnim? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expectanim_activity_rotation)


        this.expectAnimMove = animation(duration = 1500L) {
            animate(text1) {
                topOfParent()
                leftOfParent()
                rotated(90f)
            }

            animate(text2) {
                alignLeft(text1)
                belowOf(text1)
            }

            animate(text3) {
                alignTop(text1)
                toRightOf(text1)
            }

            animate(text4) {
                belowOf(text3)
                alignLeft(text3)
            }
        }
        
        findViewById(R.id.content).setOnClickListener {
            if (text1.rotation == 0f) {
                expectAnimMove!!.start()
            } else {
                expectAnimMove!!.reset()
            }
        }
    }

}
