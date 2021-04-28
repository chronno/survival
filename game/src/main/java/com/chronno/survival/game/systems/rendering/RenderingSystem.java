package com.chronno.survival.game.systems.rendering;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.chronno.survival.game.components.AnimationComponent;
import com.chronno.survival.game.components.PositionComponent;
import com.chronno.survival.game.components.SkeletonComponent;
import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.spine.SkeletonRenderer;

public class RenderingSystem extends EntitySystem {


    private static final ComponentMapper<PositionComponent> PositionMapper = ComponentMapper.getFor(PositionComponent.class);
    private static final ComponentMapper<AnimationComponent> AnimationMapper = ComponentMapper.getFor(AnimationComponent.class);
    private static final ComponentMapper<SkeletonComponent> SkeletonMapper = ComponentMapper.getFor(SkeletonComponent.class);


    private final OrthographicCamera camera;
    private final SpriteBatch spriteBatch;
    private final SkeletonRenderer skeletonRenderer;
    private ImmutableArray<Entity> entities;

    public RenderingSystem(int priority, OrthographicCamera camera) {
        super(priority);
        this.camera = camera;
        this.spriteBatch = new SpriteBatch();
        this.skeletonRenderer = new SkeletonRenderer();
        this.skeletonRenderer.setPremultipliedAlpha(true);
    }

    @Override
    public void addedToEngine(Engine engine) {
        Log.info("Rendering System was added");
        Family family = Family.all(PositionComponent.class, AnimationComponent.class, SkeletonComponent.class).get();
        entities = engine.getEntitiesFor(family);

    }

    @Override
    public void removedFromEngine(Engine engine) {
        Log.info("Rendering System was removed");
    }

    @Override
    public void update(float deltaTime) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        entities.forEach(entity -> draw(entity, deltaTime));
        spriteBatch.end();
    }

    private void draw(Entity entity, float deltaTime) {
        PositionComponent positionComponent = PositionMapper.get(entity);
        SkeletonComponent skeletonComponent = SkeletonMapper.get(entity);
        AnimationComponent animationComponent = AnimationMapper.get(entity);
        animationComponent.getAnimationState().update(deltaTime);
        animationComponent.getAnimationState().apply(skeletonComponent.getSkeleton());
        skeletonComponent.getSkeleton().updateWorldTransform();
        skeletonRenderer.draw(spriteBatch, skeletonComponent.getSkeleton());
    }
}
