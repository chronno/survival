package com.chronno.survival.game.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
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
import com.chronno.survival.game.components.type.EntityTypeComponent;
import com.chronno.survival.game.components.type.PlayerEntity;
import com.chronno.survival.game.systems.player.Input;
import com.chronno.survival.game.systems.player.PlayerSystem;
import com.chronno.survival.game.systems.rendering.RenderingSystem;

public class MapScreen extends ScreenAdapter {

    private final PooledEngine engine;

    public MapScreen(PooledEngine engine) {
        this.engine = engine;
    }

    @Override
    public void show() {
        OrthographicCamera orthographicCamera = new OrthographicCamera(960, 640);
        Gdx.input.setInputProcessor(new Input(orthographicCamera));
        engine.addSystem(new RenderingSystem(0, orthographicCamera));
        engine.addSystem(new PlayerSystem(5));
        createMapEntity();
        createPlayerEntity();
    }

    private void createMapEntity() {
        Entity mapEntity = engine.createEntity();
        TilemapComponent tilemapComponent = engine.createComponent(TilemapComponent.class);
        tilemapComponent.set("example.tmx");
        mapEntity.add(tilemapComponent);
        RendererComponent rendererComponent = engine.createComponent(RendererComponent.class);
        rendererComponent.set(new TileMapRenderer());
        mapEntity.add(rendererComponent);
        engine.addEntity(mapEntity);
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
        EntityTypeComponent entityTypeComponent = engine.createComponent(EntityTypeComponent.class);
        entityTypeComponent.set(new PlayerEntity());
        characterEntity.add(entityTypeComponent);
        engine.addEntity(characterEntity);
    }

    @Override
    public void render(float delta) {
        engine.update(delta);
    }

    @Override
    public void dispose() {
        engine.removeAllEntities();
        engine.removeAllSystems();
        engine.clearPools();
    }
}
