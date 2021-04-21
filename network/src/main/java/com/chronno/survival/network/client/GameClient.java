package com.chronno.survival.network.client;

import java.io.IOException;
import java.util.Optional;

import com.chronno.survival.network.exception.ClientConnectionException;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.minlog.Log;

public class GameClient {
	private static final Integer DEFAULT_TCP_PORT = 54555;
	private static final Integer DEFAULT_UDP_PORT = 54777;
	private final Client client;
	private final Kryo kryo;

	
	public GameClient() {
		this.client = new Client();
		this.client.addListener(new ClientListener());
		this.kryo = client.getKryo();	
	}
	
	public void connect(String serverIp, Optional<Integer> tcpPort, Optional<Integer> udpPort) {
		try {
			this.client.start();
			this.client.connect(1000, serverIp, tcpPort.orElse(DEFAULT_TCP_PORT), udpPort.orElse(DEFAULT_UDP_PORT));
			this.client.run();
		} catch (IOException e) {
			Log.info(String.format("could not connect to: %s", serverIp));
			throw new ClientConnectionException(String.format("failed to connect to: %s", serverIp), e);
			
		} finally {
			this.client.stop();
		}
	}
	
	public Client getClient() {
		return this.client;
	}

	public Kryo getKryo() {
		return kryo;
	}
}
