package com.chronno.survival.game.systems.network;

import com.badlogic.ashley.core.EntitySystem;
import com.chronno.survival.network.client.GameClient;
import com.chronno.survival.network.messaging.Message;
import com.esotericsoftware.minlog.Log;

public class NetworkSystem extends EntitySystem {
    private GameClient client;

    public NetworkSystem(int priority) {
        super(priority);
        try {
            client = new GameClient(new ClientNetworkInterpreter());
            client.connect("181.164.136.246", 25565, 25575);
        } catch (Exception e) {
            Log.info("cannot connect");
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Message message = new Message();
        message.setType("Login");
        client.sendMessage(message);
    }
}
