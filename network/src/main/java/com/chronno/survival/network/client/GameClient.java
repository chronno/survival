package com.chronno.survival.network.client;

import java.io.IOException;
import java.util.Optional;

import com.chronno.survival.network.exception.ClientConnectionException;
import com.chronno.survival.network.messaging.MessageRegistry;
import com.chronno.survival.network.server.MessageInterpreter;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

public class GameClient {
	private static final Integer DEFAULT_TCP_PORT = 54555;
	private static final Integer DEFAULT_UDP_PORT = 54777;
	private final Client client;
	private final Kryo kryo;
	
	public GameClient(MessageInterpreter interpreter) {
		this.client = new Client();
		this.client.addListener(new ClientListener(interpreter));
		this.kryo = client.getKryo();
		MessageRegistry.register(this.kryo);
	}
	
	public void connect(String serverIp, Integer tcpPort,  Integer udpPort) {
		try {
			this.client.start();
			this.client.connect(1000, serverIp, Optional.ofNullable(tcpPort).orElse(DEFAULT_TCP_PORT), Optional.of(udpPort).orElse(DEFAULT_UDP_PORT));
		} catch (IOException e) {
			Log.info(String.format("could not connect to: %s", serverIp));
			throw new ClientConnectionException(String.format("failed to connect to: %s", serverIp), e);
		}
	}

	public void disconnect() {
		this.client.stop();
	}
	
	public Client getClient() {
		return this.client;
	}

	public Kryo getKryo() {
		return kryo;
	}
}
