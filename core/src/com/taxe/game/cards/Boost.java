package com.taxe.game.cards;

import com.taxe.game.GameCore;
import com.taxe.game.commands.Commands;
import com.taxe.game.nodes.City;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by henry on 09/02/15.
 */
public class Boost extends Card {
    public Boost() {
        super();
        command = Commands.boostTrainCommand;

        qtyLabel.setPosition(48, 42);

        setPosition(0, 0);
        setSize(getTexture().getWidth(), getTexture().getHeight());
    }

    public Texture getTexture() { return CardTextures.BOOST_CARD; }

    @Override
    public void clicked(GameCore game) {
        if (game.getActivePlayer().getBoostQty() > 0) {
            command.executeCommand(game, null);
            game.getActivePlayer().adjustBoostQty(-1);

            game.hand.UpdateCardLabels(game);
        }

        else {
            System.out.println("Insufficient Boost Cards!");
        }
    }
}
