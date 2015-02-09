package com.taxe.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.taxe.game.GameCore;
import com.taxe.game.util.Coordinate;

import javax.xml.soap.Text;

/**
 * The main Group that holds and handles all of the elements of the GUI (Graphical User Interface)
 */
public class Gui extends Group {

    private GameCore game;
    private Hud hud;
    private InfoDisplay infoDisplay;
    private GameEndMenu gameEndMenu;

    /**
     * Creates an instance of Gui
     * @param game the parent instance of GameCore
     */
    public Gui(GameCore game) {// Add Other Stuffs
        this.game = game;

        hud = new Hud(game);
        addActor(hud);

        infoDisplay = new InfoDisplay(game);
        addActor(infoDisplay);

        createNotification(GuiTextures.PLAYER_1_TURN_START, new Coordinate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2), 1);
    }

    /**
     * Resize all of the elements of the Gui
     */
    public void resize() {
        hud.resize();
        infoDisplay.resize();
        if (gameEndMenu != null) {
            gameEndMenu.resize();
        }
    }

    /**
     * Creates an instance of the end game menu
     * @param winner the player that won as an index value. (index value = player number - 1)
     */
    public void createGameEndMenu(int winner) {
        gameEndMenu = new GameEndMenu(winner);
        addActor(gameEndMenu);
    }

    /**
     * Create an instance of a notification
     * @param texture the notification image to show
     * @param coordinate the center position of the notification, usually new Coordinate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2)
     * @param duration time that the notification is displayed before it fades out
     */
    public void createNotification(Texture texture, Coordinate coordinate, float duration) {
        Notification notification = new Notification(this, texture, coordinate, duration);
        addActor(notification);
    }

    public void createTextNotification(CharSequence text, Coordinate coordinate, float duration) {
        Label label = new Label(text, new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        label.setAlignment(Align.center);
        TextNotification notification = new TextNotification(this, label, coordinate, duration);
        addActor(notification);
    }

    /**
     * returns the GameCore game instance
     * @return GameCore instance
     */
    public GameCore getGame() {
        return game;
    }

    /**
     * returns the Hud hud instance
     * @return hud instance
     */
    public Hud getHud() {
        return hud;
    }

    /**
     * returns the InfoDisplay instance
     * @return InfoDisplay instance
     */
    public InfoDisplay getInfoDisplay() {
        return infoDisplay;
    }

}
