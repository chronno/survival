package com.chronno.survival.game;

import com.badlogic.gdx.Game;
import com.chronno.survival.game.model.spine.SpineObjectController;
import com.chronno.survival.game.services.ProjectLoader;

public class SurvivalGame extends Game {

    private ProjectLoader projectLoader;

    @Override
    public void create() {
        this.projectLoader = new ProjectLoader(1920, 1280, "project.dt");
        this.projectLoader.init("MainScene");
        this.projectLoader.getRootItem().getChild("animation").appendScript(new SpineObjectController());
    }

    @Override
    public void render() {
        this.projectLoader.update();
    }

    @Override
    public void dispose() {
        this.projectLoader.dispose();
    }
}
