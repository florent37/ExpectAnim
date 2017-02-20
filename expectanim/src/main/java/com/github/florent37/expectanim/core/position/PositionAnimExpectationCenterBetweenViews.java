package com.github.florent37.expectanim.core.position;

import android.view.View;

import java.util.List;

/**
 * Created by florentchampigny on 17/02/2017.
 */

public class PositionAnimExpectationCenterBetweenViews extends PositionAnimExpectation {

    private View view1;
    private View view2;
    private boolean horizontal;
    private boolean vertical;


    public PositionAnimExpectationCenterBetweenViews(View view1, View view2, boolean horizontal, boolean vertical) {
        this.view1 = view1;
        this.view2 = view2;
        this.horizontal = horizontal;
        this.vertical = vertical;

        setForPositionY(true);
        setForPositionX(true);
    }

    @Override
    public Float getCalculatedValueX(View viewToMove) {
        if (horizontal) {
            final int centerXView1 = (int) (view1.getLeft() + view1.getWidth() / 2f);
            final int centerXView2 = (int) (view2.getLeft() + view2.getWidth() / 2f);

            return (centerXView1 + centerXView2) / 2f - viewToMove.getWidth() / 2f;
        }
        return null;
    }

    @Override
    public List<View> getViewsDependencies() {
        final List<View> viewsDependencies = super.getViewsDependencies();
        viewsDependencies.add(view1);
        viewsDependencies.add(view2);
        return viewsDependencies;
    }

    @Override
    public Float getCalculatedValueY(View viewToMove) {
        if (vertical) {
            final int centerYView1 = (int) (view1.getTop() + view1.getHeight() / 2f);
            final int centerYView2 = (int) (view2.getTop() + view2.getHeight() / 2f);

            return (centerYView1 + centerYView2) / 2f - viewToMove.getHeight() / 2f;
        }
        return null;
    }
}
