package com.chronno.survival.network.server;

import com.chronno.survival.network.exception.CommunicationException;
import com.chronno.survival.network.messaging.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

public class ServerListener extends Listener {

	private final Server server;
	private final MessageInterpreter interpreter;
	public ServerListener(Server server, MessageInterpreter interpreter) {
		this.server = server;
		this.interpreter = interpreter;
	}

	@Override
	public void connected(Connection connection) {
		Log.info(String.format("Connection from: %s, with id: %s", connection.getRemoteAddressTCP(), connection.getID()));

	}

	@Override
	public void disconnected(Connection connection) {
		Log.info(String.format("Disconnection from: %s, with id: %s", connection.getRemoteAddressTCP(), connection.getID()));

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

	private void broadcast(Object message) {
		this.server.sendToAllTCP(message);
	}
}
