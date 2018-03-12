package com.github.florent37.expectanim.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.github.florent37.expectanim.animation


class ExpectAnimSetNowActivity : AppCompatActivity() {

    lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expectanim_activity_set_now)

        view = findViewById(R.id.follow)
        view.setOnClickListener {
            animation {
                animate(view) {
                    invisible()
                }
            }.setNow()
        }


    }

}
