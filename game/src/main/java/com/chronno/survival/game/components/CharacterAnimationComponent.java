package com.chronno.survival.game.components;

import static com.chronno.survival.game.components.DirectionComponent.*;

public class CharacterAnimationComponent extends AnimationComponent {

    private static final String JsonNameStructure = "%s Character %s.json";
    private static final String AnimNameStructure = "%s %s";

    private Direction direction = Direction.Down;


    public void setDirection(Direction currentDirection) {
        if (shouldChangeSkeleton(currentDirection)) {
            set(atlasPath, getUpdatedJsonPath(currentDirection), getUpdatedAnimation(currentDirection, "Walk"));
            direction = currentDirection;
        }
    }

    @Override
    public void setAnimation(String animation, Boolean loop) {
        String animationName = getUpdatedAnimation(direction, animation);
        if (canChangeAnimation(animationName)) {
            super.setAnimation(animationName, loop);
        }
    }

    private String getUpdatedJsonPath(Direction currentDirection) {
        if (currentDirection.equals(Direction.Left) || currentDirection.equals(Direction.Right)) {
            return String.format(JsonNameStructure, "Male", "Side");
        } else {
            return String.format(JsonNameStructure, "Male", currentDirection.name());
        }
    }

    private String getUpdatedAnimation(Direction currentDirection, String animation) {
        if (currentDirection.equals(Direction.Left) || currentDirection.equals(Direction.Right)) {
            return String.format(AnimNameStructure, "Side", animation);
        } else {
            return String.format(AnimNameStructure, currentDirection.name(), animation);
        }
    }

    private Boolean shouldChangeSkeleton(Direction requestedDirection) {
        return !jsonPath.equals(getUpdatedJsonPath(requestedDirection));

    }
}
