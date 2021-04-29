package com.chronno.survival.game.components;

public final class PositionComponent extends BaseComponent {
    private Integer x = 512;
    private Integer y = 512;

    public void set(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public void reset() {

    }

    public void move(DirectionComponent.Direction direction, int value) {
        switch (direction) {
            case Empty:
                break;
            case Down:
                y -= value;
                break;
            case Up:
                y += value;
                break;
            case Left:
                x -= value;
                break;
            case Right:
                x += value;
                break;
        }

    }
}
