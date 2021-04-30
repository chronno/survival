package com.chronno.survival.game.model;

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
    },
    Spell {
        @Override
        public Boolean isLoopable() {
            return false;
        }
    };;

    public abstract Boolean isLoopable();
}