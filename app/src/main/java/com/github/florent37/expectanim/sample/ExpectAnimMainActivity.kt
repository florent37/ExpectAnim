package com.github.florent37.expectanim.sample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.expectanim_activity_main.*


class ExpectAnimMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expectanim_activity_main)

        sample.setOnClickListener {
            openScreen(ExpectAnimSampleActivity::class.java)
        }

        scroll.setOnClickListener {
            openScreen(ExpectAnimScrollActivity::class.java)
        }

        rotation.setOnClickListener {
            openScreen(ExpectAnimRotationActivity::class.java)
        }

        flip.setOnClickListener {
            openScreen(ExpectAnimFlipActivity::class.java)
        }

        setnow.setOnClickListener {
            openScreen(ExpectAnimSetNowActivity::class.java)
        }

        visible.setOnClickListener {
            openScreen(ExpectAnimAlphaActivity::class.java)
        }

        concat.setOnClickListener {
            openScreen(ExpectAnimConcatActivity::class.java)
        }
    }

    private fun openScreen(screenClass: Class<*>) {
        startActivity(Intent(this@ExpectAnimMainActivity, screenClass))
    }

}
