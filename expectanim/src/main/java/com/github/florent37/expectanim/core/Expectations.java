package com.github.florent37.expectanim.core;

import android.view.View;

import com.github.florent37.expectanim.GravityIntDef;
import com.github.florent37.expectanim.GravityScaleHorizontalIntDef;
import com.github.florent37.expectanim.GravityScaleVerticalIntDef;
import com.github.florent37.expectanim.core.alpha.AlphaAnimExpectation;
import com.github.florent37.expectanim.core.alpha.AlphaAnimExpectationValue;
import com.github.florent37.expectanim.core.anim3d.CameraDistanceExpectation;
import com.github.florent37.expectanim.core.anim3d.CameraDistanceExpectationValue;
import com.github.florent37.expectanim.core.custom.CustomAnimExpectation;
import com.github.florent37.expectanim.core.custom.TextColorAnimExpectation;
import com.github.florent37.expectanim.core.custom.ViewBackgroundAlphaAnimExpectation;
import com.github.florent37.expectanim.core.position.PositionAnimExpectation;
import com.github.florent37.expectanim.core.position.PositionAnimExpectationAboveOf;
import com.github.florent37.expectanim.core.position.PositionAnimExpectationAlignBottom;
import com.github.florent37.expectanim.core.position.PositionAnimExpectationAlignLeft;
import com.github.florent37.expectanim.core.position.PositionAnimExpectationAlignRight;
import com.github.florent37.expectanim.core.position.PositionAnimExpectationAlignTop;
import com.github.florent37.expectanim.core.position.PositionAnimExpectationBelowOf;
import com.github.florent37.expectanim.core.position.PositionAnimExpectationBottomOfParent;
import com.github.florent37.expectanim.core.position.PositionAnimExpectationCenterBetweenViewAndParent;
import com.github.florent37.expectanim.core.position.PositionAnimExpectationCenterBetweenViews;
import com.github.florent37.expectanim.core.position.PositionAnimExpectationCenterInParent;
import com.github.florent37.expectanim.core.position.PositionAnimExpectationLeftOf;
import com.github.florent37.expectanim.core.position.PositionAnimExpectationLeftOfParent;
import com.github.florent37.expectanim.core.position.PositionAnimExpectationOriginal;
import com.github.florent37.expectanim.core.position.PositionAnimExpectationOutOfScreen;
import com.github.florent37.expectanim.core.position.PositionAnimExpectationRightOf;
import com.github.florent37.expectanim.core.position.PositionAnimExpectationRightOfParent;
import com.github.florent37.expectanim.core.position.PositionAnimExpectationSameCenterAs;
import com.github.florent37.expectanim.core.position.PositionAnimExpectationTopOfParent;
import com.github.florent37.expectanim.core.rotation.RotationExpectation;
import com.github.florent37.expectanim.core.rotation.RotationExpectationOriginal;
import com.github.florent37.expectanim.core.rotation.RotationExpectationValue;
import com.github.florent37.expectanim.core.rotation.RotationFlipExpectationValue;
import com.github.florent37.expectanim.core.scale.ScaleAnimExpectation;
import com.github.florent37.expectanim.core.scale.ScaleAnimExpectationHeight;
import com.github.florent37.expectanim.core.scale.ScaleAnimExpectationOriginalScale;
import com.github.florent37.expectanim.core.scale.ScaleAnimExpectationSameHeightAs;
import com.github.florent37.expectanim.core.scale.ScaleAnimExpectationSameScaleAs;
import com.github.florent37.expectanim.core.scale.ScaleAnimExpectationSameWidthAs;
import com.github.florent37.expectanim.core.scale.ScaleAnimExpectationValues;
import com.github.florent37.expectanim.core.scale.ScaleAnimExpectationWidth;

import java.util.ArrayList;
import java.util.List;

public class Expectations {

    private List<AnimExpectation> expectations = new ArrayList<>();

    //region position

    public PositionAnimExpectation toRightOf(View view) {
        final PositionAnimExpectation expect = new PositionAnimExpectationRightOf(view);
        expectations.add(expect);
        return expect;
    }

    public PositionAnimExpectation toLeftOf(View view) {
        final PositionAnimExpectation expect = new PositionAnimExpectationLeftOf(view);
        expectations.add(expect);
        return expect;
    }

    public PositionAnimExpectation belowOf(View view) {
        final PositionAnimExpectation expect = new PositionAnimExpectationBelowOf(view);
        expectations.add(expect);
        return expect;
    }

    public PositionAnimExpectation aboveOf(View view) {
        final PositionAnimExpectationAboveOf expect = new PositionAnimExpectationAboveOf(view);
        expectations.add(expect);
        return expect;
    }

    public PositionAnimExpectation atItsOriginalPosition() {
        final PositionAnimExpectation expect = new PositionAnimExpectationOriginal();
        expectations.add(expect);
        return expect;
    }

