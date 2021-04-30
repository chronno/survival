package com.chronno.survival.server;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.chronno.survival.network.server.GameServer;


public class SurvivalServer implements ApplicationListener {

    private final GameServer server;
    private TiledMap map;

    public SurvivalServer() {
        server = new GameServer(new ServerNetworkInterpreter());
        server.run();
    }

    @Override
    public void create()  {
        map = new TmxMapLoader().load("maps/example.tmx");
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
