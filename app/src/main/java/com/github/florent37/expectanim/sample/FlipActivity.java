package com.github.florent37.expectanim.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.florent37.expectanim.ExpectAnim;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.github.florent37.expectanim.core.Expectations.flippedHorizontally;
import static com.github.florent37.expectanim.core.Expectations.flippedHorizontallyAndVertically;
import static com.github.florent37.expectanim.core.Expectations.flippedVertically;
import static com.github.florent37.expectanim.core.Expectations.withCameraDistance;

public class FlipActivity extends AppCompatActivity {

    @BindView(R.id.image_1)
    View image1;
    @BindView(R.id.image_2)
    View image2;
    @BindView(R.id.image_3)
    View image3;
    @BindView(R.id.image_4)
    View image4;

    private ExpectAnim expectAnimMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip);
        ButterKnife.bind(this);

        this.expectAnimMove = new ExpectAnim()
                .expect(image1)
                .toBe(
                        withCameraDistance(500f),
                        flippedHorizontally()
                )
                .expect(image2)
                .toBe(
                        withCameraDistance(1000f),
                        flippedVertically()
                )
                .expect(image3)
                .toBe(
                        withCameraDistance(1500f),
                        flippedVertically()
                )
                .expect(image4)
                .toBe(
                        withCameraDistance(2000f),
                        flippedHorizontallyAndVertically()
                )
                .toAnimation()
                .setDuration(1500);
    }

    @OnClick(R.id.content)
    public void onMoveClicked() {
        expectAnimMove.setPercent(0f);
        expectAnimMove.start();
    }

}
