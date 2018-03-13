package com.github.florent37.kotlin.pleaseanimate.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.github.florent37.kotlin.pleaseanimate.please
import kotlinx.android.synthetic.main.activity_alpha.*

class AnimAlphaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alpha)

        image1.setOnClickListener {
            Toast.makeText(baseContext, "click", Toast.LENGTH_SHORT).show()
        }

        visible.setOnClickListener {
            please {
                animate(image1) toBe {
                    visible()
                }
            }.start()
        }

        invisible.setOnClickListener {
            please(duration = 1000L) {
                animate(image1) toBe {
                    invisible()
                }
            }.start()
        }
    }
}
