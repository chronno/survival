package com.chronno.survival.game.services.player;

import com.chronno.survival.game.model.character.Animation;
import com.chronno.survival.game.model.character.Direction;

import java.util.Objects;

public class QueuedAnimation {

    public static QueuedAnimation Default = new QueuedAnimation(Animation.Idle, Direction.Empty);
    private final Animation animation;
    private final Direction direction;

    public QueuedAnimation(Animation animation, Direction direction) {
        this.animation = animation;
        this.direction = direction;
    }

    public Animation getAnimation() {
        return animation;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueuedAnimation that = (QueuedAnimation) o;
        return animation == that.animation && direction == that.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(animation, direction);
    }
}
