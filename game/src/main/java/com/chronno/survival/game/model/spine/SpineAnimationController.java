package com.chronno.survival.game.model.spine;

import games.rednblack.editor.renderer.components.SpineDataComponent;
import games.rednblack.h2d.extention.spine.SpineObjectComponent;

import java.util.Stack;

public class SpineAnimationController {

    private final SpineObjectComponent spineComponent;
    private final Stack<AnimationRequest> animationQueue = new Stack<>();
    private AnimationStatus currentAnimation;

    public SpineAnimationController(SpineObjectComponent spineObject, SpineDataComponent spineData) {
        spineComponent = spineObject;
        spineComponent.getState().addListener(new SpineAnimationListener(this));
        currentAnimation = new AnimationStatus(spineData.currentAnimationName, true, "running");
    }

    public void requestAnimation(AnimationRequest animationRequest) {
        animationQueue.push(animationRequest);
        updateCurrentAnimation();
    }

    public void updateCurrentAnimation() {
        AnimationRequest nextAnimation = animationQueue.peek();
        if(canChangeAnimation(nextAnimation)) {
            spineComponent.setAnimation(nextAnimation.getAnimationName());
            currentAnimation.set(nextAnimation);
        }
    }

    public void onAnimationStatusChange(String animationStatus) {
        this.currentAnimation.setStatus(animationStatus);
    }

    private Boolean canChangeAnimation(AnimationRequest nextAnimation) {
        boolean isIterruptible = currentAnimation.getInterruptible();
        boolean isRunning = currentAnimation.getStatus().equals("running");
        boolean isSame = nextAnimation.getAnimationName().equals(currentAnimation.getAnimationName());
        return !isSame && (isIterruptible || !isRunning);
    }
}
