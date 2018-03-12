package com.github.florent37.expectanim.core

import android.view.View
import com.github.florent37.expectanim.GravityIntDef
import com.github.florent37.expectanim.GravityScaleHorizontalIntDef
import com.github.florent37.expectanim.GravityScaleVerticalIntDef
import com.github.florent37.expectanim.core.alpha.AlphaAnimExpectation
import com.github.florent37.expectanim.core.alpha.AlphaAnimExpectationValue
import com.github.florent37.expectanim.core.custom.CustomAnimExpectation
import com.github.florent37.expectanim.core.custom.TextColorAnimExpectation
import com.github.florent37.expectanim.core.custom.ViewBackgroundAlphaAnimExpectation
import com.github.florent37.expectanim.core.position.*
import com.github.florent37.expectanim.core.rotation.RotationExpectation
import com.github.florent37.expectanim.core.rotation.RotationExpectationOriginal
import com.github.florent37.expectanim.core.rotation.RotationExpectationValue
import com.github.florent37.expectanim.core.rotation.RotationFlipExpectationValue
import com.github.florent37.expectanim.core.scale.*

class Expectations {

    internal val expectations = mutableListOf<AnimExpectation>()

    fun rightOf(view: View, marginDp: Float? = null, margin: Float? = null): PositionAnimExpectation {
        return PositionAnimExpectationRightOf(view).apply {
            expectations.add(this)
            this.marginDp = marginDp
            this.margin = margin
        }
    }

    fun leftOf(view: View, marginDp: Float? = null, margin: Float? = null): PositionAnimExpectation {
        return PositionAnimExpectationLeftOf(view).apply {
            expectations.add(this)
            this.marginDp = marginDp
            this.margin = margin
        }
    }

    fun belowOf(view: View, marginDp: Float? = null, margin: Float? = null): PositionAnimExpectation {
        return PositionAnimExpectationBelowOf(view).apply {
            expectations.add(this)
            this.marginDp = marginDp
            this.margin = margin
        }
    }

    fun aboveOf(view: View, marginDp: Float? = null, margin: Float? = null): PositionAnimExpectation {
        return PositionAnimExpectationAboveOf(view).apply {
            expectations.add(this)
            this.marginDp = marginDp
            this.margin = margin
        }
    }

    fun originalPosition(): PositionAnimExpectation {
        return PositionAnimExpectationOriginal().apply {
            expectations.add(this)
        }
    }

    fun sameCenterAs(view: View, horizontal: Boolean = false, vertical: Boolean = false): PositionAnimExpectation {
        return PositionAnimExpectationSameCenterAs(view, horizontal, vertical).apply {
            expectations.add(this)
        }
    }

    fun sameCenterHorizontalAs(view: View): PositionAnimExpectation {
        return sameCenterAs(view, true, false).apply {
            expectations.add(this)
        }
    }

    fun sameCenterVerticalAs(view: View): PositionAnimExpectation {
        return sameCenterAs(view, false, true).apply {
            expectations.add(this)
        }
    }

    fun centerInParent(horizontal: Boolean, vertical: Boolean): PositionAnimExpectation {
        return PositionAnimExpectationCenterInParent(horizontal, vertical).apply {
            expectations.add(this)
        }
    }

    fun centerVerticalInParent(): PositionAnimExpectation {
        return centerInParent(false, true).apply {
            expectations.add(this)
        }
    }

    fun centerHorizontalInParent(): PositionAnimExpectation {
        return centerInParent(true, false).apply {
            expectations.add(this)
        }
    }

    fun outOfScreen(@GravityIntDef vararg gravities: Int): PositionAnimExpectation {
        return PositionAnimExpectationOutOfScreen(gravities).apply {
            expectations.add(this)
        }
    }

    fun centerBetweenViews(view1: View, view2: View, horizontal: Boolean = false, vertical: Boolean = false): PositionAnimExpectation {
        return PositionAnimExpectationCenterBetweenViews(view1, view2, horizontal, vertical).apply {
            expectations.add(this)
        }
    }

    fun topOfHisParent(marginDp: Float? = null, margin: Float? = null): PositionAnimExpectation {
        return PositionAnimExpectationTopOfParent().apply {
            expectations.add(this)
            this.marginDp = marginDp
            this.margin = margin
        }
    }

    fun rightOfHisParent(marginDp: Float? = null, margin: Float? = null): PositionAnimExpectation {
        return PositionAnimExpectationRightOfParent().apply {
            expectations.add(this)
            this.marginDp = marginDp
            this.margin = margin
        }
    }

    fun bottomOfHisParent(marginDp: Float? = null, margin: Float? = null): PositionAnimExpectation {
        return PositionAnimExpectationBottomOfParent().apply {
            expectations.add(this)
            this.marginDp = marginDp
            this.margin = margin
        }
    }

    fun leftOfHisParent(marginDp: Float? = null, margin: Float? = null): PositionAnimExpectation {
        return PositionAnimExpectationLeftOfParent().apply {
            expectations.add(this)
            this.marginDp = marginDp
            this.margin = margin
        }
    }

    fun centerBetweenViewAndHisParent(otherView: View, horizontal: Boolean, vertical: Boolean, toBeOnRight: Boolean, toBeOnBottom: Boolean, marginDp: Float? = null, margin: Float? = null): PositionAnimExpectation {
        return PositionAnimExpectationCenterBetweenViewAndParent(otherView, horizontal, vertical, toBeOnRight, toBeOnBottom).apply {
            expectations.add(this)
            this.marginDp = marginDp
            this.margin = margin
        }
    }

