package com.github.florent37.expectanim.core.anim3d;

import android.support.annotation.Nullable;
import android.view.View;

import com.github.florent37.expectanim.core.AnimExpectation;

/**
 * Created by Christian Ringshofer on 17/02/2017.
 */
public abstract class CameraDistanceExpectation extends AnimExpectation {

    @Nullable
    public abstract Float getCalculatedCameraDistance(View viewToMove);

}
