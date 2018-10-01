# ExpectAnim

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ExpectAnim-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/5335)
[![CircleCI](https://circleci.com/gh/florent37/ExpectAnim/tree/master.svg?style=svg)](https://circleci.com/gh/florent37/ExpectAnim/tree/master)

Describe your animation and run !


<a href="https://goo.gl/WXW8Dc">
  <img alt="Android app on Google Play" src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>



[![gif](https://raw.githubusercontent.com/florent37/ExpectAnim/master/media/sample.gif)](https://github.com/florent37/ExpectAnim)

```java
new ExpectAnim()

                .expect(avatar)
                .toBe(
                    Expectations...
                )
                .toAnimation()
                .start();
```

# Download

<a href='https://ko-fi.com/A160LCC' target='_blank'><img height='36' style='border:0px;height:36px;' src='https://az743702.vo.msecnd.net/cdn/kofi1.png?v=0' border='0' alt='Buy Me a Coffee at ko-fi.com' /></a>

In your module [![Download](https://api.bintray.com/packages/florent37/maven/ExpectAnim/images/download.svg)](https://bintray.com/florent37/maven/ExpectAnim/_latestVersion)
```groovy
compile 'com.github.florent37:expectanim:1.0.7'
```

This code describe the video above

```java
new ExpectAnim()

                .expect(avatar)
                .toBe(
                        bottomOfParent().withMarginDp(16),
                        leftOfParent().withMarginDp(16),
                        width(40).toDp().keepRatio()
                )

                .expect(name)
                .toBe(
                        toRightOf(avatar).withMarginDp(16),
                        sameCenterVerticalAs(avatar),
                        toHaveTextColor(Color.WHITE)
                )

                .expect(subname)
                .toBe(
                        toRightOf(name).withMarginDp(5),
                        sameCenterVerticalAs(name),
                        toHaveTextColor(Color.WHITE)
                )

                .expect(follow)
                .toBe(
                        rightOfParent().withMarginDp(4),
                        bottomOfParent().withMarginDp(12),
                        toHaveBackgroundAlpha(0f)
                )

                .expect(message)
                .toBe(
                        aboveOf(follow).withMarginDp(4),
                        rightOfParent().withMarginDp(4),
                        toHaveBackgroundAlpha(0f)
                )

                .expect(bottomLayout)
                .toBe(
                        atItsOriginalPosition()
                )

                .expect(content)
                .toBe(
                        atItsOriginalPosition(),
                        visible()
                )

                .toAnimation()
                .setDuration(1500)

                .start();
```

## Follow scroll

[![gif](https://raw.githubusercontent.com/florent37/ExpectAnim/master/media/scroll.gif)](https://github.com/florent37/ExpectAnim)

Use `setPercent` to apply modify the current step of the animation

Exemple with a scrollview

```java
this.expectAnimMove = new ExpectAnim()
                .expect(username)
                .toBe(
                        toRightOf(avatar).withMarginDp(16),
                        sameCenterVerticalAs(avatar),
                        alpha(0.5f)
                )

                .expect(avatar)
                .toBe(
                        topOfParent(),
                        leftOfParent().withMarginDp(16),
                        scale(0.5f, 0.5f)
                )
                .expect(follow)
                .toBe(
                        rightOfParent().withMarginDp(16),
                        sameCenterVerticalAs(avatar)
                )

                .expect(backbground)
                .toBe(
                        height(height).withGravity(Gravity.LEFT|Gravity.START, Gravity.TOP)
                )

                .toAnimation();

scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        final float percent = (scrollY * 1f) / v.getMaxScrollAmount();

        expectAnimMove.setPercent(percent);
    }
});
```

## Concat

You can play an anim after one other using `ExpectAnim.concat` (static method)

```
ExpectAnim.concat(
                new ExpectAnim()
                        .expect(image1)
                        .toBe(
                                withCameraDistance(500f),
                                flippedHorizontally()
                        )
                        .toAnimation()
                        .setDuration(1000),
                new ExpectAnim()
                        .expect(image2)
                        .toBe(
                                withCameraDistance(1000f),
                                flippedVertically()
                        )
                        .toAnimation()
                        .setDuration(500)
                )
                .start()
```

## Apply directly

Use `setNow` to apply directly the transformation

```java
new ExpectAnim()
                .expect(view)
                .toBe(
                        outOfScreen(Gravity.BOTTOM)
                )
                .toAnimation()
                .setNow();
```

## Reset

Use `reset` to return to the initial state of views

```java
expectAnim.reset():
```

# List of expectations

```
new ExpectAnim()
                .expect(view)
                .toBe(

                    //.withMargin(marginPx)
                    //.withMarginDp(margin)
                    //.withMarginDimen(R.dimen.margin)

                    toRightOf(view)
                    toLeftOf(view)
                    belowOf(view)
                    aboveOf(view)

                    atItsOriginalPosition()


                    sameCenterAs(view, horizontal, vertical)
                    sameCenterHorizontalAs(view)
                    sameCenterVerticalAs(view)
                    centerInParent(horizontal, vertical)
                    centerVerticalInParent()
                    centerHorizontalInParent()

                    centerBetweenViews(view1, view2, horizontal, vertical)
                    centerBetweenViewAndParent(otherView, horizontal, vertical, toBeOnRight, toBeOnBottom)

                    topOfParent()
                    rightOfParent()
                    bottomOfParent()
                    leftOfParent()

                    alignBottom(otherView)
                    alignTop(otherView)
                    alignLeft(otherView)
                    alignRight(otherView)

                    outOfScreen(gravitiy)  //Gravity.LEFT / Gravity.RIGHT / Gravity.TOP / Gravity.BOTTOM

                    alpha(alpha)
                    sameAlphaAs(otherView)
                    visible()
                    invisible()

                    //.keepRatio()
                    //.withGravity(horizontalGravity, verticalGravity)

                    atItsOriginalScale()

                    scale(scaleX, scaleY)
                    height(height)
                    width(width)
                    sameScaleAs(otherView)
                    sameWidthAs(otherView)
                    sameHeightAs(otherView)


                    toHaveTextColor(textColor)
                    toHaveBackgroundAlpha(alpha)

                    rotated(rotation)
                    vertical(bottomOfViewAtLeft)
                    atItsOriginalRotation()
                )

````

# Proguard

```
-keep class com.github.florent37.expectanim.*{ *; }
-dontwarn com.github.florent37.expectanim.**
```

# Changelog

## 1.0.7

Added startDelay

## 1.0.6

Added ExpectAnim.concat

## 1.0.4

Fixed listeners references

## 1.0.3

Alpha 0 force view to be INVISIBLE`

## 1.0.2

Added `flips` rotations

`flippedHorizontally()`
`flippedVertically()`
`flippedHorizontallyAndVertically()`
`withCameraDistance(1000f)`

## 1.0.1

Added `rotations`

[![gif](https://raw.githubusercontent.com/florent37/ExpectAnim/master/media/rotations.gif)](https://github.com/florent37/ExpectAnim)

# Credits

Author: Florent Champigny [http://www.florentchampigny.com/](http://www.florentchampigny.com/)

Blog : [http://www.tutos-android-france.com/](http://www.tutos-android-france.com/)

Fiches Plateau Moto : [https://www.fiches-plateau-moto.fr/](https://www.fiches-plateau-moto.fr/)

<a href="https://goo.gl/WXW8Dc">
  <img alt="Android app on Google Play" src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>

<a href="https://plus.google.com/+florentchampigny">
  <img alt="Follow me on Google+"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/gplus.png" />
</a>
<a href="https://twitter.com/florent_champ">
  <img alt="Follow me on Twitter"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/twitter.png" />
</a>
<a href="https://fr.linkedin.com/in/florentchampigny">
  <img alt="Follow me on LinkedIn"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/linkedin.png" />
</a>

# License

    Copyright 2017 florent37, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
