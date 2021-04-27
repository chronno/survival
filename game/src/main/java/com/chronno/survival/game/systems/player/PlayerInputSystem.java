package com.chronno.survival.game.systems.player;


import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.chronno.survival.game.components.CharacterAnimationComponent;
import com.chronno.survival.game.components.DirectionComponent;
import com.chronno.survival.game.components.DirectionComponent.*;
import com.chronno.survival.game.components.PlayerComponent;
import com.chronno.survival.game.components.PositionComponent;
import com.esotericsoftware.minlog.Log;

import static com.chronno.survival.game.components.DirectionComponent.Direction.*;
import static com.chronno.survival.game.components.PlayerComponent.Action.*;


public class PlayerInputSystem extends EntitySystem {

    private static final ComponentMapper<DirectionComponent> DirectionMapper = ComponentMapper.getFor(DirectionComponent.class);
    private static final ComponentMapper<CharacterAnimationComponent> CharacterAnimationMapper = ComponentMapper.getFor(CharacterAnimationComponent.class);
    private static final ComponentMapper<PlayerComponent> PlayerMapper = ComponentMapper.getFor(PlayerComponent.class);
    private ImmutableArray<Entity> entities;

    public PlayerInputSystem(int priority) {
        super(priority);
    }

    @Override
    public void addedToEngine(Engine engine) {
        Log.info("Player Input System was added");
        Family family = Family.all(PositionComponent.class, DirectionComponent.class, PlayerComponent.class, CharacterAnimationComponent.class).get();
        entities = engine.getEntitiesFor(family);
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            entities.forEach(entity -> startAction(entity, Swing, Empty));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
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

    private void startAction(Entity entity, PlayerComponent.Action action, Direction direction) {
        DirectionComponent directionComponent = DirectionMapper.get(entity);
        PlayerComponent playerComponent = PlayerMapper.get(entity);
        CharacterAnimationComponent animationComponent = CharacterAnimationMapper.get(entity);
        if (!Empty.equals(direction)) { directionComponent.set(direction); }
        animationComponent.setAnimation(action.name(), action.isLoopable());
    }
}
