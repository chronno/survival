package com.chronno.survival.game;

import com.badlogic.gdx.Game;
import com.chronno.survival.game.model.character.CharacterController;
import com.chronno.survival.game.services.network.NetworkController;
import com.chronno.survival.game.services.ProjectLoader;

public class SurvivalGame extends Game {

    private ProjectLoader projectLoader;

    private CharacterController characterController;
    private NetworkController networkController;

    @Override
    public void create() {
        projectLoader = new ProjectLoader(1920, 1280, "project.dt");
        projectLoader.init("MainScene");
        characterController = new CharacterController(projectLoader.getRootItem().getChild("animation"));
        networkController = new NetworkController();
    }

    @Override
    public void render() {
        projectLoader.update();
    }

    @Override
    public void dispose() {
        projectLoader.dispose();
    }
}
