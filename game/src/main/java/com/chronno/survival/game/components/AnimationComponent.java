package com.chronno.survival.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import com.esotericsoftware.spine.*;
import com.badlogic.gdx.utils.Pool.Poolable;

public class AnimationComponent implements Component, Poolable {

    private TextureAtlas atlas;
    private SkeletonJson skeletonJson;
    private SkeletonData skeletonData;
    private Skeleton skeleton;
    private AnimationState animationState;
    private AnimationStateData animationStateData;


    public Skeleton getSkeleton() {
        return skeleton;
    }

    public AnimationState getAnimationState() {
        return animationState;
    }

    public void set(String atlasPath, String jsonPath) {
        atlas = new TextureAtlas(Gdx.files.internal(atlasPath));
        skeletonJson = new SkeletonJson(atlas);
        skeletonJson.setScale(0.5f);
        skeletonData = skeletonJson.readSkeletonData(Gdx.files.internal(jsonPath));

        skeleton = new Skeleton(skeletonData);
        animationStateData = new AnimationStateData(skeletonData);
        animationState = new AnimationState(animationStateData);
        animationState.setAnimation(0, "Down Walk", true);
    }

    @Override
    public void reset() {

    }
}
