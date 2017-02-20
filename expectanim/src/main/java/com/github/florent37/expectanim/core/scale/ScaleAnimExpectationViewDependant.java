package com.github.florent37.expectanim.core.scale;

import android.view.View;

import java.util.List;

/**
 * Created by florentchampigny on 20/02/2017.
 */
public abstract class ScaleAnimExpectationViewDependant extends ScaleAnimExpectation {

    protected final View otherView;

    public ScaleAnimExpectationViewDependant(View otherView, Integer gravityHorizontal, Integer gravityVertical) {
        super(gravityHorizontal, gravityVertical);
        this.otherView = otherView;
    }

    @Override
    public List<View> getViewsDependencies() {
        final List<View> viewsDependencies = super.getViewsDependencies();
        viewsDependencies.add(otherView);
        return viewsDependencies;
    }
}
