package com.chronno.survival.server;

import com.chronno.survival.network.server.GameServer;
import com.esotericsoftware.minlog.Log;

public class ServerStartup {
	
	public static void main(String[] args) {
		GameServer server = new GameServer();
		server.run();
	}
}
