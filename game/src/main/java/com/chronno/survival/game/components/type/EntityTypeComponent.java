package com.chronno.survival.game.components.type;

import com.badlogic.ashley.core.Entity;
import com.chronno.survival.game.components.BaseComponent;

public class EntityTypeComponent extends BaseComponent {

    private EntityType type;

    public EntityType getType() {
        return type;
    }

    public void set(EntityType type) {
        this.type = type;
    }

    @Override
    public void reset() {
        type = null;
    }

    public void update(Entity entity) {
        type.update(entity);
    }
}
