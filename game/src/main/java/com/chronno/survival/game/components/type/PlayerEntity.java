package com.chronno.survival.game.components.type;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.chronno.survival.game.components.ActionComponent;
import com.chronno.survival.game.components.AnimationComponent;
import com.chronno.survival.game.components.DirectionComponent;
import com.chronno.survival.game.components.PositionComponent;
import com.chronno.survival.game.components.SkeletonComponent;
import com.chronno.survival.game.model.Action;
import com.chronno.survival.game.model.Direction;

public class PlayerEntity implements EntityType {

    private static final String CharacterSkeletonPathTemplate = "Male Character %s.json";
    private static final String AnimationNameTemplate = "%s %s";

    private static final ComponentMapper<PositionComponent> PositionMapper = ComponentMapper.getFor(PositionComponent.class);
    private static final ComponentMapper<DirectionComponent> DirectionMapper = ComponentMapper.getFor(DirectionComponent.class);
    private static final ComponentMapper<AnimationComponent> AnimationMapper = ComponentMapper.getFor(AnimationComponent.class);
    private static final ComponentMapper<SkeletonComponent> SkeletonMapper = ComponentMapper.getFor(SkeletonComponent.class);
    private static final ComponentMapper<ActionComponent> ActionMapper = ComponentMapper.getFor(ActionComponent.class);

    @Override
    public void update(Entity entity) {
        if (Gdx.input.isKeyJustPressed(Keys.A)) {
            process(entity, Action.Spell, Direction.Empty);
        } else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            process(entity, Action.Walk, Direction.Down);
        } else if (Gdx.input.isKeyPressed(Keys.UP)) {
            process(entity, Action.Walk, Direction.Up);
        } else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            process(entity, Action.Walk, Direction.Left);
        } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            process(entity, Action.Walk, Direction.Right);
        } else {
            process(entity, Action.Idle, Direction.Empty);
        }
    }

    private void process(Entity entity, Action action, Direction direction) {
        DirectionComponent directionComponent = DirectionMapper.get(entity);
        SkeletonComponent skeletonComponent = SkeletonMapper.get(entity);
        AnimationComponent animationComponent = AnimationMapper.get(entity);
        ActionComponent actionComponent = ActionMapper.get(entity);
        Direction realDirection = getRealDirection(direction, directionComponent);
        String requiredAnimationName = String.format(AnimationNameTemplate, realDirection.name(), action.name());
        String requiredSkeleton = String.format(CharacterSkeletonPathTemplate, realDirection.name());
        if (animationComponent.canChangeAnimation(requiredAnimationName)) {
            skeletonComponent.setTo(requiredSkeleton);
            skeletonComponent.flipX(realDirection.equals(Direction.Left));
            directionComponent.set(realDirection);
            actionComponent.set(action);
            animationComponent.update(skeletonComponent.getSkeleton(), requiredAnimationName, action.isLoopable());
        }

    }

    private Direction getRealDirection(Direction direction, DirectionComponent directionComponent) {
        return direction.equals(Direction.Empty) ? directionComponent.getDirection() : direction;
    }
}
