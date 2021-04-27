package com.chronno.survival.game.systems.rendering;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.chronno.survival.game.components.AnimationComponent;
import com.chronno.survival.game.components.PositionComponent;
import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.spine.SkeletonRenderer;

public class RenderingSystem extends EntitySystem {


    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<AnimationComponent> am = ComponentMapper.getFor(AnimationComponent.class);

    private final OrthographicCamera camera;
    private final SpriteBatch spriteBatch;
    private final SkeletonRenderer skeletonRenderer;
    private ImmutableArray<Entity> entities;

    public RenderingSystem(OrthographicCamera camera) {
        this.camera = camera;
        this.spriteBatch = new SpriteBatch();
        this.skeletonRenderer = new SkeletonRenderer();
        this.skeletonRenderer.setPremultipliedAlpha(true);
    }

    @Override
    public void addedToEngine(Engine engine) {
        Log.info("Rendering System was added");
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, AnimationComponent.class).get());

    }

    @Override
    public void removedFromEngine(Engine engine) {
        Log.info("Rendering System was removed");
    }

    @Override
    public void update(float deltaTime) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        entities.forEach(entity -> {
            PositionComponent position = pm.get(entity);
            AnimationComponent animation = am.get(entity);
            animation.getAnimationState().update(deltaTime);
            animation.getAnimationState().apply(animation.getSkeleton());
            animation.getSkeleton().updateWorldTransform();
            skeletonRenderer.draw(spriteBatch, animation.getSkeleton());
        });
        spriteBatch.end();
    }


}
