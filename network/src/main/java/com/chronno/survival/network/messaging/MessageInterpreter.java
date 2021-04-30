package com.chronno.survival.network.messaging;

import com.esotericsoftware.kryonet.Connection;

public interface MessageInterpreter {

    void process(Message message);

    void onConnectionSuccess(Connection connection);

}
