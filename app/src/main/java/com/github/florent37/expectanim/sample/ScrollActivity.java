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

import static com.github.florent37.expectanim.core.Expectations.alpha;
import static com.github.florent37.expectanim.core.Expectations.height;
import static com.github.florent37.expectanim.core.Expectations.leftOfParent;
import static com.github.florent37.expectanim.core.Expectations.rightOfParent;
import static com.github.florent37.expectanim.core.Expectations.sameCenterVerticalAs;
import static com.github.florent37.expectanim.core.Expectations.scale;
import static com.github.florent37.expectanim.core.Expectations.toRightOf;
import static com.github.florent37.expectanim.core.Expectations.topOfParent;


public class ScrollActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        ButterKnife.bind(this);

        this.expectAnimMove = new ExpectAnim()
                .expect(avatar)
                .toBe(
                        topOfParent().withMarginDp(20),
                        leftOfParent().withMarginDp(20),
                        scale(0.5f, 0.5f)
                )

                .expect(username)
                .toBe(
                        toRightOf(avatar).withMarginDp(16),
                        sameCenterVerticalAs(avatar),

                        alpha(0.5f)
                )

                .expect(follow)
                .toBe(
                        rightOfParent().withMarginDp(20),
                        sameCenterVerticalAs(avatar)
                )

                .expect(backbground)
                .toBe(
                        height(height).withGravity(Gravity.LEFT, Gravity.TOP)
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

}
