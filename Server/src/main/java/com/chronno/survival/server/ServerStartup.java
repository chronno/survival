package com.chronno.survival.server;

import com.chronno.survival.network.server.GameServer;

public class ServerStartup {
	
	public static void main(String[] args) {
		GameServer server = new GameServer(new NetworkInterpreter());
		server.run();
	}
}
