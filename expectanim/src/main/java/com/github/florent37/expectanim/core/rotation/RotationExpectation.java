package com.github.florent37.expectanim.core.rotation;

import android.view.View;

import com.github.florent37.expectanim.core.AnimExpectation;
import com.github.florent37.expectanim.core.alpha.AlphaAnimExpectation;

/**
 * Created by florentchampigny on 18/02/2017.
 */
public abstract class RotationExpectation extends AnimExpectation {

    public abstract Float getCalculatedRotation(View viewToMove);
    public abstract Float getCalculatedRotationX(View viewToMove);
    public abstract Float getCalculatedRotationY(View viewToMove);
}
