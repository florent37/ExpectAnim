package com.github.florent37.expectanim.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.github.florent37.expectanim.animation
import kotlinx.android.synthetic.main.expectanim_activity_alpha.*

class ExpectAnimAlphaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expectanim_activity_alpha)

        image1.setOnClickListener {
            Toast.makeText(baseContext, "click", Toast.LENGTH_SHORT).show()
        }

        findViewById(R.id.visible).setOnClickListener {
            animation {
                animate(image1) {
                    visible()
                }
            }.start()
        }

        findViewById(R.id.invisible).setOnClickListener {
            animation(duration = 1000L) {
                animate(image1) {
                    invisible()
                }
            }.start()
        }
    }
}
