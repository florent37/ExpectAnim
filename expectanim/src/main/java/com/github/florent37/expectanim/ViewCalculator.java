package com.github.florent37.expectanim;

import android.view.View;

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

    public float finalPositionLeftOfView(View view) {
        if (expectationForView.containsKey(view)) {
            final Float futurPositionX = expectationForView.get(view).getFuturPositionX();
            if (futurPositionX != null) {
                return futurPositionX;
            }
        }
        return view.getX();
    }

    public float finalPositionRightOfView(View view) {
        if (expectationForView.containsKey(view)) {
            final Float futurPositionX = expectationForView.get(view).getFuturPositionX();
            if (futurPositionX != null) {
                final float finalWidthOfView = finalWidthOfView(view);
                return futurPositionX + finalWidthOfView;
            }
        }
        return view.getRight();
    }

    public float finalPositionTopOfView(View view) {
        if (expectationForView.containsKey(view)) {
            final Float futurPositionY = expectationForView.get(view).getFuturPositionY();
            if (futurPositionY != null) {
                return futurPositionY;
            }
        }
        return view.getTop();
    }

    public float finalPositionBottomOfView(View view) {
        if (expectationForView.containsKey(view)) {
            final Float futurPositionY = expectationForView.get(view).getFuturPositionY();
            if (futurPositionY != null) {
                return futurPositionY + finalHeightOfView(view);
            }
        }
        return view.getBottom();
    }

    public float finalCenterXOfView(View view) {
        if (expectationForView.containsKey(view)) {
            return expectationForView.get(view).getFuturPositionX() + finalWidthOfView(view) / 2f;
        } else {
            return view.getLeft() + view.getWidth() / 2f;
        }
    }

    public float finalCenterYOfView(View view) {
        if (expectationForView.containsKey(view)) {
            return expectationForView.get(view).getFuturPositionY() + finalHeightOfView(view) / 2f;
        } else {
            return view.getTop() + view.getHeight() / 2f;
        }
    }

    public float finalWidthOfView(View view) {
        if (expectationForView.containsKey(view)) {
            final Float scaleX = expectationForView.get(view).getWillHasScaleX();
            if (scaleX != 1f) {
                return scaleX * view.getWidth() + view.getPivotX() * scaleX;
            }
        }
        return view.getWidth();
    }

    public float finalHeightOfView(View view) {
        if (expectationForView.containsKey(view)) {
            final Float scaleY = expectationForView.get(view).getWillHasScaleY();
            if (scaleY != 1) {
                return scaleY * view.getHeight() + view.getPivotY() * scaleY;
            }
        }
        return view.getHeight();
    }

}
