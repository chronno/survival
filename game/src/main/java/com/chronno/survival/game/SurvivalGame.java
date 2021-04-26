package com.chronno.survival.game;

import com.badlogic.gdx.Game;
import com.chronno.survival.game.model.Controllers;
import com.chronno.survival.game.services.ProjectLoader;

public class SurvivalGame extends Game {

    private ProjectLoader projectLoader;

    @Override
    public void create() {
        projectLoader = new ProjectLoader(1920, 1280, "project.dt");
        projectLoader.init("MainScene");

        Controllers.spineObjectController(projectLoader.getRootItem().getChild("animation"));
        //this.projectLoader.getRootItem().getChild("animation").addComponent(new PlayerComponent());
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
