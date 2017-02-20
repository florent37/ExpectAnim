package com.github.florent37.expectanim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by florentchampigny on 17/02/2017.
 */

public class ExpectAnim {

    private List<ViewExpectation> expectationList;
    private View anyView;

    private List<View> viewToMove;
    private ViewCalculator viewCalculator;

    private AnimatorSet animatorSet;

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

    private ExpectAnim calculate(){
        if(animatorSet == null) {
            animatorSet = new AnimatorSet();
            anyView.post(new Runnable() {
                @Override
                public void run() {
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

                    animatorSet.playTogether(animatorList);
                }
            });
        }
        return this;
    }

    public ExpectAnim start() {
        calculate();
        anyView.post(new Runnable() {
            @Override
            public void run() {
                animatorSet.start();
            }
        });
        return this;
    }

    private boolean hasDependency(ViewExpectation viewExpectation) {
        final List<View> dependencies = viewExpectation.getDependencies();
        if (!dependencies.isEmpty()) {
            for(View view : viewToMove){
                if(dependencies.contains(view)){
                    return true;
                }
            }
        }
        return false;
    }

    public void setPercent(float percent) {
        calculate();
        if(animatorSet != null) {
            final ArrayList<Animator> anims = animatorSet.getChildAnimations();
            for (Animator animator : anims) {
                if (animator instanceof ValueAnimator) {
                    ((ValueAnimator) animator).setCurrentPlayTime((long) (percent * animator.getDuration()));
                }
            }
        }
    }
}
