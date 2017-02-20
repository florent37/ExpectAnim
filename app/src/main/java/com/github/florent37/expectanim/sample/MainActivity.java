package com.github.florent37.expectanim.sample;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import com.github.florent37.expectanim.ExpectAnim;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.github.florent37.expectanim.core.Expectations.alphaValue;
import static com.github.florent37.expectanim.core.Expectations.atHisOriginalPosition;
import static com.github.florent37.expectanim.core.Expectations.atHisOriginalScale;
import static com.github.florent37.expectanim.core.Expectations.height;
import static com.github.florent37.expectanim.core.Expectations.leftOfParent;
import static com.github.florent37.expectanim.core.Expectations.rightOfParent;
import static com.github.florent37.expectanim.core.Expectations.sameCenterVerticalAs;
import static com.github.florent37.expectanim.core.Expectations.scale;
import static com.github.florent37.expectanim.core.Expectations.toRightOf;
import static com.github.florent37.expectanim.core.Expectations.topOfParent;
import static com.github.florent37.expectanim.core.Expectations.visible;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.username)
    View username;
    @BindView(R.id.avatar)
    View avatar;
    @BindView(R.id.follow)
    View follow;
    @BindView(R.id.background)
    View backbground;

    @BindView(R.id.scrollview)
    NestedScrollView scrollView;

    @BindDimen(R.dimen.height)
    int height;

    private ExpectAnim expectAnimMove;
    private ExpectAnim expectAnimReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.expectAnimMove = new ExpectAnim()
                .expect(username)
                .toBe(
                        toRightOf(avatar).withMarginDp(16),
                        sameCenterVerticalAs(avatar),

                        alphaValue(0.5f)
                )

                .andExpect(avatar)
                .toBe(
                        topOfParent(),
                        leftOfParent().withMarginDp(16),
                        scale(0.5f, 0.5f)
                )
                .andExpect(follow)
                .toBe(
                        rightOfParent().withMarginDp(16),
                        sameCenterVerticalAs(avatar)
                )

                .andExpect(backbground)
                .toBe(
                        height(height).withGravity(Gravity.LEFT, Gravity.TOP)
                )

                .toAnimation();

        this.expectAnimReset = new ExpectAnim()
                .expect(username)
                .toBe(
                        atHisOriginalPosition(),
                        visible()
                )

                .andExpect(avatar)
                .toBe(
                        atHisOriginalPosition(),
                        atHisOriginalScale()
                )

                .andExpect(follow)
                .toBe(
                        atHisOriginalPosition()
                )

                .andExpect(backbground)
                .toBe(
                        atHisOriginalScale()
                )

                .toAnimation();

        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                final float percent = (scrollY * 1f) / v.getMaxScrollAmount();
                expectAnimMove.setPercent(percent);
            }
        });
    }

    @OnClick(R.id.move)
    public void onMoveClicked() {
        expectAnimMove.start();
    }

    @OnClick(R.id.reset)
    public void onResetClicked() {
        expectAnimReset.start();
    }

}
