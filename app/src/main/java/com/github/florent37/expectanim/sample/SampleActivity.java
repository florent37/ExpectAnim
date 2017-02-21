package com.github.florent37.expectanim.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import com.github.florent37.expectanim.ExpectAnim;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.github.florent37.expectanim.core.Expectations.aboveOf;
import static com.github.florent37.expectanim.core.Expectations.atHisOriginalPosition;
import static com.github.florent37.expectanim.core.Expectations.bottomOfParent;
import static com.github.florent37.expectanim.core.Expectations.leftOfParent;
import static com.github.florent37.expectanim.core.Expectations.outOfScreen;
import static com.github.florent37.expectanim.core.Expectations.rightOfParent;
import static com.github.florent37.expectanim.core.Expectations.sameCenterVerticalAs;
import static com.github.florent37.expectanim.core.Expectations.toHaveTextColor;
import static com.github.florent37.expectanim.core.Expectations.toRightOf;


public class SampleActivity extends AppCompatActivity {

    @BindView(R.id.name)
    View name;
    @BindView(R.id.avatar)
    View avatar;
    @BindView(R.id.subname)
    View subname;
    @BindView(R.id.follow)
    View follow;
    @BindView(R.id.message)
    View message;

    @BindView(R.id.bottomLayout)
    View bottomLayout;

    private ExpectAnim expectAnimMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        ButterKnife.bind(this);

        new ExpectAnim()
                .expect(bottomLayout)
                .toBe(
                        outOfScreen(Gravity.BOTTOM)
                )
                .toAnimation()
                .setNow();

        this.expectAnimMove = new ExpectAnim()

                .expect(avatar)
                .toBe(
                        bottomOfParent().withMarginDp(16),
                        leftOfParent().withMarginDp(16)
                )

                .expect(name)
                .toBe(
                        toRightOf(avatar).withMarginDp(16),
                        sameCenterVerticalAs(avatar),
                        toHaveTextColor(Color.WHITE)
                )

                .expect(subname)
                .toBe(
                        toRightOf(name).withMarginDp(5),
                        sameCenterVerticalAs(name),
                        toHaveTextColor(Color.WHITE)
                )

                .expect(follow)
                .toBe(
                        rightOfParent().withMarginDp(4),
                        bottomOfParent().withMarginDp(4)
                )

                .expect(message)
                .toBe(
                        aboveOf(follow).withMarginDp(4),
                        rightOfParent().withMarginDp(4)
                )

                .expect(bottomLayout)
                .toBe(
                        atHisOriginalPosition()
                )

                .toAnimation();
    }

    @OnClick(R.id.move)
    public void onMoveClicked() {
        expectAnimMove.start();
    }

    @OnClick(R.id.reset)
    public void onResetClicked() {
        expectAnimMove.reset();
    }

}
