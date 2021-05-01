package com.chronno.survival.game.components.rendering;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.chronno.survival.game.components.drawable.AnimationComponent;
import com.chronno.survival.game.components.drawable.SkeletonComponent;
import com.esotericsoftware.spine.SkeletonRenderer;

public class SpineAnimationRenderer implements Renderer {
    private static final ComponentMapper<AnimationComponent> AnimationMapper = ComponentMapper.getFor(AnimationComponent.class);
    private static final ComponentMapper<SkeletonComponent> SkeletonMapper = ComponentMapper.getFor(SkeletonComponent.class);
    private final SkeletonRenderer skeletonRenderer;

    public SpineAnimationRenderer() {
        this.skeletonRenderer = new SkeletonRenderer();
        this.skeletonRenderer.setPremultipliedAlpha(true);
    }

    @Override
    public void render(Entity entity, SpriteBatch spriteBatch, Camera camera, Float delta) {
        SkeletonComponent skeletonComponent = SkeletonMapper.get(entity);
        AnimationComponent animationComponent = AnimationMapper.get(entity);
        skeletonComponent.getSkeleton().updateWorldTransform();
        animationComponent.getAnimationState().update(delta);
        animationComponent.getAnimationState().apply(skeletonComponent.getSkeleton());
        spriteBatch.begin();
        skeletonRenderer.draw(spriteBatch, skeletonComponent.getSkeleton());
        spriteBatch.end();
    }
}
