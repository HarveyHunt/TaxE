package com.taxe.game.cards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.taxe.game.GameCore;
import com.taxe.game.commands.Commands;

/**
 * A boost card increases the speed of all trains owned by a player.
 */
public class Boost extends Card {
    public Boost() {
        super();
        qtyLabel.setPosition(48, 42);
        setPosition(0, 0);
        setSize(getTexture().getWidth(), getTexture().getHeight());
    }

    public Texture getTexture() {
        return CardTextures.BOOST_CARD;
    }

    @Override
    public void clicked(GameCore game) {
        if ((game.getActivePlayer().getBoostQty() > 0) && (game.hand.isCardUsable())) {
            Commands.boostTrainCommand.executeCommand(game, null);
            game.getActivePlayer().adjustBoostQty(-1);

            game.hand.updateCardLabels(game);
        } else {
            Label label = new Label("Cannot use card",
                    new Label.LabelStyle(new BitmapFont(), Color.RED));
            label.setAlignment(Align.center);

            game.getGui().getNotificationBox().addLabel(label, 5.0f);
        }
    }
}
