package com.chronno.survival.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class SurvivalGameLauncher {

	public static void main(String[] args) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setIdleFPS(60);
		config.setTitle("Survival");
		config.setWindowedMode(960, 640);
		new Lwjgl3Application(new SurvivalGame(), config);
	}
}