    public PositionAnimExpectation sameCenterAs(View view, boolean horizontal, boolean vertical) {
        final PositionAnimExpectationSameCenterAs expect = new PositionAnimExpectationSameCenterAs(view, horizontal, vertical);
        expectations.add(expect);
        return expect;
    }

    public PositionAnimExpectation sameCenterHorizontalAs(View view) {
        final PositionAnimExpectation expect = sameCenterAs(view, true, false);
        expectations.add(expect);
        return expect;
    }

    public PositionAnimExpectation sameCenterVerticalAs(View view) {
        final PositionAnimExpectation expect = sameCenterAs(view, false, true);
        expectations.add(expect);
        return expect;
    }

    public PositionAnimExpectation centerInParent(boolean horizontal, boolean vertical) {
        final PositionAnimExpectation expect = new PositionAnimExpectationCenterInParent(horizontal, vertical);
        expectations.add(expect);
        return expect;
    }

    public PositionAnimExpectation centerVerticalInParent() {
        final PositionAnimExpectation expect = centerInParent(false, true);
        expectations.add(expect);
        return expect;
    }

    public PositionAnimExpectation centerHorizontalInParent() {
        final PositionAnimExpectation expect = centerInParent(true, false);
        expectations.add(expect);
        return expect;
    }

    public PositionAnimExpectation outOfScreen(@GravityIntDef int... gravities) {
        final PositionAnimExpectationOutOfScreen expect = new PositionAnimExpectationOutOfScreen(gravities);
        expectations.add(expect);
        return expect;
    }

    public PositionAnimExpectation centerBetweenViews(View view1, View view2, boolean horizontal, boolean vertical) {
        final PositionAnimExpectation expect = new PositionAnimExpectationCenterBetweenViews(view1, view2, horizontal, vertical);
        expectations.add(expect);
        return expect;
    }

    public PositionAnimExpectation topOfParent() {
        final PositionAnimExpectationTopOfParent expect = new PositionAnimExpectationTopOfParent();
        expectations.add(expect);
        return expect;
    }

    public PositionAnimExpectation rightOfParent() {
        final PositionAnimExpectationRightOfParent expect = new PositionAnimExpectationRightOfParent();
        expectations.add(expect);
        return expect;
    }

    public PositionAnimExpectation bottomOfParent() {
        final PositionAnimExpectationBottomOfParent expect = new PositionAnimExpectationBottomOfParent();
        expectations.add(expect);
        return expect;
    }

    public PositionAnimExpectation leftOfParent() {
        final PositionAnimExpectationLeftOfParent expect = new PositionAnimExpectationLeftOfParent();
        expectations.add(expect);
        return expect;
    }

    public PositionAnimExpectation centerBetweenViewAndParent(View otherView, boolean horizontal, boolean vertical, boolean toBeOnRight, boolean toBeOnBottom) {
        final PositionAnimExpectationCenterBetweenViewAndParent expect = new PositionAnimExpectationCenterBetweenViewAndParent(otherView, horizontal, vertical, toBeOnRight, toBeOnBottom);
        expectations.add(expect);
        return expect;
    }

    public PositionAnimExpectation alignBottom(View otherView) {
        final PositionAnimExpectationAlignBottom expect = new PositionAnimExpectationAlignBottom(otherView);
        expectations.add(expect);
        return expect;
    }

    public PositionAnimExpectation alignTop(View otherView) {
        final PositionAnimExpectationAlignTop expect = new PositionAnimExpectationAlignTop(otherView);
        expectations.add(expect);
        return expect;
    }

    public PositionAnimExpectation alignLeft(View otherView) {
        final PositionAnimExpectationAlignLeft expect = new PositionAnimExpectationAlignLeft(otherView);
        expectations.add(expect);
        return expect;
    }

    public PositionAnimExpectation alignRight(View otherView) {
        final PositionAnimExpectationAlignRight expect = new PositionAnimExpectationAlignRight(otherView);
        expectations.add(expect);
        return expect;
    }

    //endregion

    //region alpha

    public AlphaAnimExpectation alpha(float alpha) {
        final AlphaAnimExpectationValue expect = new AlphaAnimExpectationValue(alpha);
        expectations.add(expect);
        return expect;
    }

    public AlphaAnimExpectation sameAlphaAs(View otherView) {
        final AlphaAnimExpectationValue expect = new AlphaAnimExpectationValue(otherView.getAlpha());
        expectations.add(expect);
        return expect;
    }

    public AlphaAnimExpectation visible() {
        final AlphaAnimExpectationValue expect = new AlphaAnimExpectationValue(1f);
        expectations.add(expect);
        return expect;
    }

    public AlphaAnimExpectation invisible() {
        final AlphaAnimExpectationValue expect = new AlphaAnimExpectationValue(0f);
        expectations.add(expect);
        return expect;
    }

    //endregion

    //region scale


