package com.github.florent37.expectanim.core;

import android.view.View;

import com.github.florent37.expectanim.ViewCalculator;

import java.util.ArrayList;
import java.util.List;

public abstract class AnimExpectation {

    protected ViewCalculator viewCalculator;

    public void setViewCalculator(ViewCalculator viewCalculator) {
        this.viewCalculator = viewCalculator;
    }

    public List<View> getViewsDependencies(){
        return new ArrayList<>();
    }
}
