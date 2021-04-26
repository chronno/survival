package com.chronno.survival.game.model.character;

public enum Animation {
    Bow {
        @Override
        public Boolean interruptible() {
            return false;
        }
    },
    Death {
        @Override
        public Boolean interruptible() {
            return false;
        }
    },
    Hurt {
        @Override
        public Boolean interruptible() {
            return false;
        }
    },
    Idle {
        @Override
        public Boolean interruptible() {
            return true;
        }
    },
    Spell {
        @Override
        public Boolean interruptible() {
            return false;
        }
    },
    Swing {
        @Override
        public Boolean interruptible() {
            return false;
        }
    },
    Thrust {
        @Override
        public Boolean interruptible() {
            return false;
        }
    },
    Walk {
        @Override
        public Boolean interruptible() {
            return true;
        }
    };

    public abstract Boolean interruptible();
}
