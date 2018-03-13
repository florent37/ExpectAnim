package com.github.florent37.kotlin.pleaseanimate.sample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class AnimMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sample.setOnClickListener {
            openScreen(AnimSampleActivity::class.java)
        }

        scroll.setOnClickListener {
            openScreen(AnimScrollActivity::class.java)
        }

        rotation.setOnClickListener {
            openScreen(AnimRotationActivity::class.java)
        }

        flip.setOnClickListener {
            openScreen(AnimFlipActivity::class.java)
        }

        setnow.setOnClickListener {
            openScreen(AnimSetNowActivity::class.java)
        }

        visible.setOnClickListener {
            openScreen(AnimAlphaActivity::class.java)
        }

        concat.setOnClickListener {
            openScreen(AnimConcatActivity::class.java)
        }
    }

    private fun openScreen(screenClass: Class<*>) {
        startActivity(Intent(this@AnimMainActivity, screenClass))
    }

}
