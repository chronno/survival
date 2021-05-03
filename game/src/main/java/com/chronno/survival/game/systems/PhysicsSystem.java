package com.chronno.survival.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.physics.box2d.World;
import com.chronno.survival.game.components.physics.PolygonComponent;
import com.esotericsoftware.minlog.Log;


public class PhysicsSystem extends EntitySystem {
    private World world;
    private ImmutableArray<Entity> entities;

    public PhysicsSystem(int priority, World world) {
        super(priority);
        this.world = world;
    }

    @Override
    public void addedToEngine(Engine engine) {
        Log.info("Physics System was added");
        Family family = Family.all(PolygonComponent.class).get();
        entities = engine.getEntitiesFor(family);

    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }


    private Boolean isAdded = false;
    @Override
    public void update(float deltaTime) {
        world.step(deltaTime, 6, 2);
    }

}
