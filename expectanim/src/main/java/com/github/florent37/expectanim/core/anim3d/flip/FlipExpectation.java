package com.github.florent37.expectanim.core.anim3d.flip;

import android.support.annotation.Nullable;
import android.view.View;

import com.github.florent37.expectanim.core.AnimExpectation;

/**
 * Created by Christian Ringshofer on 17/02/2017.
 */
public abstract class FlipExpectation extends AnimExpectation {

    @Nullable
    public abstract Float getCalculatedRotationX(View viewToMove);

    @Nullable
    public abstract Float getCalculatedRotationY(View viewToMove);

}
