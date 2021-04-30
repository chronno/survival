package com.chronno.survival.game.components;

import com.chronno.survival.game.model.Action;

public final class ActionComponent extends BaseComponent {

    private Action currentAction = Action.Idle;

    public void set(Action nextAction) {
        this.currentAction = nextAction;
    }

    public Action getCurrentAction() {
        return currentAction;
    }

    @Override
    public void reset() {
    }
}
