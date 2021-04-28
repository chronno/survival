package com.chronno.survival.game.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.chronno.survival.game.components.ActionComponent;
import com.chronno.survival.game.components.AnimationComponent;
import com.chronno.survival.game.components.DirectionComponent;
import com.chronno.survival.game.components.PositionComponent;
import com.chronno.survival.game.components.SkeletonComponent;
import com.chronno.survival.game.components.TilemapComponent;
import com.chronno.survival.game.components.rendering.RendererComponent;
import com.chronno.survival.game.components.rendering.SpineAnimationRenderer;
import com.chronno.survival.game.components.rendering.TileMapRenderer;
import com.chronno.survival.game.systems.CharacterActionSystem;
import com.chronno.survival.game.systems.player.PlayerInputSystem;
import com.chronno.survival.game.systems.rendering.RenderingSystem;

public class MapScreen implements Screen {

    private final PooledEngine engine;

    public MapScreen(PooledEngine engine) {
        this.engine = engine;
    }

    @Override
    public void show() {
        engine.addSystem(new RenderingSystem(3, new OrthographicCamera(960, 640)));
        engine.addSystem(new CharacterActionSystem(4));
        engine.addSystem(new PlayerInputSystem(5));

        Entity mapEntity = engine.createEntity();
        TilemapComponent tilemapComponent = engine.createComponent(TilemapComponent.class);
        tilemapComponent.set("testmap.tmx");
        mapEntity.add(tilemapComponent);
        RendererComponent rendererComponent = engine.createComponent(RendererComponent.class);
        rendererComponent.set(new TileMapRenderer());
        mapEntity.add(rendererComponent);
        engine.addEntity(mapEntity);

        createPlayerEntity();

    }

    private void createPlayerEntity() {
        Entity characterEntity = engine.createEntity();
        SkeletonComponent skeletonComponent = engine.createComponent(SkeletonComponent.class);
        skeletonComponent.set("Male.atlas", "Male Character Down.json");
        AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
        animationComponent.set(skeletonComponent.getSkeleton(), "Down Idle");
        characterEntity.add(animationComponent);
        characterEntity.add(skeletonComponent);
        characterEntity.add(engine.createComponent(PositionComponent.class));
        characterEntity.add(engine.createComponent(ActionComponent.class));
        characterEntity.add(engine.createComponent(DirectionComponent.class));
        RendererComponent rendererComponent = engine.createComponent(RendererComponent.class);
        rendererComponent.set(new SpineAnimationRenderer());
        characterEntity.add(rendererComponent);
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
