package com.chronno.survival.game.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.Skin;

import static com.chronno.survival.game.components.DirectionComponent.Direction.Left;
import static com.chronno.survival.game.components.DirectionComponent.Direction.Right;

public final class SkeletonComponent extends BaseComponent {

    private static final String JsonNameStructure = "%s Character %s.json";

    private static String AnimationsPath = "animations/";
    private String atlasPath;
    private String jsonPath;
    private Skeleton skeleton;

    public void set(String atlasPath, String jsonPath) {
        this.atlasPath = atlasPath;
        this.jsonPath = jsonPath;
        final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(AnimationsPath.concat(atlasPath)));
        final SkeletonJson skeletonJson = new SkeletonJson(atlas);
        skeletonJson.setScale(0.5f);
        final SkeletonData skeletonData = skeletonJson.readSkeletonData(Gdx.files.internal(AnimationsPath.concat(jsonPath)));
        Skin headSkin = skeletonData.findSkin("Head");
        Skin swordSkin = skeletonData.findSkin("Swing");
        Skin helmSkin = skeletonData.findSkin("Headwear");
        Skin skin = skeletonData.findSkin("Leather Equipment");
        skin.addSkin(headSkin);
        skin.addSkin(swordSkin);
        skin.addSkin(helmSkin);
        skeletonData.setDefaultSkin(skin);
        skeleton = new Skeleton(skeletonData);
    }

    public Skeleton getSkeleton() {
        return skeleton;
    }

    @Override
    public void reset() {

    }

    public void update(DirectionComponent.Direction currentDirection) {
        if (shouldChangeSkeleton(currentDirection)) {
            set(atlasPath, getUpdatedJsonPath(currentDirection));
        }
        skeleton.setScaleX(Left.equals(currentDirection) ? - 1 : 1);
    }

    private Boolean shouldChangeSkeleton(DirectionComponent.Direction requestedDirection) {
        return !jsonPath.equals(getUpdatedJsonPath(requestedDirection));

    }

    private String getUpdatedJsonPath(DirectionComponent.Direction currentDirection) {
        if (currentDirection.equals(Left) || currentDirection.equals(Right)) {
            return String.format(JsonNameStructure, "Male", "Side");
        } else {
            return String.format(JsonNameStructure, "Male", currentDirection.name());
        }
    }
}
