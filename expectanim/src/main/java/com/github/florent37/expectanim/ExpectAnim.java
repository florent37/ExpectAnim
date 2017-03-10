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
    private List<Integer> sequenceIndexes;
    private View anyView;
    private Object tag;

    private List<View> viewToMove;
    private ViewCalculator viewCalculator;

    private AnimatorSet animatorSet;
    private AnimatorSet reverseAnimatorSet;

    private WeakReference<AnimationEndListener> endListenerWeakReference;
    private WeakReference<AnimationStartListener> startListenerWeakReference;


    @Nullable
    private Interpolator interpolator;
    private Long duration = DEFAULT_DURATION;

    public ExpectAnim() {
        this.expectationList = new ArrayList<>();
        this.viewToMove = new ArrayList<>();
        this.viewCalculator = new ViewCalculator();
        this.sequenceIndexes = new ArrayList<>();
    }

    public ViewExpectation expect(View view) {
        this.anyView = view;
        final ViewExpectation viewExpectation = new ViewExpectation(this, view);
        expectationList.add(viewExpectation);
        return viewExpectation;
    }

    public ViewExpectation expectInNewSequence(View view) {
        if (!expectationList.isEmpty()) {
            sequenceIndexes.add(expectationList.size());
        }
        return expect(view);
    }

    private ExpectAnim calculate() {
        if (animatorSet == null) {
            animatorSet = generateNewAnimatorSet();
            if (interpolator != null) {
                animatorSet.setInterpolator(interpolator);
            }
            animatorSet.setDuration(duration);

//            final List<Animator> animatorList = new ArrayList<>();

            final List<ViewExpectation> expectationsToCalculate = new ArrayList<>();

            //"ViewDependencies" = récupérer toutes les vues des "Expectations"
            for (ViewExpectation viewExpectation : expectationList) {
                viewExpectation.calculateDependencies();
                viewToMove.add(viewExpectation.getViewToMove());
                expectationsToCalculate.add(viewExpectation);

                viewCalculator.setExpectationForView(viewExpectation.getViewToMove(), viewExpectation);
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
//                        animatorList.addAll(viewExpectation.getAnimations());

                        final View view = viewExpectation.getViewToMove();
                        viewToMove.remove(view);
                        viewCalculator.wasCalculated(viewExpectation);

                        iterator.remove();
                    } else {
                        //si oui, attendre le prochain tour
                    }
                }
            }

            //build animatorSets
            List<List<Animator>> animatorSequentialLists = new ArrayList<>();
            List<Animator> sequenceAnimatorList = new ArrayList<>();
            animatorSequentialLists.add(sequenceAnimatorList);
            for (int i = 0; i < expectationList.size(); i++) {
                if (sequenceIndexes.contains(i) && !sequenceAnimatorList.isEmpty()) {
                    sequenceAnimatorList = new ArrayList<>();
                    animatorSequentialLists.add(sequenceAnimatorList);
                }
                sequenceAnimatorList.addAll(expectationList.get(i).getAnimations());
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

//            if (animatorSequentialLists.size() == 1) {
//                animatorSet.playTogether(animatorSequentialLists.get(0));
//            } else {
                List<Animator> sets = new ArrayList<>();
                for (int i = 0; i < animatorSequentialLists.size(); i++) {
                    AnimatorSet sequenceAnimator = generateNewAnimatorSet();
                    sequenceAnimator.playTogether(animatorSequentialLists.get(i));
                    sets.add(sequenceAnimator);
                }
                animatorSet.playSequentially(sets);
//            }
        }
        return this;

    }

    private AnimatorSet generateNewAnimatorSet() {
        AnimatorSet set = new AnimatorSet();
        return set;
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
        percent *= 1f;
        final List<Animator> anims = animatorSet.getChildAnimations();
        int numberOfSequences = anims.size();
        float sequenceShare = 1f / numberOfSequences;
        List<Float> percents = new ArrayList<>();
        for (int i = 1; i <= numberOfSequences; i++) {
            if (percent >= i * sequenceShare) {
                percents.add(1f);
            } else if (percent < sequenceShare * (i - 1)) {
                percents.add(0f);
            } else {
                percents.add((percent - sequenceShare * (i - 1)) * numberOfSequences);
            }
        }
        for (int i = 0; i < anims.size(); i++) {
            Animator animator = anims.get(i);
            if (animator instanceof AnimatorSet) {
                List<Animator> childAnimations = ((AnimatorSet) animator).getChildAnimations();
                for (int j = 0; j < childAnimations.size(); j++) {
                    Animator anim1 = childAnimations.get(j);
                    if (anim1 instanceof ValueAnimator) {
                        ((ValueAnimator) anim1).setCurrentPlayTime((long) (percents.get(i) * animator.getDuration()));
                    }
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
        view.postDelayed(runnable, 5);
    }

    public void reset() {
        final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "percent", 1f, 0f);
        objectAnimator.setDuration(duration);
        if (interpolator != null) {
            objectAnimator.setInterpolator(interpolator);
        }
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                notifyListenerStart();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                notifyListenerEnd();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
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

    public Object getTag() {
        return tag;
    }

    public ExpectAnim setTag(Object tag) {
        this.tag = tag;
        return this;
    }
}
