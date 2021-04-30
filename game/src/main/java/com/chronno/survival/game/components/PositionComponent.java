package com.chronno.survival.game.components;

public final class PositionComponent extends BaseComponent {
    private Integer x;
    private Integer y;

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
        x = 0;
        y = 0;
    }
}