    public ScaleAnimExpectation atItsOriginalScale() {
        final ScaleAnimExpectationOriginalScale expect = new ScaleAnimExpectationOriginalScale();
        expectations.add(expect);
        return expect;
    }

    public ScaleAnimExpectation scale(float scaleX, float scaleY, @GravityScaleHorizontalIntDef int gravityHorizontal, @GravityScaleVerticalIntDef int gravityVertical) {
        final ScaleAnimExpectationValues expect = new ScaleAnimExpectationValues(scaleX, scaleY, gravityHorizontal, gravityVertical);
        expectations.add(expect);
        return expect;
    }

    public ScaleAnimExpectation scale(float scaleX, float scaleY) {
        final ScaleAnimExpectationValues expect = new ScaleAnimExpectationValues(scaleX, scaleY, null, null);
        expectations.add(expect);
        return expect;
    }

    public ScaleAnimExpectation height(int height, @GravityScaleHorizontalIntDef int gravityHorizontal, @GravityScaleVerticalIntDef int gravityVertical) {
        final ScaleAnimExpectationHeight expect = new ScaleAnimExpectationHeight(height, gravityHorizontal, gravityVertical);
        expectations.add(expect);
        return expect;
    }

    public ScaleAnimExpectation height(int height) {
        final ScaleAnimExpectationHeight expect = new ScaleAnimExpectationHeight(height, null, null);
        expectations.add(expect);
        return expect;
    }

    public ScaleAnimExpectation width(int width, @GravityScaleHorizontalIntDef int gravityHorizontal, @GravityScaleVerticalIntDef int gravityVertical) {
        final ScaleAnimExpectationWidth expect = new ScaleAnimExpectationWidth(width, gravityHorizontal, gravityVertical);
        expectations.add(expect);
        return expect;
    }

    public ScaleAnimExpectation width(int width) {
        final ScaleAnimExpectationWidth expect = new ScaleAnimExpectationWidth(width, null, null);
        expectations.add(expect);
        return expect;
    }

    public ScaleAnimExpectation sameScaleAs(View otherView) {
        final ScaleAnimExpectationSameScaleAs expect = new ScaleAnimExpectationSameScaleAs(otherView);
        expectations.add(expect);
        return expect;
    }

    public ScaleAnimExpectation sameWidthAs(View otherView) {
        final ScaleAnimExpectationSameWidthAs expect = new ScaleAnimExpectationSameWidthAs(otherView, null, null);
        expectations.add(expect);
        return expect;
    }

    public ScaleAnimExpectation sameHeightAs(View otherView) {
        final ScaleAnimExpectationSameHeightAs expect = new ScaleAnimExpectationSameHeightAs(otherView, null, null);
        expectations.add(expect);
        return expect;
    }

    //endregion

    //region custom

    public CustomAnimExpectation toHaveTextColor(int textColor) {
        final TextColorAnimExpectation expect = new TextColorAnimExpectation(textColor);
        expectations.add(expect);
        return expect;
    }

    public CustomAnimExpectation toHaveBackgroundAlpha(float alpha) {
        final ViewBackgroundAlphaAnimExpectation expect = new ViewBackgroundAlphaAnimExpectation(alpha);
        expectations.add(expect);
        return expect;
    }


    //endregion

    //region rotation

    public RotationExpectation rotated(float rotation) {
        final RotationExpectationValue expect = new RotationExpectationValue(rotation);
        expectations.add(expect);
        return expect;
    }

    public CameraDistanceExpectation withCameraDistance(float cameraDistance) {
        final CameraDistanceExpectationValue expect = new CameraDistanceExpectationValue(cameraDistance);
        expectations.add(expect);
        return expect;
    }

    public RotationExpectation flippedHorizontally() {
        final RotationFlipExpectationValue expect = new RotationFlipExpectationValue(0f, 180f);
        expectations.add(expect);
        return expect;
    }

    public RotationExpectation flippedVertically() {
        final RotationFlipExpectationValue expect = new RotationFlipExpectationValue(180f, 0f);
        expectations.add(expect);
        return expect;
    }

    public RotationExpectation flippedHorizontallyAndVertically() {
        final RotationFlipExpectationValue expect = new RotationFlipExpectationValue(180f, 180f);
        expectations.add(expect);
        return expect;
    }

    public RotationExpectation vertical(boolean bottomOfViewAtLeft) {
        final RotationExpectation expect;
        if (bottomOfViewAtLeft) {
            expect = new RotationExpectationValue(90);
        } else {
            expect = new RotationExpectationValue(270);
        }
        expectations.add(expect);
        return expect;
    }

    public RotationExpectation atItsOriginalRotation() {
        final RotationExpectationOriginal expect = new RotationExpectationOriginal();
        expectations.add(expect);
        return expect;
    }

    //endregion


    public List<AnimExpectation> getExpectations() {
        return expectations;
    }
}
