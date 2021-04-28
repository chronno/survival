package com.chronno.survival.game.components;

public class ActionComponent extends BaseComponent {

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

    public enum Action {
        Walk {
            @Override
            public Boolean isLoopable() {
                return true;
            }
        },
        Idle {
            @Override
            public Boolean isLoopable() {
                return true;
            }
        },
        Swing {
            @Override
            public Boolean isLoopable() {
                return false;
            }
        };

        public abstract Boolean isLoopable();
    }
}
