package com.chronno.survival.game.services.network;

import com.chronno.survival.network.client.GameClient;

public class NetworkController {

    private final GameClient gameClient;

        public NetworkController() {
        gameClient = new GameClient(new NetworkInterpreter());

    }

    public void update() {

    }

    public void disconect() {

    }

}
