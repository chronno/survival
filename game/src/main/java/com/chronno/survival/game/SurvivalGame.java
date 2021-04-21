package com.chronno.survival.game;

import java.util.Optional;

import com.badlogic.gdx.Game;
import com.chronno.survival.network.client.GameClient;

public class SurvivalGame extends Game {
	
	private final GameClient client;
	
	public SurvivalGame() {
		super();
		client = new GameClient();
		client.connect("181.164.136.246", Optional.of(25565), Optional.of(25575));
	}

	@Override
	public void create() {
		
		
	}

}
