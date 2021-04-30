package com.chronno.survival.network.client;

import com.chronno.survival.network.exception.ClientConnectionException;
import com.chronno.survival.network.messaging.Message;
import com.chronno.survival.network.messaging.MessageInterpreter;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.minlog.Log;

import java.io.IOException;
import java.util.Optional;

public class GameClient {
	private static final Integer DEFAULT_TCP_PORT = 54555;
	private static final Integer DEFAULT_UDP_PORT = 54777;
	private final Client client;
	private final Kryo kryo;
	
	public GameClient(MessageInterpreter interpreter) {
		this.client = new Client();
		this.client.addListener(new ClientListener(interpreter));
		this.kryo = client.getKryo();
		this.kryo.register(Message.class);
	}
	
	public void connect(String serverIp, Integer tcpPort,  Integer udpPort) {
		try {
			this.client.start();
			this.client.connect(10, serverIp, Optional.ofNullable(tcpPort).orElse(DEFAULT_TCP_PORT), Optional.of(udpPort).orElse(DEFAULT_UDP_PORT));

		} catch (IOException e) {
			Log.info(String.format("could not connect to: %s", serverIp));
			throw new ClientConnectionException(String.format("failed to connect to: %s", serverIp), e);
		}
	}

	public void sendMessage(Message message) {
		if (client.isConnected()) {
			this.client.sendTCP(message);
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
