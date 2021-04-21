package com.chronno.survival.network.server;

import java.io.IOException;
import java.util.Optional;

import com.chronno.survival.network.exception.ServerStartupException;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

public class GameServer {

	private static final Integer DEFAULT_TCP_PORT = 54555;
	private static final Integer DEFAULT_UDP_PORT = 54777;
	private final Server server;
	private final Kryo kryo;
	private Optional<Integer> tcpPort;
	private Optional<Integer> udpPort;
	
	public GameServer() {
		this.server = new Server();
		this.server.addListener(new ServerListener());
		this.kryo = server.getKryo();
		this.udpPort = Optional.empty();
		this.tcpPort = Optional.empty();
	}

	public void bindPorts(Integer tcpPort, Integer udpPort) {
		this.tcpPort = Optional.of(tcpPort);
		this.udpPort =  Optional.of(udpPort);
	}
	
	
	public void run() {
		try {
			this.server.bind(this.tcpPort.orElse(DEFAULT_TCP_PORT), this.udpPort.orElse(DEFAULT_UDP_PORT));
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
		return tcpPort.get();
	}

	public Integer getUdpPort() {
		return udpPort.get();
	}

	public Kryo getKryo() {
		return kryo;
	}
}
