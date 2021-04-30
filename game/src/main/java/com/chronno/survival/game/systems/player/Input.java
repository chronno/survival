package com.chronno.survival.game.systems.player;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Input extends InputAdapter {

    private OrthographicCamera camera;
    public Input(OrthographicCamera orthographicCamera) {
        camera = orthographicCamera;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        camera.zoom += amountY * 0.2f;
        return true;
    }
}
