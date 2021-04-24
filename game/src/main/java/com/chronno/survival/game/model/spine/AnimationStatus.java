package com.chronno.survival.game.model.spine;

public class AnimationStatus {
    
    private String animationName;
    private Boolean interruptible;
    private String status;

    public AnimationStatus(String animationName, Boolean interruptible, String status) {
        this.animationName = animationName;
        this.interruptible = interruptible;
        this.status = status;
    }

    public String getAnimationName() {
        return animationName;
    }

    public void setAnimationName(String animationName) {
        this.animationName = animationName;
    }

    public Boolean getInterruptible() {
        return interruptible;
    }

    public void setInterruptible(Boolean interruptible) {
        this.interruptible = interruptible;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void set(AnimationRequest nextAnimation) {
        this.animationName = nextAnimation.getAnimationName();
        this.interruptible = nextAnimation.getInterruptible();
    }
}
