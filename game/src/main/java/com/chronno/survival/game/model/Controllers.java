package com.chronno.survival.game.model;

import com.chronno.survival.game.model.character.CharacterController;

public class Controllers {

    public static CharacterController spineObjectController(WorldObject object) {
        return new CharacterController(object);
    }
}
