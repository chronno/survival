package com.chronno.survival.game.systems.network;

import com.chronno.survival.network.messaging.Message;
import com.chronno.survival.network.messaging.MessageInterpreter;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.minlog.Log;

public class ClientNetworkInterpreter implements MessageInterpreter {
    @Override
    public void process(Message message) {
        Log.info(message.toString());
    }

    @Override
    public void onConnectionSuccess(Connection connection) {

    }
}
