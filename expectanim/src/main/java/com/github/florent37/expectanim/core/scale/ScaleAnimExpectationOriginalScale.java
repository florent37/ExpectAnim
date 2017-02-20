package com.github.florent37.expectanim.core.scale;

import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by florentchampigny on 17/02/2017.
 */
public class ScaleAnimExpectationOriginalScale extends ScaleAnimExpectation {

    public ScaleAnimExpectationOriginalScale() {
        super(null, null);
    }

    @Override
    public Float getCalculatedValueScaleX(View viewToMove) {
        return 1f;
    }

    @Override
    public Float getCalculatedValueScaleY(View viewToMove) {
        return 1f;
    }
}
