package com.github.florent37.expectanim;

import android.view.View;

import com.github.florent37.expectanim.ViewExpectation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by florentchampigny on 20/02/2017.
 */

public class ViewCalculator {
    private final Map<View, ViewExpectation> expectationForView;

    public ViewCalculator() {
        expectationForView = new HashMap<>();
    }

    public void wasCalculated(View view, ViewExpectation viewExpectation) {
        expectationForView.put(view, viewExpectation);
    }

    public float finalPositionLeftOfView(View view){
        if(expectationForView.containsKey(view)){
            return expectationForView.get(view).getFuturPositionX();
        } else {
            return view.getX();
        }
    }

    public float finalPositionRightOfView(View view){
        if(expectationForView.containsKey(view)){
            return expectationForView.get(view).getFuturPositionX() + finalWidthOfView(view);
        } else {
            return view.getRight();
        }
    }

    public float finalPositionTopOfView(View view){
        if(expectationForView.containsKey(view)){
            return expectationForView.get(view).getFuturPositionY();
        } else {
            return view.getTop();
        }
    }

    public float finalPositionBottomOfView(View view){
        if(expectationForView.containsKey(view)){
            return expectationForView.get(view).getFuturPositionY() + finalHeightOfView(view);
        } else {
            return view.getBottom();
        }
    }

    public float finalCenterXOfView(View view) {
        if(expectationForView.containsKey(view)){
            return expectationForView.get(view).getFuturPositionX() + finalWidthOfView(view) / 2f;
        } else {
            return view.getLeft() + view.getWidth() / 2f;
        }
    }

    public float finalCenterYOfView(View view) {
        if(expectationForView.containsKey(view)){
            return expectationForView.get(view).getFuturPositionY() + finalHeightOfView(view) / 2f;
        } else {
            return view.getTop() + view.getHeight() / 2f;
        }
    }

    public float finalWidthOfView(View view) {
        if(expectationForView.containsKey(view)){
            final Float scaleX = expectationForView.get(view).getWillHasScaleX();
            return scaleX * view.getWidth() + view.getPivotX() * scaleX;
        } else {
            return view.getWidth();
        }
    }

    public float finalHeightOfView(View view) {
        if(expectationForView.containsKey(view)){
            final Float scaleY = expectationForView.get(view).getWillHasScaleY();
            return scaleY * view.getHeight() + view.getPivotY() * scaleY;
        } else {
            return view.getHeight();
        }
    }

}
