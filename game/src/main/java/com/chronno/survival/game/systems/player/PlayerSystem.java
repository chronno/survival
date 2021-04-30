package com.chronno.survival.game.systems.player;


import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.chronno.survival.game.components.type.EntityTypeComponent;
import com.esotericsoftware.minlog.Log;


public class PlayerSystem extends EntitySystem {

    private static final ComponentMapper<EntityTypeComponent> EntityTypeMapper = ComponentMapper.getFor(EntityTypeComponent.class);

    private ImmutableArray<Entity> entities;

    public PlayerSystem(int priority) {
        super(priority);
    }

    @Override
    public void addedToEngine(Engine engine) {
        Log.info("Player System was added");
        Family family = Family.all(EntityTypeComponent.class).get();
        entities = engine.getEntitiesFor(family);
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        entities.forEach(this::update);
    }

    private void update(Entity entity) {
        EntityTypeComponent entityTypeComponent = EntityTypeMapper.get(entity);
        entityTypeComponent.update(entity);
    }

}
