package com.chronno.survival.game.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.Skin;


public final class SkeletonComponent extends BaseComponent {

    private static final String AnimationsPath = "animations/";

    private String atlasPath;
    private String jsonPath;
    private Skeleton skeleton;

    public void set(String atlasPath, String jsonPath) {
        this.atlasPath = atlasPath;
        this.jsonPath = jsonPath;
        final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(AnimationsPath.concat(atlasPath)));
        final SkeletonJson skeletonJson = new SkeletonJson(atlas);
        final SkeletonData skeletonData = skeletonJson.readSkeletonData(Gdx.files.internal(AnimationsPath.concat(jsonPath)));
        setBasicSkin(skeletonData);
        skeleton = new Skeleton(skeletonData);
        skeleton.setX(512);
        skeleton.setY(512);
    }

    //TODO remove this this should be handled by some kind of clothing/equipment system and probably this should apply that
    private void setBasicSkin(SkeletonData skeletonData) {
        Skin headSkin = skeletonData.findSkin("Head");
        Skin swordSkin = skeletonData.findSkin("Swing");
        Skin helmSkin = skeletonData.findSkin("Headwear");
        Skin skin = skeletonData.findSkin("Leather Equipment");
        skin.addSkin(headSkin);
        skin.addSkin(swordSkin);
        skin.addSkin(helmSkin);
        skeletonData.setDefaultSkin(skin);
    }

    public Skeleton getSkeleton() {
        return skeleton;
    }

    @Override
    public void reset() {

    }

    public boolean hasName(String str) {
        return jsonPath.equals(str);
    }

    public void setTo(String jsonPath) {
        this.set(atlasPath, jsonPath);
    }

    public void flipX(Boolean flip) {
        this.skeleton.setScaleX(flip ? -1 : 1);
    }

    public void flipY(Boolean flip) {
        this.skeleton.setScaleY(flip ? -1 : 1);
    }
}
