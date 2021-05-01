package com.chronno.survival.game.components.character;

import com.chronno.survival.game.components.BaseComponent;

public class StatsComponent extends BaseComponent {

    private Integer maxHealth;
    private Integer health;


    @Override
    public void reset() {
        health = 0;
        maxHealth = 0;
    }
}
