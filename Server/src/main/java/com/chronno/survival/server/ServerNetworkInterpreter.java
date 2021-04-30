package com.chronno.survival.server;

import com.chronno.survival.network.messaging.Message;
import com.chronno.survival.network.messaging.MessageInterpreter;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.minlog.Log;

public class ServerNetworkInterpreter implements MessageInterpreter {

    @Override
    public void process(Message message) {
        Log.info(message.toString());
    }

    public void onConnectionSuccess(Connection connection) {

        connection.sendTCP(new Message());
    }
}
