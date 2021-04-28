package com.chronno.survival.game.systems.player;


import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.chronno.survival.game.components.ActionComponent;
import com.chronno.survival.game.components.ActionComponent.Action;
import com.chronno.survival.game.components.DirectionComponent;
import com.chronno.survival.game.components.DirectionComponent.Direction;
import com.chronno.survival.game.components.PositionComponent;
import com.esotericsoftware.minlog.Log;

import static com.chronno.survival.game.components.ActionComponent.Action.Idle;
import static com.chronno.survival.game.components.ActionComponent.Action.Swing;
import static com.chronno.survival.game.components.ActionComponent.Action.Walk;
import static com.chronno.survival.game.components.DirectionComponent.Direction.Down;
import static com.chronno.survival.game.components.DirectionComponent.Direction.Empty;
import static com.chronno.survival.game.components.DirectionComponent.Direction.Left;
import static com.chronno.survival.game.components.DirectionComponent.Direction.Right;
import static com.chronno.survival.game.components.DirectionComponent.Direction.Up;


public class PlayerInputSystem extends EntitySystem {

    private static final ComponentMapper<DirectionComponent> DirectionMapper = ComponentMapper.getFor(DirectionComponent.class);
    private static final ComponentMapper<ActionComponent> ActionMapper = ComponentMapper.getFor(ActionComponent.class);
    private ImmutableArray<Entity> entities;

    public PlayerInputSystem(int priority) {
        super(priority);
    }

    @Override
    public void addedToEngine(Engine engine) {
        Log.info("Player Input System was added");
        Family family = Family.all(PositionComponent.class, DirectionComponent.class, ActionComponent.class).get();
        entities = engine.getEntitiesFor(family);
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            entities.forEach(entity -> startAction(entity, Swing, Empty));
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            entities.forEach(entity -> startAction(entity, Walk, Down));
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            entities.forEach(entity -> startAction(entity, Walk, Up));
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            entities.forEach(entity -> startAction(entity, Walk, Left));
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            entities.forEach(entity -> startAction(entity, Walk, Right));
        } else {
            entities.forEach(entity -> startAction(entity, Idle, Empty));
        }
    }

    private void startAction(Entity entity, Action action, Direction direction) {
        DirectionComponent directionComponent = DirectionMapper.get(entity);
        ActionComponent actionComponent = ActionMapper.get(entity);
        if (!Empty.equals(direction)) {
            directionComponent.set(direction);
        }
        actionComponent.set(action);

    }
}
