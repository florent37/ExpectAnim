package com.github.florent37.expectanim.core.scale;

import android.support.annotation.Nullable;
import android.view.View;

import com.github.florent37.expectanim.GravityScaleHorizontalIntDef;
import com.github.florent37.expectanim.GravityScaleVerticalIntDef;
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

    public ScaleAnimExpectation withGravity(@GravityScaleHorizontalIntDef @Nullable Integer gravityHorizontal, @GravityScaleVerticalIntDef @Nullable Integer gravityVertical) {
        if (gravityHorizontal != null) {
            this.gravityHorizontal = gravityHorizontal;
        }
        if (gravityVertical != null) {
            this.gravityVertical = gravityVertical;
        }
        return this;
    }

    public void setToDp(boolean toDp) {
        this.toDp = toDp;
    }

    public void setKeepRatio(boolean keepRatio) {
        this.keepRatio = keepRatio;
    }
}
