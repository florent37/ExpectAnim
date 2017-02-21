package com.github.florent37.expectanim.core.scale;

import android.support.annotation.Nullable;
import android.view.View;

import com.github.florent37.expectanim.core.AnimExpectation;
import com.github.florent37.expectanim.core.Expectations;
import com.github.florent37.expectanim.core.position.PositionAnimExpectation;

/**
 * Created by florentchampigny on 17/02/2017.
 */

public abstract class ScaleAnimExpectation extends AnimExpectation {

    protected boolean toDp = false;
    protected boolean keepRatio = false;
    @Nullable
    private Integer gravityHorizontal;
    @Nullable
    private Integer gravityVertical;

    public ScaleAnimExpectation(@Nullable Integer gravityHorizontal, @Nullable Integer gravityVertical) {
        if (gravityHorizontal != null) {
            this.gravityHorizontal = gravityHorizontal;
        }
        if (gravityVertical != null) {
            this.gravityVertical = gravityVertical;
        }
    }

    protected int dpToPx(float value, View view) {
        final int v =  (int) PositionAnimExpectation.dpToPx(view.getContext(), value);
        toDp = false;
        return v;
    }

    public abstract Float getCalculatedValueScaleX(View viewToMove);

    public abstract Float getCalculatedValueScaleY(View viewToMove);

    public Integer getGravityHorizontal() {
        return gravityHorizontal;
    }

    public Integer getGravityVertical() {
        return gravityVertical;
    }

    public ScaleAnimExpectation withGravity(@Expectations.GravityScaleHorizontalIntDef @Nullable Integer gravityHorizontal, @Expectations.GravityScaleVerticalIntDef @Nullable Integer gravityVertical) {
        if (gravityHorizontal != null) {
            this.gravityHorizontal = gravityHorizontal;
        }
        if (gravityVertical != null) {
            this.gravityVertical = gravityVertical;
        }
        return this;
    }

    public ScaleAnimExpectation toDp() {
        this.toDp = true;
        return this;
    }

    public ScaleAnimExpectation keepRatio() {
        this.keepRatio = true;
        return this;
    }
}
