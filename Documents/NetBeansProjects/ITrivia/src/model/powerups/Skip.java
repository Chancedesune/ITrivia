//SKIP CLASS
package model.powerups;

import ui.GameFrame;

public class Skip extends PowerUp {
    public Skip() {
        super("Skip", 3);  //3 = costs 3 tokens
    }
    @Override
    public void applyPowerUp(GameFrame game) {
        game.skipCurrentQuestion();
    }
}
