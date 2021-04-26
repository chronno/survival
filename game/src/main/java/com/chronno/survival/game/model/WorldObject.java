package com.chronno.survival.game.model;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.chronno.survival.game.services.ProjectLoader;
import games.rednblack.editor.renderer.utils.ItemWrapper;
import games.rednblack.h2d.extention.spine.SpineObjectComponent;

public class WorldObject {

    protected final PooledEngine engine;
    protected final ItemWrapper itemWrapper;
    protected final ProjectLoader projectLoader;

    public WorldObject(Entity entity, PooledEngine engine, ProjectLoader projectLoader) {
        this.itemWrapper = new ItemWrapper(entity);
        this.engine = engine;
        this.projectLoader = projectLoader;
    }

    public WorldObject getChild(String id) {
        Entity entity = itemWrapper.getChild(id).getEntity();
        return new WorldObject(entity, engine, projectLoader);
    }

    public void appendScript(ObjectController objectController) {
       itemWrapper.addScript(objectController, engine);
    }

    public void addComponent(Component component) {
        itemWrapper.getEntity().add(component);
    }

    public ProjectLoader getProjectLoader() {
        return projectLoader;
    }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        return itemWrapper.getComponent(componentClass);
    }
}
