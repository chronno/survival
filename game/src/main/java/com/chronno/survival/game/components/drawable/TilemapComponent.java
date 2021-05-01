package com.chronno.survival.game.components.drawable;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.chronno.survival.game.components.BaseComponent;

public final class TilemapComponent extends BaseComponent {

    private static final String MapsPath = "maps/";

    private TiledMap tiledMap;

    @Override
    public void reset() {
        tiledMap.dispose();
        tiledMap = null;
    }

    public void set(String mapPath) {
        tiledMap = new TmxMapLoader().load(MapsPath.concat(mapPath));
    }

    public TiledMap getMap() {
        return tiledMap;
    }
}
