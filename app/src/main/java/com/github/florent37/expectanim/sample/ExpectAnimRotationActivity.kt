package com.github.florent37.expectanim.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.github.florent37.expectanim.ExpectAnim
import com.github.florent37.expectanim.animation
import com.github.florent37.expectanim.core.Expectations.*

class ExpectAnimRotationActivity : AppCompatActivity() {

    lateinit var text1: View
    lateinit var text2: View
    lateinit var text3: View
    lateinit var text4: View

    private var expectAnimMove: ExpectAnim? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expectanim_activity_rotation)

        text1 = findViewById(R.id.text1)
        text2 = findViewById(R.id.text2)
        text3 = findViewById(R.id.text3)
        text4 = findViewById(R.id.text4)

        this.expectAnimMove = animation(duration = 1500L) {
            expect(text1)
                    .toBe(
                            topOfParent(),
                            leftOfParent(),
                            rotated(90f)
                    )

            expect(text2)
                    .toBe(
                            alignLeft(text1),
                            belowOf(text1)
                    )

            expect(text3)
                    .toBe(
                            alignTop(text1),
                            toRightOf(text1)
                    )

            expect(text4)
                    .toBe(
                            belowOf(text3),
                            alignLeft(text3)
                    )
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
