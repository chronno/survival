package com.chronno.survival.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.chronno.survival.game.screens.MapScreen;

public class SurvivalGame extends Game {

    private final PooledEngine engine;

    public SurvivalGame() {
        this.engine = new PooledEngine();
    }


    @Override
    public void create() {
        setScreen(new MapScreen(engine));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }


}
