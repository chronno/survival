package com.chronno.survival.game.services.player;

import com.chronno.survival.game.model.character.Animation;
import com.chronno.survival.game.model.character.Direction;
import games.rednblack.h2d.extention.spine.SpineObjectComponent;

public class SpineAnimationsController {

    private final SpineObjectComponent component;

    public SpineAnimationsController(SpineObjectComponent component) {
        this.component = component;
    }

    public void setAnimation(Animation animation, Direction direction) {
        String animationName = getAnimationName(animation, direction);
        component.setAnimation(animationName);
    }

    private String getAnimationName(Animation animation, Direction direction) {
        return String.format("%s %s", direction.skeletonName(), animation);
    }
}
