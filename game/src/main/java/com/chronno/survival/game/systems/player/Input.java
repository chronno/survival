package com.chronno.survival.game.systems.player;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Input extends InputAdapter {

    private final OrthographicCamera camera;
    public Input(OrthographicCamera orthographicCamera) {
        camera = orthographicCamera;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        camera.zoom += amountY * 0.2f;
        return true;
    }
}
