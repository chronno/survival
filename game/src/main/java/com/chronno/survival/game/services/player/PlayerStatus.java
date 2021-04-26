package com.chronno.survival.game.services.player;

import com.chronno.survival.game.model.character.Animation;
import com.chronno.survival.game.model.character.Direction;

public class PlayerStatus {

    private Direction direction;
    private Animation animation;
    private AnimationStatus animationStatus;

    public PlayerStatus(Direction direction, Animation animation, AnimationStatus animationStatus) {
        this.direction = direction;
        this.animation = animation;
        this.animationStatus = animationStatus;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public void setAnimationStatus(AnimationStatus animationStatus) {
        this.animationStatus = animationStatus;
    }

    public AnimationStatus getAnimationStatus() {
        return this.animationStatus;
    }
}
