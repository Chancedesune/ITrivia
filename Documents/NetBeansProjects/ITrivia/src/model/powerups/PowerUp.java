//POWERUP SUPERCLASS (SAAN NAG IINHERIT YUNG 50/50 AND SKIP)
package model.powerups;

import ui.GameFrame;

public abstract class PowerUp {
    protected String name;
    protected int cost; // tokens required to use this power-up

    public PowerUp(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    // Abstract method that defines how the power-up is applied
    public abstract void applyPowerUp(GameFrame game);
}
