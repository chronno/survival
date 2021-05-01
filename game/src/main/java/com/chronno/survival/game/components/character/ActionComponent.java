package com.chronno.survival.game.components.character;

import com.chronno.survival.game.components.BaseComponent;
import com.chronno.survival.game.model.Action;

public final class ActionComponent extends BaseComponent {

    private Action currentAction;

    public void set(Action nextAction) {
        this.currentAction = nextAction;
    }

    public Action getCurrentAction() {
        return currentAction;
    }

    @Override
    public void reset() {
        currentAction = Action.Idle;
    }
}
