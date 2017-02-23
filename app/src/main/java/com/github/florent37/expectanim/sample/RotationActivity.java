package com.github.florent37.expectanim.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.florent37.expectanim.ExpectAnim;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.github.florent37.expectanim.core.Expectations.alignLeft;
import static com.github.florent37.expectanim.core.Expectations.alignTop;
import static com.github.florent37.expectanim.core.Expectations.belowOf;
import static com.github.florent37.expectanim.core.Expectations.leftOfParent;
import static com.github.florent37.expectanim.core.Expectations.toRightOf;
import static com.github.florent37.expectanim.core.Expectations.topOfParent;
import static com.github.florent37.expectanim.core.Expectations.rotated;

public class RotationActivity extends AppCompatActivity {

    @BindView(R.id.text1)
    View text1;
    @BindView(R.id.text2)
    View text2;
    @BindView(R.id.text3)
    View text3;
    @BindView(R.id.text4)
    View text4;

    private ExpectAnim expectAnimMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation);
        ButterKnife.bind(this);

        this.expectAnimMove = new ExpectAnim()

                .expect(text1)
                .toBe(
                        topOfParent(),
                        leftOfParent(),
                        rotated(90)
                )

                .expect(text2)
                .toBe(
                        alignLeft(text1),
                        belowOf(text1)
                )

                .expect(text3)
                .toBe(
                        alignTop(text1),
                        toRightOf(text1)
                )

                .expect(text4)
                .toBe(
                        belowOf(text3),
                        alignLeft(text3)
                )

                .toAnimation()
                .setDuration(1500);
    }

    @OnClick(R.id.content)
    public void onMoveClicked() {
        if (text1.getRotation() == 0) {
            expectAnimMove.start();
        } else {
            expectAnimMove.reset();
        }
    }

}
