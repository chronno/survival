package com.chronno.survival.game.services.player;

import com.badlogic.gdx.files.FileHandle;
import com.chronno.survival.game.model.WorldObject;
import com.chronno.survival.game.model.character.Animation;
import com.chronno.survival.game.model.character.Direction;
import com.chronno.survival.game.services.ProjectLoader;
import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import games.rednblack.editor.renderer.components.TransformComponent;
import games.rednblack.h2d.extention.spine.SpineObjectComponent;

import static com.chronno.survival.game.services.player.AnimationStatus.Completed;
import static com.chronno.survival.game.services.player.AnimationStatus.Interrupted;

public class PlayerAnimationService {
    protected final ProjectLoader projectLoader;
    protected final WorldObject object;
    protected final SpineObjectComponent spineObjectComponent;
    protected final TransformComponent transformComponent;
    protected final SpineAnimationsController animationsController;

    private final PlayerStatus playerStatus;
    private boolean isPositionTranslated = false;

    public PlayerAnimationService(ProjectLoader projectLoader, WorldObject object) {
        this.projectLoader = projectLoader;
        this.object = object;
        this.spineObjectComponent = object.getComponent(SpineObjectComponent.class);
        this.transformComponent = object.getComponent(TransformComponent.class);
        this.animationsController = new SpineAnimationsController(spineObjectComponent);
        this.playerStatus = new PlayerStatus(Direction.Down, Animation.Walk, Completed);
        this.spineObjectComponent.getState().addListener(new SpineAnimationListener(this));
    }

    public void addToAnimationsQueue(Animation animation, Direction direction) {
       QueuedAnimation requested = new QueuedAnimation(animation, direction);
       updateAnimation(requested);
    }

    private void updateAnimation(QueuedAnimation nextAnimation) {
        if (shouldChangeAnimation(nextAnimation)) {
            prepare(nextAnimation);
        }
    }

    private void prepare(QueuedAnimation nextAnimation) {
        if (shouldChangeSkeleton(nextAnimation)) {
            changeSkeleton(nextAnimation.getDirection());
        }
        flipImage(nextAnimation.getDirection());
        playerStatus.setAnimation(nextAnimation.getAnimation());
        changeAnimation(nextAnimation.getAnimation());
    }

    private void flipImage(Direction direction) {
        if (!"Side".equals(direction.skeletonName())) { return;}
        if (Direction.Left.equals(direction) && !isPositionTranslated) {
            transformComponent.x += direction.spatialFix();
            transformComponent.scaleX = - 1;
            isPositionTranslated = true;
        } else if (Direction.Right.equals(direction) && isPositionTranslated) {
            transformComponent.scaleX = 1;
            transformComponent.x += direction.spatialFix();
            isPositionTranslated = false;
        }
    }

    private boolean shouldChangeAnimation(QueuedAnimation nextAnimation) {
        boolean canBeInterrupted = playerStatus.getAnimation().interruptible();
        boolean isAnimationFinished = Completed.equals(playerStatus.getAnimationStatus());
        boolean isSameAnimation = nextAnimation.getAnimation().equals(playerStatus.getAnimation());
        return (canBeInterrupted || isAnimationFinished) && (!isSameAnimation || shouldChangeSkeleton(nextAnimation));
    }


    private void changeAnimation(Animation animation) {
        animationsController.setAnimation(animation, playerStatus.getDirection());
    }

    private boolean shouldChangeSkeleton(QueuedAnimation nextAnimation) {
        boolean animationRequireSpecificDirection = !Direction.Empty.equals(nextAnimation.getDirection());
        boolean usesSameSkeleton = playerStatus.getDirection().skeletonName().equals(nextAnimation.getDirection().skeletonName());
        return animationRequireSpecificDirection && !usesSameSkeleton;
    }

    private void changeSkeleton(Direction direction) {
        FileHandle skeletonFile = findSkeletonFile(direction);
        spineObjectComponent.skeletonData = spineObjectComponent.skeletonJson.readSkeletonData(skeletonFile);
        spineObjectComponent.skeleton = new Skeleton(spineObjectComponent.skeletonData);
        spineObjectComponent.getState().setData(new AnimationStateData(spineObjectComponent.skeletonData));
        playerStatus.setDirection(direction);
    }

    private FileHandle findSkeletonFile(Direction direction) {
        return projectLoader.getResourceManager().getSkeletonJSON(String.format("Male Character %s", direction.skeletonName()));
    }

    public void onAnimationStatusChange(Animation animation, AnimationStatus animationStatus) {
        if (!animation.equals(playerStatus.getAnimation()) && animationStatus.equals(Interrupted)) {
            Log.info(playerStatus.getAnimation().name() + " interrupted " + animation.name());
        }
        playerStatus.setAnimationStatus(animationStatus);

    }
}
