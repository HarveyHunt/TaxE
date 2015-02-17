package com.taxe.game.cards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.taxe.game.GameCore;
import com.taxe.game.commands.Commands;

/**
 * Created by henry on 09/02/15.
 */
public class Block extends Card {
    public Block() {
        super();
        command = Commands.blockCityCommand;

        qtyLabel.setPosition(112, 42);

        setPosition(64, 0);
        setSize(getTexture().getWidth(), getTexture().getHeight());
    }

    public Texture getTexture() {
        return CardTextures.BLOCK_CARD;
    }

    @Override
    public void clicked(GameCore game) {
        if ((game.getActivePlayer().getBlockQty() > 0) && (game.hand.isCardUsable())) {
            command.executeCommand(game, null);
            game.getActivePlayer().adjustBlockQty(-1);

            game.hand.updateCardLabels(game);
        }

        else {
            Label label = new Label("Cannot use card",
                    new Label.LabelStyle(new BitmapFont(), Color.RED));
            label.setAlignment(Align.center);

            game.getGui().getNotificationBox().addLabel(label, 5.0f);
        }
    }
}
