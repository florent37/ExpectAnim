package com.github.florent37.expectanim.sample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View


class ExpectAnimMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expectanim_activity_main)

        findViewById(R.id.sample).setOnClickListener {
            startActivity(Intent(this@ExpectAnimMainActivity, ExpectAnimSampleActivity::class.java))
        }

        findViewById(R.id.scroll).setOnClickListener {
            startActivity(Intent(this@ExpectAnimMainActivity, ExpectAnimScrollActivity::class.java))
        }

        findViewById(R.id.rotation).setOnClickListener {
            startActivity(Intent(this@ExpectAnimMainActivity, ExpectAnimRotationActivity::class.java))
        }

        findViewById(R.id.flip).setOnClickListener {
            startActivity(Intent(this@ExpectAnimMainActivity, ExpectAnimFlipActivity::class.java))
        }

        findViewById(R.id.setnow).setOnClickListener {
            startActivity(Intent(this@ExpectAnimMainActivity, ExpectAnimSetNowActivity::class.java))
        }

        findViewById(R.id.visible).setOnClickListener {
            startActivity(Intent(this@ExpectAnimMainActivity, ExpectAnimAlphaActivity::class.java))
        }

        findViewById(R.id.concat).setOnClickListener {
            startActivity(Intent(this@ExpectAnimMainActivity, ExpectAnimConcatActivity::class.java))
        }
    }

}
