package com.github.florent37.expectanim.core.alpha;

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

public class ExpectAnimAlphaManager extends ExpectAnimManager {

    private Float alpha = null;

    public ExpectAnimAlphaManager(List<AnimExpectation> animExpectations, View viewToMove, ViewCalculator viewCalculator) {
        super(animExpectations, viewToMove, viewCalculator);
    }

    @Override
    public void calculate() {
        for (AnimExpectation expectation : animExpectations) {
            if (expectation instanceof AlphaAnimExpectation) {
                final Float alpha = ((AlphaAnimExpectation) expectation).getCalculatedAlpha(viewToMove);
                if (alpha != null) {
                    this.alpha = alpha;
                }
            }
        }
    }

    @Override
    public List<Animator> getAnimators() {
        final List<Animator> animations = new ArrayList<>();

        calculate();

        if (alpha != null) {
            animations.add(ObjectAnimator.ofFloat(viewToMove, View.ALPHA, alpha));
        }

        return animations;
    }
}
