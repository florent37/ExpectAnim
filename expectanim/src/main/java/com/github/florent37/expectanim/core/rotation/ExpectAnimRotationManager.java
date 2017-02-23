package com.github.florent37.expectanim.core.rotation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import com.github.florent37.expectanim.ViewCalculator;
import com.github.florent37.expectanim.core.AnimExpectation;
import com.github.florent37.expectanim.core.ExpectAnimManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florentchampigny on 17/02/2017.
 */

public class ExpectAnimRotationManager extends ExpectAnimManager {

    private Float rotation = null;

    public ExpectAnimRotationManager(List<AnimExpectation> animExpectations, View viewToMove, ViewCalculator viewCalculator) {
        super(animExpectations, viewToMove, viewCalculator);
    }

    @Override
    public void calculate() {
        for (AnimExpectation expectation : animExpectations) {
            if (expectation instanceof RotationExpectation) {
                final Float rotation = ((RotationExpectation) expectation).getCalculatedRotation(viewToMove);
                if (rotation != null) {
                    this.rotation = rotation;
                }
            }
        }
    }

    @Override
    public List<Animator> getAnimators() {
        final List<Animator> animations = new ArrayList<>();

        calculate();

        if (rotation != null) {
            animations.add(ObjectAnimator.ofFloat(viewToMove, View.ROTATION, rotation));
        }

        return animations;
    }

    public Float getRotation() {
        return rotation;
    }
}
