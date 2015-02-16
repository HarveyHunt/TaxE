package com.taxe.game.commands;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.taxe.game.GameCore;
import com.taxe.game.cargo.Cargo;
import com.taxe.game.nodes.City;
import com.taxe.game.trains.Train;
import com.taxe.game.util.Pair;

/**
 * Attempt to load Cargo into the train that has stopped on City.
 */
public class LoadCargoCommand implements Commandable {
    /**
     * @param game instance of GameCore
     * @param target A Pair of the form: (City, Cargo).
     * @throws IllegalArgumentException if the Pair isn't of the form
     * (City, Cargo).
     */
    public void executeCommand(GameCore game, Object target) throws IllegalArgumentException {
        if (!(target instanceof Pair))
            throw new IllegalArgumentException("target not instance of Pair");
        if (!(((Pair) target).left instanceof City))
            throw new IllegalArgumentException("target.left not instance of City");
        if (!(((Pair) target).right instanceof Cargo))
            throw new IllegalArgumentException("target.right not instance of Cargo");

        Pair pair = (Pair) target;
        Label label = new Label("You don't own a train on " + pair.left,
                new Label.LabelStyle(new BitmapFont(), Color.ORANGE));

        for (Train t : game.getActivePlayer().getTrains()) {
            if (t.getNode() == pair.left)
                if (t.loadCargo((Cargo) pair.right))
                    label = new Label("Loaded " + pair.right,
                            new Label.LabelStyle(new BitmapFont(), Color.GREEN));
                else
                    label = new Label("Couldn't load " + pair.right,
                            new Label.LabelStyle(new BitmapFont(), Color.RED));
        }

        label.setAlignment(Align.center);
        game.getGui().getNotificationBox().addLabel(label, 5.0f);
    }
}