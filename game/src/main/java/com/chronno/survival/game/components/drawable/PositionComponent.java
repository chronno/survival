package com.chronno.survival.game.components.drawable;

import com.chronno.survival.game.components.BaseComponent;

public final class PositionComponent extends BaseComponent {
    private Integer x = 512;
    private Integer y = 512;

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

    public void set(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public void update(Integer x, Integer y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public void reset() {
        x = 0;
        y = 0;
    }
}
