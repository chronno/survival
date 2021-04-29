package com.chronno.survival.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.chronno.survival.game.components.ActionComponent;
import com.chronno.survival.game.components.ActionComponent.Action;
import com.chronno.survival.game.components.AnimationComponent;
import com.chronno.survival.game.components.DirectionComponent;
import com.chronno.survival.game.components.DirectionComponent.Direction;
import com.chronno.survival.game.components.PositionComponent;
import com.chronno.survival.game.components.SkeletonComponent;
import com.esotericsoftware.minlog.Log;

public class CharacterActionSystem extends EntitySystem {

    private static final String AnimNameStructure = "%s %s";

    private static final ComponentMapper<DirectionComponent> DirectionMapper = ComponentMapper.getFor(DirectionComponent.class);
    private static final ComponentMapper<ActionComponent> ActionMapper = ComponentMapper.getFor(ActionComponent.class);
    private static final ComponentMapper<SkeletonComponent> SkeletonMapper = ComponentMapper.getFor(SkeletonComponent.class);
    private static final ComponentMapper<AnimationComponent> AnimationMapper = ComponentMapper.getFor(AnimationComponent.class);

    private ImmutableArray<Entity> entities;

    public CharacterActionSystem(int priority) {
        super(priority);
    }

    @Override
    public void addedToEngine(Engine engine) {
        Log.info("Movement System was added");
        Family family = Family.all(PositionComponent.class,
                DirectionComponent.class,
                ActionComponent.class, SkeletonComponent.class,
                AnimationComponent.class).get();
        entities = engine.getEntitiesFor(family);
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        for (int i = 0; i < this.entities.size(); ++i) {
            this.processEntity(this.entities.get(i), deltaTime);
        }
    }

    private void processEntity(Entity entity, float delta) {
        ActionComponent actionComponent = ActionMapper.get(entity);
        DirectionComponent directionComponent = DirectionMapper.get(entity);
        SkeletonComponent skeletonComponent = SkeletonMapper.get(entity);
        AnimationComponent animationComponent = AnimationMapper.get(entity);

        Direction currentDirection = directionComponent.getDirection();
        Action currentAction = actionComponent.getCurrentAction();
        String animationName = getAnimationName(currentDirection, currentAction);
        if (animationComponent.canChangeAnimation(animationName)) {
            skeletonComponent.update(currentDirection);
            animationComponent.update(skeletonComponent.getSkeleton(), animationName, currentAction.isLoopable());
        }
    }

    private String getAnimationName(Direction direction, Action action) {
        if (direction.equals(Direction.Left) || direction.equals(Direction.Right)) {
            return String.format(AnimNameStructure, "Side", action.name());
        } else {
            return String.format(AnimNameStructure, direction.name(), action.name());
        }
    }

}
