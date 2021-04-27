package com.chronno.survival.game.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.*;

public class AnimationComponent extends BaseComponent {

    protected static String AnimationsPath = "animations/";

    protected String atlasPath;
    protected String jsonPath;

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

    public void set(String atlasPath, String jsonPath, String animation) {
        if(canChangeAnimation(animation)) {
            this.atlasPath = atlasPath;
            this.jsonPath = jsonPath;
            loadSkeletonData(atlasPath, jsonPath);
            Skin headSkin = skeletonData.findSkin("Head");
            Skin swordSkin = skeletonData.findSkin("Swing");
            Skin helmSkin = skeletonData.findSkin("Headwear");
            Skin skin = skeletonData.findSkin("Leather Equipment");
            skin.addSkin(headSkin);
            skin.addSkin(swordSkin);
            //skin.addSkin(helmSkin);
            skeletonData.setDefaultSkin(skin);
            skeleton = new Skeleton(skeletonData);
            animationStateData = new AnimationStateData(skeletonData);
            animationState = new AnimationState(animationStateData);
        }
    }

    public void setAnimation(String animation, Boolean loop) {
        if (canChangeAnimation(animation)) {
            animationState.setAnimation(0, animation, loop);
        }
    }

    protected Boolean canChangeAnimation(String animation) {
        if (animationState == null) {return true;}
        AnimationState.TrackEntry currentAnimation = animationState.getCurrent(0);
        if (currentAnimation == null) { return true; }
        boolean isSame = currentAnimation.getAnimation().getName().equals(animation);
        boolean canBeInterrupted = currentAnimation.getLoop() || currentAnimation.isComplete();
        return !isSame && canBeInterrupted;
    }

    private void loadSkeletonData(String atlasPath, String jsonPath) {
        atlas = new TextureAtlas(Gdx.files.internal(AnimationsPath.concat(atlasPath)));
        skeletonJson = new SkeletonJson(atlas);
        skeletonJson.setScale(0.5f);
        skeletonData = skeletonJson.readSkeletonData(Gdx.files.internal(AnimationsPath.concat(jsonPath)));
    }

    @Override
    public void reset() {

    }


}
