package com.github.florent37.expectanim.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.florent37.expectanim.ExpectAnim;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.github.florent37.expectanim.core.Expectations.height;
import static com.github.florent37.expectanim.core.Expectations.invisible;
import static com.github.florent37.expectanim.core.Expectations.scale;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.sample)
    public void onSampleClicked() {
        startActivity(new Intent(this, SampleActivity.class));
    }

    @OnClick(R.id.scroll)
    public void onScrollClicked() {
        startActivity(new Intent(this, ScrollActivity.class));
    }

    @OnClick(R.id.rotation)
    public void onRotationClicked() {
        startActivity(new Intent(this, RotationActivity.class));
    }

    @OnClick(R.id.flip)
    public void onFlipClicked() {
        startActivity(new Intent(this, FlipActivity.class));
    }

    @OnClick(R.id.setnow)
    public void onSetNowClicked() {
        startActivity(new Intent(this, SetNowActivity.class));
    }

    @OnClick(R.id.visible)
    public void onVisibleClicked() {
        startActivity(new Intent(this, AlphaActivity.class));
    }

    @OnClick(R.id.concat)
    public void onConcatClicked() {
        startActivity(new Intent(this, ConcatActivity.class));
    }

}
