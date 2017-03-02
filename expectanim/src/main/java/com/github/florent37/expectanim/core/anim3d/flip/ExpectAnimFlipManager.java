package com.github.florent37.expectanim.core.anim3d.flip;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.florent37.expectanim.ViewCalculator;
import com.github.florent37.expectanim.core.AnimExpectation;
import com.github.florent37.expectanim.core.ExpectAnimManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian Ringshofer on 17/02/2017.
 */

public class ExpectAnimFlipManager extends ExpectAnimManager {

    @Nullable
    private Float mRotationX, mRotationY = null;

    public ExpectAnimFlipManager(List<AnimExpectation> animExpectations, View viewToMove, ViewCalculator viewCalculator) {
        super(animExpectations, viewToMove, viewCalculator);
    }

    @Override
    public void calculate() {
        for (AnimExpectation expectation : animExpectations) {
            if (expectation instanceof FlipExpectation) {
                final Float rotationX = ((FlipExpectation) expectation).getCalculatedRotationX(viewToMove);
                if (rotationX != null) this.mRotationX = rotationX;
                final Float rotationY = ((FlipExpectation) expectation).getCalculatedRotationY(viewToMove);
                if (rotationY != null) this.mRotationY = rotationY;
            }
        }
    }

    @Override
    public List<Animator> getAnimators() {
        final List<Animator> animations = new ArrayList<>();
        calculate();
        if (mRotationX != null) {
            animations.add(ObjectAnimator.ofFloat(viewToMove, View.ROTATION_X, mRotationX));
        }
        if (mRotationY != null) {
            animations.add(ObjectAnimator.ofFloat(viewToMove, View.ROTATION_Y, mRotationY));
        }
        return animations;
    }

    @Nullable
    public Float getRotationX() {
        return mRotationX;
    }

    @Nullable
    public Float getRotationY() {
        return mRotationY;
    }

}
