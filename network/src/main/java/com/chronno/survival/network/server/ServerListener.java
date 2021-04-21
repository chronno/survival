package com.chronno.survival.network.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

public class ServerListener extends Listener {

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
	public void received(Connection connection, Object message) {
		
	}

}
