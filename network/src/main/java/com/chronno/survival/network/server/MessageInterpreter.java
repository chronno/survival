package com.chronno.survival.network.server;

import com.chronno.survival.network.messaging.Message;

public interface MessageInterpreter {

    void process(Message message);

}
