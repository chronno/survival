package com.chronno.survival.game.model.spine;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.chronno.survival.game.model.ObjectController;
import games.rednblack.editor.renderer.components.SpineDataComponent;
import games.rednblack.editor.renderer.utils.ItemWrapper;
import games.rednblack.h2d.extention.spine.SpineObjectComponent;

public class SpineObjectController extends ObjectController {

    private SpineObjectComponent spineObject;
    private SpineDataComponent spineData;
    private SpineAnimationController animationController;

    @Override
    public void init(Entity item){
        ItemWrapper itemWrapper = new ItemWrapper(item);
        spineObject = itemWrapper.getComponent(SpineObjectComponent.class);
        spineData = itemWrapper.getComponent(SpineDataComponent.class);
        animationController = new SpineAnimationController(spineObject, spineData);
        requestAnimation("Down Idle", true);
    }

    protected void requestAnimation(String animationName, Boolean interruptible) {
        this.animationController.requestAnimation(new AnimationRequest(animationName, interruptible));
    }

    @Override
    public void act(float v) {
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
           requestAnimation("Down Swing", false);
        } else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
           requestAnimation("Down Walk", true);
        } else { requestAnimation("Down Idle", true);
        }
    }
}
