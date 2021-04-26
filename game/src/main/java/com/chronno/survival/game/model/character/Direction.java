package com.chronno.survival.game.model.character;

public enum Direction {
    Down {
        @Override
        public String skeletonName() {
            return "Down";
        }

        @Override
        public Integer spatialFix() {
            return 0;
        }
    },
    Up {
        @Override
        public String skeletonName() {
            return "Up";
        }

        @Override
        public Integer spatialFix() {
            return 0;
        }
    },
    Left {
        @Override
        public String skeletonName() {
            return "Side";
        }

        @Override
        public Integer spatialFix() {
            return 256;
        }
    },
    Right {
        @Override
        public String skeletonName() {
            return "Side";
        }

        @Override
        public Integer spatialFix() {
            return -256;
        }
    },
    Empty {
        @Override
        public String skeletonName() {
            return null;
        }

        @Override
        public Integer spatialFix() {
            return 0;
        }
    };


    public abstract String skeletonName();
    public abstract Integer spatialFix();

}
