package com.chronno.survival.game.model.spine;

import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Event;

public class SpineAnimationListener implements AnimationState.AnimationStateListener {

    private final SpineAnimationController controller;

    public SpineAnimationListener(SpineAnimationController spineAnimationController) {
        this.controller = spineAnimationController;
    }

    @Override
    public void start(AnimationState.TrackEntry trackEntry) {
        this.controller.onAnimationStatusChange("running");
    }

    @Override
    public void interrupt(AnimationState.TrackEntry trackEntry) {
        this.controller.onAnimationStatusChange("running");
    }

    @Override
    public void end(AnimationState.TrackEntry trackEntry) {
        this.controller.onAnimationStatusChange("running");
    }

    @Override
    public void dispose(AnimationState.TrackEntry trackEntry) {
        this.controller.onAnimationStatusChange("running");
    }

    @Override
    public void complete(AnimationState.TrackEntry trackEntry) {
        this.controller.onAnimationStatusChange("stopped");
    }

    @Override
    public void event(AnimationState.TrackEntry trackEntry, Event event) {

    }
}
