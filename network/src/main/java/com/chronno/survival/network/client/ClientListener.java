package com.chronno.survival.network.client;

import com.chronno.survival.network.exception.CommunicationException;
import com.chronno.survival.network.messaging.Message;
import com.chronno.survival.network.server.MessageInterpreter;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

public class ClientListener extends Listener {

	private final MessageInterpreter interpreter;
	public ClientListener(MessageInterpreter interpreter) {
		this.interpreter = interpreter;
	}

	@Override
	public void connected(Connection connection) {
		Log.info(String.format("Connected to: %s, with id: %s", connection.getRemoteAddressTCP(), connection.getID()));
	}

	@Override
	public void disconnected(Connection connection) {
		Log.info(String.format("Disconnected from: %s, with id: %s", connection.getRemoteAddressTCP(), connection.getID()));
	}

	@Override
	public void idle(Connection connection) {
	}

	@Override
	public void received(Connection connection, Object remoteMessage) {
		if (remoteMessage.getClass().equals(Message.class)) {
			interpreter.process((Message) remoteMessage);
		}
	}

}
