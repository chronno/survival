package com.chronno.survival.game.components;

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

    public enum Direction {
        Left,
        Right,
        Up,
        Down,
        Empty
    }
}
