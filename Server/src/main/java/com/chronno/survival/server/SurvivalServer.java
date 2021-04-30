package com.chronno.survival.server;

import com.badlogic.gdx.Game;
import com.chronno.survival.network.server.GameServer;


public class SurvivalServer extends Game {

    private final GameServer server;


    public SurvivalServer() {
        server = new GameServer(new ServerNetworkInterpreter());
    }

    @Override
    public void create()  {
        server.run();
    }

    @Override
    public void dispose() {
        server.getServer().stop();
    }
}
