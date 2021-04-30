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
            process(entity, Action.Swing, Direction.Empty);
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
        String requiredAnimationName = getAnimationName(action, direction, directionComponent);
        if (!directionComponent.isSame(direction)) {
            String requiredSkeleton = String.format(CharacterSkeletonPathTemplate, direction.name());
            skeletonComponent.setTo(requiredSkeleton);
            skeletonComponent.flipX(direction.equals(Direction.Left));
            directionComponent.set(direction);
        }
        if (animationComponent.canChangeAnimation(requiredAnimationName)){
            animationComponent.update(skeletonComponent.getSkeleton(), requiredAnimationName, action.isLoopable());
            actionComponent.set(action);
        }
    }

    private String getAnimationName(Action action, Direction direction, DirectionComponent directionComponent) {
        String directionName =  direction.equals(Direction.Empty) ? directionComponent.getDirection().name() : direction.name();
        return String.format(AnimationNameTemplate, directionName, action.name());
    }


}
