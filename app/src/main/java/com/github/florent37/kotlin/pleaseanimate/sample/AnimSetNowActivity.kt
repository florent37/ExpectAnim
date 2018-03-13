package com.github.florent37.kotlin.pleaseanimate.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.florent37.kotlin.pleaseanimate.please
import kotlinx.android.synthetic.main.activity_set_now.*


class AnimSetNowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_now)

        revert.setOnClickListener { view ->
            please { //toBe is optional
                animate(view) toBe {
                    invisible()
                }
            }.now()
        }


    }

}
