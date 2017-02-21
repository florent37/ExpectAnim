package com.github.florent37.expectanim.core.custom;

import android.animation.Animator;
import android.view.View;

import com.github.florent37.expectanim.core.AnimExpectation;

/**
 * Created by florentchampigny on 21/02/2017.
 */

public abstract class CustomAnimExpectation extends AnimExpectation {
    public abstract Animator getAnimator(View viewToMove);
}
