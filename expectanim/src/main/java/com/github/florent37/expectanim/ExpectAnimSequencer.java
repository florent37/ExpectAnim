package com.github.florent37.expectanim;

import com.github.florent37.expectanim.listener.AnimationEndListener;
import com.github.florent37.expectanim.listener.AnimationStartListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gimbert on 09/03/2017.
 */

public class ExpectAnimSequencer {
    private List<ExpectAnim> anims = new ArrayList<>();

    private WeakReference<AnimationEndListener> endListenerWeakReference;
    private WeakReference<AnimationStartListener> startListenerWeakReference;

    public ExpectAnimSequencer playSequencially(ExpectAnim... anims) {
        for (int i = 0; i < anims.length; i++) {
            this.anims.add(anims[i]);
        }
        return this;
    }

    public ExpectAnimSequencer setEndListener(AnimationEndListener listener) {
        endListenerWeakReference = new WeakReference<>(listener);
        return this;
    }

    public ExpectAnimSequencer setStartListener(AnimationStartListener listener) {
        startListenerWeakReference = new WeakReference<>(listener);
        return this;
    }

    public void reset() {
        startAnim(anims.size() - 1, true);
    }

    public void start() {
        startAnim(0, false);
    }

    private void startAnim(int currentIndex, boolean reverse) {
        if ((currentIndex >= anims.size() && !reverse) || (currentIndex < 0 && reverse)) {
            if (endListenerWeakReference != null && endListenerWeakReference.get() != null) {
                endListenerWeakReference.get().onAnimationEnd(anims.get(reverse ? 0 : anims.size() - 1));
            }
            return;
        }
        ExpectAnim anim = anims.get(currentIndex);
        anim.setTag(new Object[] {reverse, currentIndex});
        anim.setEndListener(new AnimationEndListener() {
            @Override
            public void onAnimationEnd(ExpectAnim expectAnim) {
                expectAnim.setEndListener(null);
                Object[] tag = (Object[]) expectAnim.getTag();
                boolean reverse;
                int currentAnimIndex = 0;
                if (tag == null) {
                    reverse = false;
                } else {
                    reverse = ((Boolean) tag[0]);
                    currentAnimIndex = ((Integer) tag[1]);
                }
                if (reverse) {
                    currentAnimIndex--;
                } else {
                    currentAnimIndex++;
                }
                startAnim(currentAnimIndex, reverse);
            }
        });
        if (startListenerWeakReference != null && startListenerWeakReference.get() != null) {
            startListenerWeakReference.get().onAnimationStart(anims.get(currentIndex));
        }
        if (reverse) {
            anim.reset();
        } else {
            anim.start();
        }
    }
}
