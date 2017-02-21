package com.github.florent37.expectanim.sample;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.sample)
    public void onSampleClicked(){
        startActivity(new Intent(this, SampleActivity.class));
    }

    @OnClick(R.id.scroll)
    public void onScrollClicked(){
        startActivity(new Intent(this, ScrollActivity.class));
    }

}
