package com.github.florent37.expectanim.core.position;

import android.view.View;
import android.view.ViewParent;

/**
 * Created by florentchampigny on 17/02/2017.
 */

public class PositionAnimExpectationCenterInParent extends PositionAnimExpectation {

    public boolean horizontal;
    public boolean vertical;

    public PositionAnimExpectationCenterInParent(boolean horizontal, boolean vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;

        setForPositionX(true);
        setForPositionY(true);
    }

    @Override
    public Float getCalculatedValueX(View viewToMove) {
        final ViewParent viewParent = viewToMove.getParent();
        if((viewParent instanceof View) && horizontal){
            final View parentView = (View)viewParent;
            return parentView.getWidth() / 2f - viewToMove.getWidth() / 2f;
        }
        return null;
    }

    @Override
    public Float getCalculatedValueY(View viewToMove) {
        final ViewParent viewParent = viewToMove.getParent();
        if((viewParent instanceof View) && vertical){
            final View parentView = (View)viewParent;
            return parentView.getHeight() / 2f - viewToMove.getHeight() / 2f;
        }
        return null;
    }
}
