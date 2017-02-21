package com.github.florent37.expectanim;

import android.animation.Animator;
import android.view.View;

import com.github.florent37.expectanim.core.AnimExpectation;
import com.github.florent37.expectanim.core.Expectations;
import com.github.florent37.expectanim.core.alpha.ExpectAnimAlphaManager;
import com.github.florent37.expectanim.core.custom.CustomAnimExpectation;
import com.github.florent37.expectanim.core.custom.ExpectAnimCustomManager;
import com.github.florent37.expectanim.core.position.ExpectAnimPositionManager;
import com.github.florent37.expectanim.core.position.PositionAnimationViewDependant;
import com.github.florent37.expectanim.core.scale.ExpectAnimScaleManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by florentchampigny on 17/02/2017.
 */

public class ViewExpectation {

    private final ExpectAnim expectAnim;
    private final View viewToMove;
    private final List<View> dependencies;
    private List<Animator> animations;
    private List<AnimExpectation> animExpectations;

    private Float willHasScaleX;
    private Float willHasScaleY;

    private Float willHasPositionX;
    private Float willHasPositionY;

    ViewExpectation(ExpectAnim expectAnim, View viewToMove) {
        this.expectAnim = expectAnim;
        this.viewToMove = viewToMove;
        this.animations = new ArrayList<>();
        this.animExpectations = new ArrayList<>();
        this.dependencies = new ArrayList<>();
    }

    public ViewExpectation expect(View view) {
        return expectAnim.expect(view);
    }

    public ViewExpectation toBe(AnimExpectation... animExpectations) {
        this.animExpectations.addAll(Arrays.asList(animExpectations));
        return this;
    }

    private void calculatePosition(ViewCalculator viewCalculator) {
        if (animExpectations != null) {
            final ExpectAnimPositionManager manager = new ExpectAnimPositionManager(animExpectations, viewToMove, viewCalculator);
            manager.calculate(willHasScaleX, willHasScaleY);
            willHasPositionX = manager.getPositionX();
            willHasPositionY = manager.getPositionY();
            animations.addAll(manager.getAnimators());
        }

    }

    private void calculateScale(ViewCalculator viewCalculator) {
        if (animExpectations != null) {
            final ExpectAnimScaleManager manager = new ExpectAnimScaleManager(animExpectations, viewToMove, viewCalculator);
            manager.calculate();
            willHasScaleX = manager.getScaleX();
            willHasScaleY = manager.getScaleY();
            animations.addAll(manager.getAnimators());
        }

    }

    private void calculateAlpha(ViewCalculator viewCalculator) {
        if (animExpectations != null) {
            final ExpectAnimAlphaManager manager = new ExpectAnimAlphaManager(animExpectations, viewToMove, viewCalculator);
            manager.calculate();
            animations.addAll(manager.getAnimators());
        }

    }

    private void calculateCustom(ViewCalculator viewCalculator) {
        if (animExpectations != null) {
            final ExpectAnimCustomManager manager = new ExpectAnimCustomManager(animExpectations, viewToMove, viewCalculator);
            manager.calculate();
            animations.addAll(manager.getAnimators());
        }

    }

    ExpectAnim start() {
        return expectAnim.start();
    }

    void setPercent(float percent) {
        expectAnim.setPercent(percent);
    }

    void calculate(ViewCalculator viewCalculator) {
        calculateScale(viewCalculator);
        calculatePosition(viewCalculator);
        calculateAlpha(viewCalculator);
        calculateCustom(viewCalculator);
    }

    List<Animator> getAnimations() {
        return animations;
    }

    List<View> calculateDependencies() {
        dependencies.clear();
        if (animExpectations != null) {
            for (AnimExpectation animExpectation : animExpectations) {
                    dependencies.addAll(animExpectation.getViewsDependencies());
            }
        }
        return dependencies;
    }

    List<View> getDependencies() {
        return dependencies;
    }

    View getViewToMove() {
        return viewToMove;
    }

    Float getFuturPositionX() {
        return willHasPositionX;
    }

    Float getFuturPositionY() {
        return willHasPositionY;
    }

    Float getWillHasScaleX() {
        if (willHasScaleX != null) {
            return willHasScaleX;
        } else {
            return 1f;
        }
    }

    Float getWillHasScaleY() {
        if (willHasScaleY != null) {
            return willHasScaleY;
        } else {
            return 1f;
        }
    }

    public ExpectAnim toAnimation() {
        return expectAnim;
    }
}
