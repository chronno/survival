package com.chronno.survival.game.components;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TilemapComponent extends BaseComponent {

    private TiledMapRenderer tiledMapRenderer;
    private TiledMap tiledMap;

    @Override
    public void reset() {

    }

    public void set(String mapPath) {
        tiledMap = new TmxMapLoader().load(mapPath);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

    }
}
