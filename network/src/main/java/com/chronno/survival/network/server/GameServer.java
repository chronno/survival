package com.chronno.survival.network.server;

import java.io.IOException;
import java.util.Optional;

import com.chronno.survival.network.exception.ServerStartupException;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

public class GameServer {

	private static final Integer DEFAULT_TCP_PORT = 25565;
	private static final Integer DEFAULT_UDP_PORT = 25575;
	private final Server server;
	private final Kryo kryo;
	private Integer tcpPort;
	private Integer udpPort;
	
	public GameServer() {
		this.server = new Server();
		this.server.addListener(new ServerListener(this.server));
		this.kryo = server.getKryo();
	}

	public void bindPorts(Integer tcpPort, Integer udpPort) {
		this.tcpPort = tcpPort;
		this.udpPort =  udpPort;
	}
	
	
	public void run() {
		try {
			this.server.bind(Optional.ofNullable(tcpPort).orElse(DEFAULT_TCP_PORT), Optional.ofNullable(udpPort).orElse(DEFAULT_UDP_PORT));
			this.server.run();
			Log.info("server is running");
		} catch (IOException e) {
			Log.info("server failed to start");
			throw new ServerStartupException("Server failed to start", e);
		}
	}
	
	public Server getServer() {
		return server;
	}

	public Integer getTcpPort() {
		return tcpPort;
	}

	public Integer getUdpPort() {
		return udpPort;
	}

	public Kryo getKryo() {
		return kryo;
	}
}
