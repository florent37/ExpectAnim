package com.github.florent37.expectanim.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import com.github.florent37.expectanim.ExpectAnim;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.github.florent37.expectanim.core.Expectations.bottomOfParent;
import static com.github.florent37.expectanim.core.Expectations.outOfScreen;


public class SequenceActivity extends AppCompatActivity {

    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.view3)
    View view3;

    private ExpectAnim expectAnimMove;
    private ExpectAnim anim1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequence);
        ButterKnife.bind(this);

        anim1 = new ExpectAnim()
                .expect(view1)
                .toBe(
                        bottomOfParent()
                )
                .thenExpect(view2)
                .toBe(
                        outOfScreen(Gravity.END)
                )
                .toAnimation()
                .setDuration(1000)
                .setTag(true);

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean tag = (Boolean) anim1.getTag();
                anim1.setTag(!tag);
                if (tag) {
                    anim1.start();
                } else {
                    anim1.reset();
                }
            }
        });
    }

}
