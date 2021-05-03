package com.chronno.survival.game.components.physics;

import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.chronno.survival.game.components.BaseComponent;
import java.util.ArrayList;
import java.util.List;

public class PolygonComponent extends BaseComponent {
    private List<Shape> shapes = new ArrayList<>();


    @Override
    public void reset() {

    }

    public void addShape(PolygonShape shape) {
        this.shapes.add(shape);
    }

    public List<Shape> getShapes() {
        return this.shapes;
    }
}
