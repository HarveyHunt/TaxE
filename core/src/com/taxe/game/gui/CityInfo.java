package com.taxe.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.taxe.game.GameCore;
import com.taxe.game.cargo.Cargo;
import com.taxe.game.commands.Commands;
import com.taxe.game.nodes.City;
import com.taxe.game.tasks.Task;
import com.taxe.game.trains.TrainTextures;
import com.taxe.game.util.Pair;

import java.util.ArrayList;

/**
 * Stores information about a city, such as Name, activePlayer's level of
 * influence and the cargoes that the city holds.
 */
public class CityInfo extends Group {

    private City city;
    private Label cityName;
    private Label cityInfluence;
    private ArrayList<Button> buttons;
    private Button unloadButton;
    private GameCore game;

    private final int LEFT_START = Gdx.graphics.getWidth() - 200;
    private final int BOTTOM_MARGIN = 10;
    private final int LABEL_GAP = 20;

    public CityInfo(GameCore game) {
        this.city = null;
        this.game = game;
        setupLabels();
        buttons = new ArrayList<>();
    }

    /**
     * Create the label objects that will store information about the city.
     */
    private void setupLabels() {
        this.cityName = new Label("",  new Label.LabelStyle(new BitmapFont(),
                Color.WHITE));
        this.cityName.setAlignment(Align.left);
        this.cityName.setPosition(LEFT_START, BOTTOM_MARGIN);
        addActor(this.cityName);

        this.cityInfluence = new Label("", new Label.LabelStyle(new BitmapFont(),
                Color.WHITE));
        this.cityInfluence.setAlignment(Align.left);
        this.cityInfluence.setPosition(LEFT_START, BOTTOM_MARGIN + LABEL_GAP);
        addActor(this.cityInfluence);
    }

    /**
     * Remove the buttons from the scene so that rendering a different city
     * doesn't mean old cargo buttons are displayed.
     */
    private void removeCargoButtons() {
        for (Button b : buttons)
            removeActor(b);
        buttons.clear();
    }

    public City getCity() {
        return city;
    }

    /**
     * Update labels with the information for city.
     * @param city The city that we are displaying info for.
     */
    public void setCity(City city) {
        removeCargoButtons();

        this.city = city;
        this.cityName.setText("City name: " + city.getId());
        this.cityInfluence.setText("City influence: " + city.getInfluence(game.getActivePlayer().id));

        generateCargoButtons();
        generateUnloadButton();
    }

    private void generateCargoButtons() {
        int i = 0;
        for(Cargo c : city.getCargoList()) {
            final Pair pair = new Pair(city, c);

            Button button = new Button(c.getTexture()) {
                @Override
                public void clicked(GameCore game) {
                    Commands.loadCargoCommand.executeCommand(game, pair);
                }
            };

            button.setPosition(LEFT_START + (i * button.getWidth()),
                    BOTTOM_MARGIN + (2 * LABEL_GAP));

            addActor(button);
            buttons.add(button);
            i++;
        }
    }

    private void generateUnloadButton() {
        boolean found = false;

        for (Task t : game.getTasks()) {
            if (t.getEndCity() == city) {
                found = true;
                break;
            }
        }

        if (found) {
            // TODO: Change this to a real texture.
            unloadButton = new Button(GuiTextures.MINUS_BUTTON) {
                @Override
                public void clicked(GameCore game) {
                    Commands.unloadCargoCommand.executeCommand(game, getCity());
                }
            };
            unloadButton.setPosition(LEFT_START + (unloadButton.getWidth()),
                    BOTTOM_MARGIN + (3 * LABEL_GAP));

            addActor(unloadButton);
        }

    }
}
