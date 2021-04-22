package com.chronno.survival.game;

import java.io.IOException;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.chronno.survival.network.client.GameClient;

public class SurvivalGame extends Game {
	
	private final GameClient client;
	
	public SurvivalGame() {
		super();
		this.client = new GameClient(new NetworkInterpreter());
		this.client.connect("181.164.136.246", 25565, 25575);
	}

	@Override
	public void dispose() {
		this.client.disconnect();
	}

	@Override
	public void create() {

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

}
