package com.chronno.survival.game.components.rendering;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.chronno.survival.game.components.AnimationComponent;
import com.chronno.survival.game.components.PositionComponent;
import com.chronno.survival.game.components.SkeletonComponent;
import com.esotericsoftware.spine.SkeletonRenderer;

public class SpineAnimationRenderer implements Renderer {

    private static final ComponentMapper<PositionComponent> PositionMapper = ComponentMapper.getFor(PositionComponent.class);
    private static final ComponentMapper<AnimationComponent> AnimationMapper = ComponentMapper.getFor(AnimationComponent.class);
    private static final ComponentMapper<SkeletonComponent> SkeletonMapper = ComponentMapper.getFor(SkeletonComponent.class);
    private final SkeletonRenderer skeletonRenderer;

    public SpineAnimationRenderer() {
        this.skeletonRenderer = new SkeletonRenderer();
        this.skeletonRenderer.setPremultipliedAlpha(true);
    }

    @Override
    public void render(Entity entity, SpriteBatch spriteBatch, Camera camera, Float delta) {
        PositionComponent positionComponent = PositionMapper.get(entity);
        SkeletonComponent skeletonComponent = SkeletonMapper.get(entity);
        AnimationComponent animationComponent = AnimationMapper.get(entity);

        animationComponent.getAnimationState().update(delta);
        animationComponent.getAnimationState().apply(skeletonComponent.getSkeleton());
        skeletonComponent.getSkeleton().updateWorldTransform();
        skeletonComponent.getSkeleton().setX(positionComponent.getX());
        skeletonComponent.getSkeleton().setY(positionComponent.getY());
        spriteBatch.begin();
        skeletonRenderer.draw(spriteBatch, skeletonComponent.getSkeleton());
        spriteBatch.end();
    }
}
