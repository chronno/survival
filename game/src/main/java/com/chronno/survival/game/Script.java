package com.chronno.survival.game;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.esotericsoftware.minlog.Log;
import games.rednblack.editor.renderer.components.SpineDataComponent;
import games.rednblack.editor.renderer.scripts.BasicScript;
import games.rednblack.editor.renderer.utils.ItemWrapper;
import games.rednblack.h2d.extention.spine.SpineObjectComponent;

public class Script extends BasicScript {

    private final PooledEngine engine;
    private SpineObjectComponent player;

    public Script(PooledEngine engine) {
        this.engine = engine;
    }

    @Override
    public void init(Entity item){
        super.init(item);
        ItemWrapper itemWrapper = new ItemWrapper(item);
        player = itemWrapper.getComponent(SpineObjectComponent.class);
        player.setAnimation("Down Walk");
    }

    @Override
    public void act(float v) {

    }

    @Override
    public void dispose() {

    }
}
