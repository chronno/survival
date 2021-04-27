package com.chronno.survival.game.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.chronno.survival.game.components.AnimationComponent;
import com.chronno.survival.game.components.PositionComponent;
import com.chronno.survival.game.systems.rendering.RenderingSystem;
import com.esotericsoftware.minlog.Log;

public class MapScreen implements Screen {

    private final PooledEngine engine;

    public MapScreen(PooledEngine engine) {
        this.engine = engine;
    }

    @Override
    public void show() {
        engine.addSystem(new RenderingSystem(new OrthographicCamera(960, 640)));

        Entity characterEntity = engine.createEntity();
        Log.info("a new entity has been added to the engine");
        AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
        animationComponent.set("animations/Male No Items.atlas", "animations/Male Character Down.json");
        characterEntity.add(animationComponent);
        characterEntity.add(engine.createComponent(PositionComponent.class));
        engine.addEntity(characterEntity);

    }

    @Override
    public void render(float delta) {
        engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {}
}
