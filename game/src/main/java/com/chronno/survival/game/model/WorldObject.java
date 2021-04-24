package com.chronno.survival.game.model;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import games.rednblack.editor.renderer.utils.ItemWrapper;

public class WorldObject {

    protected final PooledEngine engine;
    protected final ItemWrapper itemWrapper;

    public WorldObject(Entity entity, PooledEngine engine) {
        this.itemWrapper = new ItemWrapper(entity);
        this.engine = engine;
    }

    public WorldObject getChild(String id) {
        Entity entity = itemWrapper.getChild(id).getEntity();
        return new WorldObject(entity, this.engine);
    }

    public void appendScript(ObjectController objectController) {
       this.itemWrapper.addScript(objectController, this.engine);
    }
}
