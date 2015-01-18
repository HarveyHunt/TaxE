package com.taxe.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.game.GameCore;
import com.taxe.game.commands.Commands;
import com.taxe.game.player.Player;
import com.taxe.game.resources.Fuel;
import com.taxe.game.resources.Gold;
import com.taxe.game.util.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Owen on 09/01/2015.
 */
public class HUD extends Group {

    private GameCore game;

    private Button endTurn;
    private Button setPath;
    private Button cancelPath;

    private HashMap<Player, Healthbar> healthbars;
    private ArrayList<TextDisplay> resourceTexts;

    public HUD(GameCore game) {
        this.game = game;

        healthbars = new HashMap<>();

        Healthbar healthbar1 = new Healthbar(false);
        healthbars.put(game.getPlayers().get(0), healthbar1);
        addActor(healthbar1);
        Healthbar healthbar2 = new Healthbar(true);
        healthbars.put(game.getPlayers().get(1), healthbar2);
        addActor(healthbar2);

        endTurn = new Button(GuiTextures.END_TURN_BUTTON) {
            @Override
            public void clicked(GameCore game) {
                Commands.moveTrainsCommand.executeCommand(game, this);
            }
        };
        addActor(endTurn);
        setPath = new Button(GuiTextures.CONFIRM_ROUTE_BUTTON) {
            @Override
            public void clicked(GameCore gameCore) {
                Commands.savePathCommand.executeCommand(gameCore, this);
            }
        };
        addActor(setPath);
        cancelPath = new Button(GuiTextures.CANCEL_ROUTE_BUTTON) {
            @Override
            public void clicked(GameCore gameCore) {
                Commands.resetPathCommand.executeCommand(gameCore, null);
            }
        };
        addActor(cancelPath);

        resourceTexts = new ArrayList<>();
        TextDisplay player1Text = new TextDisplay("Gold: 0   Fuel: 0", Color.YELLOW, 1f);
        addActor(player1Text);
        resourceTexts.add(player1Text);
        TextDisplay player2Text = new TextDisplay("Gold: 0   Fuel: 0", Color.YELLOW, 1f);
        addActor(player2Text);
        resourceTexts.add(player2Text);

        setPlayerText(0, game.getPlayers().get(0).getGold(), game.getPlayers().get(0).getFuel());
        setPlayerText(1, game.getPlayers().get(1).getGold(), game.getPlayers().get(1).getFuel());

        resize();
        hidePathButtons();
    }

    public void setPlayerText(int player, Gold gold, Fuel fuel) {
        CharSequence text = "Gold: " + gold.getQuantity() + "     Fuel: " + (fuel.getUsedFuel() + 1) + "/" + (fuel.getFuelCap() + 1);
        resourceTexts.get(player).setText(text);
    }

    public Healthbar getHealthbar(Player p) {
        return healthbars.get(p);
    }

    public void lockButtons() {
        endTurn.setTouchable(Touchable.disabled);
        setPath.setTouchable(Touchable.disabled);
        cancelPath.setTouchable(Touchable.disabled);
    }

    public void unlockButtons() {
        endTurn.setTouchable(Touchable.enabled);
        setPath.setTouchable(Touchable.enabled);
        cancelPath.setTouchable(Touchable.enabled);
    }

    public void showPathButtons() {
        setPath.setVisible(true);
        cancelPath.setVisible(true);
    }

    public void hidePathButtons() {
        setPath.setVisible(false);
        cancelPath.setVisible(false);
    }

    public void resize() {
        healthbars.get(game.getPlayers().get(0)).resize(new Rectangle(
                        60, Gdx.graphics.getHeight() - 70,
                        Gdx.graphics.getWidth() / 2 - 100, 40)
        );
        healthbars.get(game.getPlayers().get(1)).resize(new Rectangle(
                        Gdx.graphics.getWidth() / 2 + 40, Gdx.graphics.getHeight() - 70,
                        Gdx.graphics.getWidth() / 2 - 100, 40));

        endTurn.setCoordinate(new Coordinate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 36.75f));
        setPath.setCoordinate(new Coordinate(Gdx.graphics.getWidth() / 2 - 76, Gdx.graphics.getHeight() - 109));
        cancelPath.setCoordinate(new Coordinate(Gdx.graphics.getWidth() / 2 + 76, Gdx.graphics.getHeight() - 109));
        resourceTexts.get(0).setCoordinate(new Rectangle(0, Gdx.graphics.getHeight() - 30, Gdx.graphics.getWidth() / 2, 30));
        resourceTexts.get(1).setCoordinate(new Rectangle(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 30, Gdx.graphics.getWidth() / 2, 30));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(
                GuiTextures.HUD_BACKGROUND,
                0, Gdx.graphics.getHeight() - GuiTextures.HUD_BACKGROUND.getHeight(),
                Gdx.graphics.getWidth(), GuiTextures.HUD_BACKGROUND.getHeight()
        );

        drawChildren(batch, parentAlpha);

        batch.draw(
                GuiTextures.PLAYER_1_ICON,
                -20, Gdx.graphics.getHeight() - GuiTextures.PLAYER_1_ICON.getHeight() + 20,
                GuiTextures.PLAYER_1_ICON.getWidth(), GuiTextures.PLAYER_1_ICON.getHeight()
        );
        batch.draw(
                GuiTextures.PLAYER_2_ICON,
                Gdx.graphics.getWidth() - GuiTextures.PLAYER_2_ICON.getWidth() + 20, Gdx.graphics.getHeight() - GuiTextures.PLAYER_2_ICON.getHeight() + 20,
                GuiTextures.PLAYER_2_ICON.getWidth(), GuiTextures.PLAYER_2_ICON.getHeight()
        );
    }

}
