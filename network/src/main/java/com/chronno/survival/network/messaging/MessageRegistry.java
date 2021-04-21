package com.chronno.survival.network.messaging;

import com.esotericsoftware.kryo.Kryo;

public class MessageRegistry {

    public static void register(Kryo source) {
        source.register(Message.class);
    }
}
