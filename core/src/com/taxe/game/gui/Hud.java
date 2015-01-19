package com.taxe.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.taxe.game.GameCore;
import com.taxe.game.commands.Commands;
import com.taxe.game.player.Player;
import com.taxe.game.resources.Fuel;
import com.taxe.game.resources.Gold;
import com.taxe.game.util.Coordinate;

import java.util.HashMap;

/**
 * Created by Owen on 09/01/2015.
 */
public class Hud extends Group {

    private GameCore game;

    private Button endTurnButton;
    private Button setPathButton;
    private Button cancelPathButton;
    private HashMap<Player, Healthbar> healthbars;
    private HashMap<Player, Label> resourceTexts;
    private HashMap<Player, Image> playerIcons;

    public Hud(GameCore game) {
        this.game = game;

        Player player1 = game.getPlayers().get(0);
        Player player2 = game.getPlayers().get(1);

        healthbars = new HashMap<>();
        Healthbar p1Healthbar = new Healthbar(false);
        Healthbar p2Healthbar = new Healthbar(true);
        healthbars.put(player1, p1Healthbar);
        healthbars.put(player2, p2Healthbar);
        addActor(p1Healthbar); addActor(p2Healthbar);

        resourceTexts = new HashMap<>();
        Label p1Label = new Label("", new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        p1Label.setAlignment(Align.center);
        Label p2Label = new Label("", new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        p2Label.setAlignment(Align.center);
        resourceTexts.put(player1, p1Label);
        resourceTexts.put(player2, p2Label);
        setPlayerText(player1);
        setPlayerText(player2);
        addActor(p1Label); addActor(p2Label);

        playerIcons = new HashMap<>();
        Image p1Icon = new Image(GuiTextures.PLAYER_1_ICON);
        Image p2Icon = new Image(GuiTextures.PLAYER_2_ICON);
        playerIcons.put(player1, p1Icon);
        playerIcons.put(player2, p2Icon);
        addActor(p1Icon); addActor(p2Icon);

        endTurnButton = new Button(GuiTextures.END_TURN_BUTTON) {
            @Override
            public void clicked(GameCore game) {
                Commands.moveTrainsCommand.executeCommand(game, this);
            }
        };
        addActor(endTurnButton);
        setPathButton = new Button(GuiTextures.CONFIRM_ROUTE_BUTTON) {
            @Override
            public void clicked(GameCore gameCore) {
                Commands.savePathCommand.executeCommand(gameCore, this);
            }
        };
        addActor(setPathButton);
        cancelPathButton = new Button(GuiTextures.CANCEL_ROUTE_BUTTON) {
            @Override
            public void clicked(GameCore gameCore) {
                Commands.resetPathCommand.executeCommand(gameCore, this);
            }
        };
        addActor(cancelPathButton);

        resize();
        hidePathButtons();
    }

    public void setPlayerText(Player p) {
        Gold g = p.getGold();
        Fuel f = p.getFuel();
        CharSequence text = "Gold: " + g.getQuantity() + "     Fuel: " + f.getUsedFuel() + "/" + f.getFuelCap();
        resourceTexts.get(p).setText(text);
    }

    public Healthbar getHealthbar(Player p) {
        return healthbars.get(p);
    }

    public void lockButtons() {
        endTurnButton.setTouchable(Touchable.disabled);
        setPathButton.setTouchable(Touchable.disabled);
        cancelPathButton.setTouchable(Touchable.disabled);
    }

    public void unlockButtons() {
        endTurnButton.setTouchable(Touchable.enabled);
        setPathButton.setTouchable(Touchable.enabled);
        cancelPathButton.setTouchable(Touchable.enabled);
    }

    public void showPathButtons() {
        setPathButton.setVisible(true);
        cancelPathButton.setVisible(true);
    }

    public void hidePathButtons() {
        setPathButton.setVisible(false);
        cancelPathButton.setVisible(false);
    }

    public void resize() {
        Player player1 = game.getPlayers().get(0);
        Player player2 = game.getPlayers().get(1);
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        healthbars.get(player1).setBounds(60, height - 70, width / 2 - 100, 40);
        healthbars.get(player2).setBounds(width / 2 + 40, height - 70, width / 2 - 100, 40);
        resourceTexts.get(player1).setBounds(0, height - 30, width / 2, 30);
        resourceTexts.get(player2).setBounds(width / 2, height - 30, width / 2, 30);
        Image p1Icon = playerIcons.get(player1);
        Image p2Icon = playerIcons.get(player2);
        p1Icon.setPosition(-20, height - p1Icon.getHeight() + 20);
        p2Icon.setPosition(width - p2Icon.getWidth() + 20, height - p2Icon.getHeight() + 20);
        endTurnButton.setPosition(width / 2, height - 36.75f);
        setPathButton.setPosition(width / 2 - 76, height - 109);
        cancelPathButton.setPosition(width / 2 + 76, height - 109);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        int height = Gdx.graphics.getHeight();
        int width = Gdx.graphics.getWidth();
        batch.draw(
                GuiTextures.HUD_BACKGROUND,
                0, height - GuiTextures.HUD_BACKGROUND.getHeight(),
                width, GuiTextures.HUD_BACKGROUND.getHeight()
        );
        drawChildren(batch, parentAlpha);
    }

}
