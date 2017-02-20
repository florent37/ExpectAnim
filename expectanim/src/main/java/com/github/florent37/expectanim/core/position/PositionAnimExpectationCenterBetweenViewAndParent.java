package com.github.florent37.expectanim.core.position;

import android.view.View;
import android.view.ViewParent;

/**
 * Created by florentchampigny on 17/02/2017.
 */

public class PositionAnimExpectationCenterBetweenViewAndParent extends PositionAnimationViewDependant {

    private boolean horizontal;
    private boolean vertical;
    private boolean toBeOnRight;
    private boolean toBeOnBottom;

    public PositionAnimExpectationCenterBetweenViewAndParent(View otherView, boolean horizontal, boolean vertical, boolean toBeOnRight, boolean toBeOnBottom) {
        super(otherView);
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.toBeOnRight = toBeOnRight;
        this.toBeOnBottom = toBeOnBottom;

        setForPositionY(true);
        setForPositionX(true);
    }

    @Override
    public Float getCalculatedValueX(View viewToMove) {
        if(horizontal){
            final ViewParent viewParent = otherView.getParent();
            if((viewParent instanceof View) && horizontal){
                final View parentView = (View) viewParent;
                final float centerOfOtherView = viewCalculator.finalCenterXOfView(otherView);
                if(toBeOnRight) {
                    final float parentWidth = parentView.getWidth();
                    return (parentWidth + centerOfOtherView) / 2f - viewToMove.getWidth() / 2f;
                } else {
                    return centerOfOtherView / 2f - viewToMove.getWidth() / 2f;
                }
            }
        }
        return null;
    }

    @Override
    public Float getCalculatedValueY(View viewToMove) {
        if(vertical){
            final ViewParent viewParent = viewToMove.getParent();
            if((viewParent instanceof View) && vertical){
                final View parentView = (View)viewParent;
                final float centerOfOtherView = viewCalculator.finalCenterYOfView(otherView);
                if(toBeOnBottom){
                    final float parentHeight = parentView.getHeight();
                    return parentHeight + centerOfOtherView / 2f - viewToMove.getHeight() / 2f;
                } else {
                    return centerOfOtherView / 2f - viewToMove.getHeight() / 2f;
                }

            }
        }
        return null;
    }
}
