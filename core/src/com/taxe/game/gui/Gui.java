package com.taxe.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.taxe.game.GameCore;
import com.taxe.game.util.Coordinate;

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
     * @param winner 0 = player 1, 1 = player 2
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

    public GameCore getGame() {
        return game;
    }

    public Hud getHud() {
        return hud;
    }

    public InfoDisplay getInfoDisplay() {
        return infoDisplay;
    }

    /*@Override
    public void draw(Batch batch, float parentAlpha) {
        drawChildren(batch, parentAlpha);
    }*/

}
