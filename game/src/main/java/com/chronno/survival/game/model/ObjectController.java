package com.chronno.survival.game.model;

import com.badlogic.ashley.core.Entity;
import com.chronno.survival.game.services.ProjectLoader;
import games.rednblack.editor.renderer.scripts.IScript;

public class ObjectController implements IScript {

    protected ProjectLoader projectLoader;

    public ObjectController(WorldObject object) {
        this.projectLoader = object.getProjectLoader();
        object.appendScript(this);
    }

    @Override
    public void init(Entity entity) {

    }

    @Override
    public void act(float v) {

    }

    @Override
    public void dispose() {

    }
}
