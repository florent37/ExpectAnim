package com.github.florent37.kotlin.pleaseanimate.core.scale;

import android.view.View;

public abstract class ScaleAnimExpectationViewDependant extends ScaleAnimExpectation {

    protected final View otherView;

    public ScaleAnimExpectationViewDependant(View otherView, Integer gravityHorizontal, Integer gravityVertical) {
        super(gravityHorizontal, gravityVertical);
        this.otherView = otherView;

        getViewsDependencies().add(otherView);
    }
}
