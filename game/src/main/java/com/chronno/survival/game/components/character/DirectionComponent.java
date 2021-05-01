package com.chronno.survival.game.components.character;

import com.chronno.survival.game.components.BaseComponent;
import com.chronno.survival.game.model.Direction;

public final class DirectionComponent extends BaseComponent {

    private Direction direction = Direction.Down;

    @Override
    public void reset() {
        this.direction = Direction.Down;
    }

    public void set(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return this.direction;
    }


    public boolean isSame(Direction direction) {
        return direction.equals(Direction.Empty) || direction.equals(this.direction);
    }
}
