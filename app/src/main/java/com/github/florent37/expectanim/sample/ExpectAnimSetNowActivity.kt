package com.github.florent37.expectanim.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.github.florent37.expectanim.please
import kotlinx.android.synthetic.main.expectanim_activity_set_now.*


class ExpectAnimSetNowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expectanim_activity_set_now)

        follow.setOnClickListener { view ->
            please {
                animate(view) toBe {
                    invisible()
                }
            }.now()
        }


    }

}
