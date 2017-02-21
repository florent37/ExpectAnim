package com.github.florent37.expectanim.core.custom;

import android.animation.Animator;
import android.view.View;

import com.github.florent37.expectanim.ViewCalculator;
import com.github.florent37.expectanim.core.AnimExpectation;
import com.github.florent37.expectanim.core.ExpectAnimManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florentchampigny on 17/02/2017.
 */

public class ExpectAnimCustomManager extends ExpectAnimManager {

    private final List<Animator> animations;

    public ExpectAnimCustomManager(List<AnimExpectation> animExpectations, View viewToMove, ViewCalculator viewCalculator) {
        super(animExpectations, viewToMove, viewCalculator);
        this.animations = new ArrayList<>();
    }

    @Override
    public void calculate() {

        for (AnimExpectation animExpectation : animExpectations) {
            if (animExpectation instanceof CustomAnimExpectation) {
                final CustomAnimExpectation expectation = (CustomAnimExpectation) animExpectation;

                expectation.setViewCalculator(viewCalculator);

                final Animator animator = expectation.getAnimator(viewToMove);
                if (animator != null) {
                    animations.add(animator);
                }
            }
        }
    }

    @Override
    public List<Animator> getAnimators() {
        return animations;
    }
}