    fun alignBottom(otherView: View, marginDp: Float? = null, margin: Float? = null): PositionAnimExpectation {
        return PositionAnimExpectationAlignBottom(otherView).apply {
            expectations.add(this)
            this.marginDp = marginDp
            this.margin = margin
        }
    }

    fun alignTop(otherView: View, marginDp: Float? = null, margin: Float? = null): PositionAnimExpectation {
        return PositionAnimExpectationAlignTop(otherView).apply {
            expectations.add(this)
            this.marginDp = marginDp
            this.margin = margin
        }
    }

    fun alignLeft(otherView: View, marginDp: Float? = null, margin: Float? = null): PositionAnimExpectation {
        return PositionAnimExpectationAlignLeft(otherView).apply {
            expectations.add(this)
            this.marginDp = marginDp
            this.margin = margin
        }
    }

    fun alignRight(otherView: View, marginDp: Float? = null, margin: Float? = null): PositionAnimExpectation {
        return PositionAnimExpectationAlignRight(otherView).apply {
            expectations.add(this)
            this.marginDp = marginDp
            this.margin = margin
        }
    }

    fun originalScale(): ScaleAnimExpectation {
        return ScaleAnimExpectationOriginalScale().apply {
            expectations.add(this)
        }
    }

    fun scale(scaleX: Float, scaleY: Float, @GravityScaleHorizontalIntDef gravityHorizontal: Int, @GravityScaleVerticalIntDef gravityVertical: Int): ScaleAnimExpectation {
        return ScaleAnimExpectationValues(scaleX, scaleY, gravityHorizontal, gravityVertical).apply {
            expectations.add(this)
        }
    }

    fun scale(scaleX: Float, scaleY: Float): ScaleAnimExpectation {
        return ScaleAnimExpectationValues(scaleX, scaleY, null, null).apply {
            expectations.add(this)
        }
    }

    fun height(height: Int, @GravityScaleHorizontalIntDef horizontalGravity: Int? = null, @GravityScaleVerticalIntDef verticalGravity: Int? = null, keepRatio: Boolean = false, toDp: Boolean = false): ScaleAnimExpectation {
        return ScaleAnimExpectationHeight(height, horizontalGravity, verticalGravity).apply {
            this.keepRatio = keepRatio
            this.toDp = toDp
            expectations.add(this)
        }
    }

    fun width(width: Int, @GravityScaleHorizontalIntDef horizontalGravity: Int? = null, @GravityScaleVerticalIntDef verticalGravity: Int? = null, keepRatio: Boolean = false, toDp: Boolean = false): ScaleAnimExpectation {
        return ScaleAnimExpectationWidth(width, horizontalGravity, verticalGravity).apply {
            expectations.add(this)
            this.keepRatio = keepRatio
            this.toDp = toDp
        }
    }

    fun sameScaleAs(otherView: View): ScaleAnimExpectation {
        return ScaleAnimExpectationSameScaleAs(otherView).apply {
            expectations.add(this)
        }
    }

    fun sameWidthAs(otherView: View): ScaleAnimExpectation {
        return ScaleAnimExpectationSameWidthAs(otherView, null, null).apply {
            expectations.add(this)
        }
    }

    fun sameHeightAs(otherView: View): ScaleAnimExpectation {
        return ScaleAnimExpectationSameHeightAs(otherView, null, null).apply {
            expectations.add(this)
        }
    }

    fun toHaveAlpha(alpha: Float): AlphaAnimExpectation {
        return AlphaAnimExpectationValue(alpha).apply {
            expectations.add(this)
        }
    }

    fun sameAlphaAs(otherView: View): AlphaAnimExpectation {
        return AlphaAnimExpectationValue(otherView.alpha).apply {
            expectations.add(this)
        }
    }

    fun visible(): AlphaAnimExpectation {
        return AlphaAnimExpectationValue(1f).apply {
            expectations.add(this)
        }
    }

    fun invisible(): AlphaAnimExpectation {
        return AlphaAnimExpectationValue(0f).apply {
            expectations.add(this)
        }
    }

    fun textColor(textColor: Int): CustomAnimExpectation {
        return TextColorAnimExpectation(textColor).apply {
            expectations.add(this)
        }
    }

    fun backgroundAlpha(alpha: Float): CustomAnimExpectation {
        return ViewBackgroundAlphaAnimExpectation(alpha).apply {
            expectations.add(this)
        }
    }


    fun toBeRotated(rotation: Float): RotationExpectation {
        return RotationExpectationValue(rotation).apply {
            expectations.add(this)
        }
    }

    fun flippedHorizontally(): RotationExpectation {
        return RotationFlipExpectationValue(0f, 180f).apply {
            expectations.add(this)
        }
    }

    fun flippedVertically(): RotationExpectation {
        return RotationFlipExpectationValue(180f, 0f).apply {
            expectations.add(this)
        }
    }

    fun flippedHorizontallyAndVertically(): RotationExpectation {
        return RotationFlipExpectationValue(180f, 180f).apply {
            expectations.add(this)
        }
    }

    fun vertical(bottomOfViewAtLeft: Boolean): RotationExpectation {
        val expect: RotationExpectation
        if (bottomOfViewAtLeft) {
            expect = RotationExpectationValue(90f)
        } else {
            expect = RotationExpectationValue(270f)
        }
        expectations.add(expect)
        return expect
    }

    fun originalRotation(): RotationExpectation {
        return RotationExpectationOriginal().apply {
            expectations.add(this)
        }
    }
}
