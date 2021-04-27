package com.chronno.survival.game.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.chronno.survival.game.components.CharacterAnimationComponent;
import com.chronno.survival.game.components.DirectionComponent;
import com.chronno.survival.game.components.PlayerComponent;
import com.chronno.survival.game.components.PositionComponent;
import com.chronno.survival.game.systems.CharacterMovementSystem;
import com.chronno.survival.game.systems.player.PlayerInputSystem;
import com.chronno.survival.game.systems.rendering.RenderingSystem;
import com.esotericsoftware.minlog.Log;

public class MapScreen implements Screen {

    private final PooledEngine engine;

    public MapScreen(PooledEngine engine) {
        this.engine = engine;
    }

    @Override
    public void show() {
        engine.addSystem(new RenderingSystem(3, new OrthographicCamera(960, 640)));
        engine.addSystem(new CharacterMovementSystem(4));
        engine.addSystem(new PlayerInputSystem(5));

        Entity characterEntity = engine.createEntity();
        Log.info("a new entity has been added to the engine");
        CharacterAnimationComponent animationComponent = engine.createComponent(CharacterAnimationComponent.class);
        animationComponent.set("Male.atlas", "Male Character Down.json", "Down Walk");
        characterEntity.add(animationComponent);
        characterEntity.add(engine.createComponent(PositionComponent.class));
        characterEntity.add(engine.createComponent(DirectionComponent.class));
        characterEntity.add(engine.createComponent(PlayerComponent.class));

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
    public void dispose() {
        engine.removeAllEntities();
        engine.removeAllSystems();
        engine.clearPools();
    }
}
