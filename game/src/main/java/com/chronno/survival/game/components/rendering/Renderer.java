package com.chronno.survival.game.components.rendering;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Renderer {

    void render(Entity entity, SpriteBatch spriteBatch, Camera camera, Float delta);
}
