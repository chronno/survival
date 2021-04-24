package com.chronno.survival.game.model.spine;

public class AnimationRequest {

    private final String animationName;
    private final Boolean interruptible;

    public AnimationRequest(String animationName, Boolean interruptible) {
        this.animationName = animationName;
        this.interruptible = interruptible;
    }

    public String getAnimationName() {
        return animationName;
    }

    public Boolean getInterruptible() {
        return interruptible;
    }
}
