package com.chronno.survival.game.services.player;

import com.chronno.survival.game.model.character.Animation;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Event;

import static com.esotericsoftware.spine.Animation.MixBlend.replace;


public class SpineAnimationListener implements AnimationState.AnimationStateListener {

    private final PlayerAnimationService playerAnimationService;

    public SpineAnimationListener(PlayerAnimationService playerAnimationService) {
        this.playerAnimationService = playerAnimationService;
    }

    @Override
    public void start(AnimationState.TrackEntry trackEntry) {
        Animation anim = getManagedAnimation(trackEntry);
        playerAnimationService.onAnimationStatusChange(anim, AnimationStatus.Started);
    }

    @Override
    public void interrupt(AnimationState.TrackEntry trackEntry) {
        Animation anim = getManagedAnimation(trackEntry);
        playerAnimationService.onAnimationStatusChange(anim, AnimationStatus.Interrupted);
    }

    @Override
    public void end(AnimationState.TrackEntry trackEntry) {
        Animation anim = getManagedAnimation(trackEntry);
        playerAnimationService.onAnimationStatusChange(anim, AnimationStatus.Ended);
    }

    @Override
    public void dispose(AnimationState.TrackEntry trackEntry) {
        Animation anim = getManagedAnimation(trackEntry);
        playerAnimationService.onAnimationStatusChange(anim, AnimationStatus.Disposed);
    }

    @Override
    public void complete(AnimationState.TrackEntry trackEntry) {
        Animation anim = getManagedAnimation(trackEntry);
        playerAnimationService.onAnimationStatusChange(anim, AnimationStatus.Completed);
    }

    @Override
    public void event(AnimationState.TrackEntry trackEntry, Event event) { }

    private Animation getManagedAnimation(AnimationState.TrackEntry trackEntry) {
        var animation = trackEntry.getAnimation();
        return Animation.valueOf(animation.getName().split(" ")[1]);
    }
}
