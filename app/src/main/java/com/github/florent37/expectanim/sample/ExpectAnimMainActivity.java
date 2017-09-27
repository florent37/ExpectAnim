package com.github.florent37.expectanim.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.github.florent37.expectanim.core.Expectations.height;
import static com.github.florent37.expectanim.core.Expectations.scale;


public class ExpectAnimMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expectanim_activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.sample)
    public void onSampleClicked() {
        startActivity(new Intent(this, ExpectAnimSampleActivity.class));
    }

    @OnClick(R.id.scroll)
    public void onScrollClicked() {
        startActivity(new Intent(this, ExpectAnimScrollActivity.class));
    }

    @OnClick(R.id.rotation)
    public void onRotationClicked() {
        startActivity(new Intent(this, ExpectAnimRotationActivity.class));
    }

    @OnClick(R.id.flip)
    public void onFlipClicked() {
        startActivity(new Intent(this, ExpectAnimFlipActivity.class));
    }

    @OnClick(R.id.setnow)
    public void onSetNowClicked() {
        startActivity(new Intent(this, ExpectAnimSetNowActivity.class));
    }

    @OnClick(R.id.visible)
    public void onVisibleClicked() {
        startActivity(new Intent(this, ExpectAnimAlphaActivity.class));
    }

    @OnClick(R.id.concat)
    public void onConcatClicked() {
        startActivity(new Intent(this, ExpectAnimConcatActivity.class));
    }

}
