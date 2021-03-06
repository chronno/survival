package com.chronno.survival.game.components.drawable;

import com.chronno.survival.game.components.BaseComponent;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;

public final class AnimationComponent extends BaseComponent {

    private AnimationState animationState;
    private AnimationStateData animationStateData;

    public AnimationState getAnimationState() {
        return animationState;
    }

    public void set(Skeleton skeleton, String animation) {
        if (canChangeAnimation(animation)) {
            animationStateData = new AnimationStateData(skeleton.getData());
            animationState = new AnimationState(animationStateData);
        }
    }

    public void update(Skeleton skeleton, String animation, Boolean loop) {
        if (canChangeAnimation(animation)) {
            animationStateData = new AnimationStateData(skeleton.getData());
            animationState = new AnimationState(animationStateData);
            animationState.setAnimation(0, animation, loop);
        }
    }

    public Boolean canChangeAnimation(String animation) {
        if (animationState == null) { return true; }
        AnimationState.TrackEntry currentAnimation = animationState.getCurrent(0);
        if (currentAnimation == null) { return true; }
        boolean isSame = currentAnimation.getAnimation().getName().equals(animation);
        boolean canBeInterrupted = currentAnimation.getLoop() || currentAnimation.isComplete();
        return !isSame && canBeInterrupted;
    }

    @Override
    public void reset() {
        animationState = null;
        animationStateData = null;
    }
}
