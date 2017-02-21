package com.github.florent37.expectanim.core.position;

import android.content.Context;
import android.support.annotation.DimenRes;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;

import com.github.florent37.expectanim.core.AnimExpectation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florentchampigny on 17/02/2017.
 */

public abstract class PositionAnimExpectation extends AnimExpectation {
    public abstract Float getCalculatedValueX(View viewToMove);
    public abstract Float getCalculatedValueY(View viewToMove);

    private boolean isForPositionY;
    private boolean isForPositionX;
    private boolean isForTranslationX;
    private boolean isForTranslationY;
    private float margin;

    @Nullable
    private Integer marginRes;

    @Nullable
    private Float marginDp;

    public boolean isForPositionY() {
        return isForPositionY;
    }

    public boolean isForPositionX() {
        return isForPositionX;
    }

    public boolean isForTranslationX() {
        return isForTranslationX;
    }

    public boolean isForTranslationY() {
        return isForTranslationY;
    }

    protected void setForPositionY(boolean forPositionY) {
        isForPositionY = forPositionY;
    }

    protected void setForPositionX(boolean forPositionX) {
        isForPositionX = forPositionX;
    }

    protected void setForTranslationX(boolean forTranslationX) {
        isForTranslationX = forTranslationX;
    }

    protected void setForTranslationY(boolean forTranslationY) {
        isForTranslationY = forTranslationY;
    }

    public float getMargin(View view) {
        if(marginRes != null){
            margin = view.getContext().getResources().getDimension(marginRes);
        } else if(marginDp != null){
            margin = dpToPx(view.getContext(), marginDp);
        }
        return margin;
    }

    public static float dpToPx(Context context, float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public PositionAnimExpectation withMargin(float margin){
        this.margin = margin;
        return this;
    }

    public PositionAnimExpectation withMarginDimen(@DimenRes int marginRes){
        this.marginRes = marginRes;
        return this;
    }

    public PositionAnimExpectation withMarginDp(float marginDp){
        this.marginDp = marginDp;
        return this;
    }

}
