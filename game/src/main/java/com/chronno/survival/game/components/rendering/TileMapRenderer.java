package com.chronno.survival.game.components.rendering;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.chronno.survival.game.components.TilemapComponent;

public class TileMapRenderer implements Renderer {

    private static final ComponentMapper<TilemapComponent> TilemapMapper = ComponentMapper.getFor(TilemapComponent.class);
    @Override
    public void render(Entity entity, SpriteBatch spriteBatch, Camera camera, Float delta) {
        TilemapComponent tilemapComponent = TilemapMapper.get(entity);
        OrthogonalTiledMapRenderer renderer = new OrthogonalTiledMapRenderer(tilemapComponent.getMap());
        renderer.setView((OrthographicCamera)camera);
        renderer.render();
    }
}
