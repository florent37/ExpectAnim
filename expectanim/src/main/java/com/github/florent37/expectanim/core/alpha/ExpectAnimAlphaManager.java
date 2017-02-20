package com.github.florent37.expectanim.core.alpha;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import com.github.florent37.expectanim.core.AnimExpectation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florentchampigny on 17/02/2017.
 */

public class ExpectAnimAlphaManager {

    private final List<AnimExpectation> animExpectations;
    private final View viewToMove;

    private Float alpha = null;

    public ExpectAnimAlphaManager(List<AnimExpectation> animExpectations, View viewToMove) {
        this.animExpectations = animExpectations;
        this.viewToMove = viewToMove;
    }

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

    public List<Animator> getAnimators() {
        final List<Animator> animations = new ArrayList<>();

        calculate();

        if (alpha != null) {
            animations.add(ObjectAnimator.ofFloat(viewToMove, View.ALPHA, alpha));
        }

        return animations;
    }
}
