package com.chronno.survival.game.components.rendering;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.chronno.survival.game.components.BaseComponent;

public class RendererComponent extends BaseComponent {

    private Renderer renderer;

    public void set(Renderer renderer) {
        this.renderer = renderer;
    }

    public void draw(Entity entity, SpriteBatch spriteBatch, Camera camera, Float delta) {
        renderer.render(entity, spriteBatch,camera, delta);
    }

    @Override
    public void reset() {

    }
}
