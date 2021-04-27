package com.chronno.survival.game.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.chronno.survival.game.components.CharacterAnimationComponent;
import com.chronno.survival.game.components.DirectionComponent;
import com.chronno.survival.game.components.DirectionComponent.Direction;
import com.chronno.survival.game.components.PositionComponent;
import com.esotericsoftware.minlog.Log;

public class CharacterMovementSystem extends EntitySystem {

    private static final ComponentMapper<PositionComponent> PositionMapper = ComponentMapper.getFor(PositionComponent.class);
    private static final ComponentMapper<CharacterAnimationComponent> CharacterAnimationMapper = ComponentMapper.getFor(CharacterAnimationComponent.class);
    private static final ComponentMapper<DirectionComponent> DirectionMapper = ComponentMapper.getFor(DirectionComponent.class);

    private ImmutableArray<Entity> entities;

    public CharacterMovementSystem(int priority) {
        super(priority);
    }

    @Override
    public void addedToEngine(Engine engine) {
        Log.info("Movement System was added");
        Family family = Family.all(PositionComponent.class, CharacterAnimationComponent.class, DirectionComponent.class).get();
        entities = engine.getEntitiesFor(family);
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        for(int i = 0; i < this.entities.size(); ++i) {
            this.processEntity(this.entities.get(i), deltaTime);
        }
    }

    private void processEntity(Entity entity, float delta) {
        updateDirection(entity);
        PositionComponent positionComponent = PositionMapper.get(entity);

    }

    private void updateDirection(Entity entity) {
        DirectionComponent directionComponent = DirectionMapper.get(entity);
        CharacterAnimationComponent characterAnimationComponent = CharacterAnimationMapper.get(entity);
        Direction currentDirection = directionComponent.getDirection();
        characterAnimationComponent.setDirection(currentDirection);
    }
}
