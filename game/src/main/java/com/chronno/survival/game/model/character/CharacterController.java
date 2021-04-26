package com.chronno.survival.game.model.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.chronno.survival.game.model.ObjectController;
import com.chronno.survival.game.model.WorldObject;
import com.chronno.survival.game.services.player.PlayerAnimationService;

import static com.chronno.survival.game.model.character.Animation.*;
import static com.chronno.survival.game.model.character.Direction.*;

public class CharacterController extends ObjectController {

    private final PlayerAnimationService animationService;
    public CharacterController(WorldObject object) {
        super(object);
        this.animationService = new PlayerAnimationService(projectLoader, object);
    }

    @Override
    public void act(float v) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            animationService.addToAnimationsQueue(Swing, Empty);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            animationService.addToAnimationsQueue(Walk, Down);
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            animationService.addToAnimationsQueue(Walk, Up);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            animationService.addToAnimationsQueue(Walk, Left);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            animationService.addToAnimationsQueue(Walk, Right);
        } else {
            animationService.addToAnimationsQueue(Idle, Empty);
        }
    }

}
