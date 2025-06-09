//FIFTY-FIFTY CLASS
package model.powerups;

import ui.GameFrame;

public class FiftyFifty extends PowerUp {

    public FiftyFifty() {
        super("50/50", 2);  //2 = cost of the token
    }

    @Override
    public void applyPowerUp(GameFrame game) {
        game.removeTwoWrongOptions();
    }
}
