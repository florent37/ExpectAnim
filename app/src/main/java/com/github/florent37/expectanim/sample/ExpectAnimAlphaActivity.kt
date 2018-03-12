package com.github.florent37.expectanim.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.github.florent37.expectanim.ExpectAnim
import com.github.florent37.expectanim.animation
import com.github.florent37.expectanim.core.Expectations.invisible
import com.github.florent37.expectanim.core.Expectations.visible

class ExpectAnimAlphaActivity : AppCompatActivity() {

    lateinit var image1: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expectanim_activity_alpha)

        image1 = findViewById(R.id.image_1)
        image1.setOnClickListener {
            Toast.makeText(baseContext, "click", Toast.LENGTH_SHORT).show()
        }

        findViewById(R.id.visible).setOnClickListener {
            animation {
                expect(image1)
                        .toBe(
                                visible()
                        )
            }.start()
        }

        findViewById(R.id.invisible).setOnClickListener {
            animation(duration = 1000L) {
                expect(image1)
                    .toBe(
                            invisible()
                    )
            }.start()
        }
    }
}
