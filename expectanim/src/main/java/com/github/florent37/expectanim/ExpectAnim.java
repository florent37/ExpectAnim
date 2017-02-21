package com.github.florent37.expectanim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Interpolator;

import com.github.florent37.expectanim.listener.AnimationEndListener;
import com.github.florent37.expectanim.listener.AnimationStartListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by florentchampigny on 17/02/2017.
 */

public class ExpectAnim {

    private static final long DEFAULT_DURATION = 300l;

    private List<ViewExpectation> expectationList;
    private View anyView;

    private List<View> viewToMove;
    private ViewCalculator viewCalculator;

    private AnimatorSet animatorSet;

    private WeakReference<AnimationEndListener> endListenerWeakReference;
    private WeakReference<AnimationStartListener> startListenerWeakReference;


    @Nullable
    private Interpolator interpolator;
    private Long duration = DEFAULT_DURATION;

    public ExpectAnim() {
        this.expectationList = new ArrayList<>();
        this.viewToMove = new ArrayList<>();
        this.viewCalculator = new ViewCalculator();
    }

    public ViewExpectation expect(View view) {
        this.anyView = view;
        final ViewExpectation viewExpectation = new ViewExpectation(this, view);
        expectationList.add(viewExpectation);
        return viewExpectation;
    }

    private ExpectAnim calculate() {
        if (animatorSet == null) {
            animatorSet = new AnimatorSet();

            if (interpolator != null) {
                animatorSet.setInterpolator(interpolator);
            }

            animatorSet.setDuration(duration);

            final List<Animator> animatorList = new ArrayList<>();

            final List<ViewExpectation> expectationsToCalculate = new ArrayList<>();

            //"ViewDependencies" = récupérer toutes les vues des "Expectations"
            for (ViewExpectation viewExpectation : expectationList) {
                viewExpectation.calculateDependencies();
                viewToMove.add(viewExpectation.getViewToMove());
                expectationsToCalculate.add(viewExpectation);
            }

            while (!expectationsToCalculate.isEmpty()) {
                //pour chaque expectation dans "Expectations"
                final Iterator<ViewExpectation> iterator = expectationsToCalculate.iterator();
                while (iterator.hasNext()) {
                    final ViewExpectation viewExpectation = iterator.next();

                    //regarder si une de ces dépendance est dans "ViewDependencies"
                    if (!hasDependency(viewExpectation)) {
                        //si non
                        viewExpectation.calculate(viewCalculator);
                        animatorList.addAll(viewExpectation.getAnimations());

                        final View view = viewExpectation.getViewToMove();
                        viewToMove.remove(view);
                        viewCalculator.wasCalculated(view, viewExpectation);

                        iterator.remove();
                    } else {
                        //si oui, attendre le prochain tour
                    }
                }
            }

            animatorSet.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    notifyListenerEnd();
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    notifyListenerStart();
                }

            });

            animatorSet.playTogether(animatorList);
        }
        return this;
    }

    private void notifyListenerStart() {
        if (startListenerWeakReference != null) {
            final AnimationStartListener listener = startListenerWeakReference.get();
            if (listener != null) {
                listener.onAnimationStart(ExpectAnim.this);
            }
        }
    }

    private void notifyListenerEnd() {
        if (endListenerWeakReference != null) {
            final AnimationEndListener listener = endListenerWeakReference.get();
            if (listener != null) {
                listener.onAnimationEnd(ExpectAnim.this);
            }
        }
    }


    public ExpectAnim start() {
        executeAfterDraw(anyView, new Runnable() {
            @Override
            public void run() {
                calculate();
                animatorSet.start();
            }
        });
        return this;
    }

    private boolean hasDependency(ViewExpectation viewExpectation) {
        final List<View> dependencies = viewExpectation.getDependencies();
        if (!dependencies.isEmpty()) {
            for (View view : viewToMove) {
                if (dependencies.contains(view)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setPercent(float percent) {
        calculate();
        if (animatorSet != null) {
            final ArrayList<Animator> anims = animatorSet.getChildAnimations();
            for (Animator animator : anims) {
                if (animator instanceof ValueAnimator) {
                    ((ValueAnimator) animator).setCurrentPlayTime((long) (percent * animator.getDuration()));
                }
            }
        }
    }

    public void setNow() {
        executeAfterDraw(anyView, new Runnable() {
            @Override
            public void run() {
                setPercent(1f);
            }
        });
    }

    public void executeAfterDraw(final View view, final Runnable runnable) {
        view.postDelayed(runnable, 50);
    }

    public void reset() {
        final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "percent", 1f, 0f);
        objectAnimator.setDuration(duration);
        if (interpolator != null) {
            objectAnimator.setInterpolator(interpolator);
        }
        objectAnimator.start();
    }

    public ExpectAnim setInterpolator(@NonNull Interpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }

    public ExpectAnim setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    public ExpectAnim setEndListener(AnimationEndListener listener) {
        this.endListenerWeakReference = new WeakReference<>(listener);
        return this;
    }

    public ExpectAnim setStartListener(AnimationStartListener listener) {
        this.startListenerWeakReference = new WeakReference<>(listener);
        return this;
    }

}
