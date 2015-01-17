package com.taxe.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.game.GameCore;
import com.taxe.game.commands.*;
import com.taxe.game.resources.Fuel;
import com.taxe.game.resources.Gold;
import com.taxe.game.util.Coordinate;

import java.util.ArrayList;

/**
 * Created by Owen on 09/01/2015.
 */
public class HUD extends Group {

    private Texture texHUD;
    private Texture texPlayer1Icon;
    private Texture texPlayer2Icon;
    private Texture texHealthbarRed;
    private Texture texHealthbarGreen;

    private int player1Health;
    private int player2Health;

    private Button endTurn;
    private Button setPath;
    private Button cancelPath;

    private ArrayList<TextDisplay> resourceTexts;

    public HUD(GameCore game) {
        texHUD = new Texture("UI/HUD.png");
        texHealthbarGreen = new Texture("UI/Healthbar green.png");
        texHealthbarRed = new Texture("UI/Healthbar Red.png");
        texPlayer1Icon = new Texture("UI/Player1 Icon.png");
        texPlayer2Icon = new Texture("UI/Player2 Icon.png");

        this.player1Health = game.getPlayers().get(0).getHomebase().getHealth();
        this.player2Health = game.getPlayers().get(1).getHomebase().getHealth();

        endTurn = new Button(new Texture("UI/Clock Square.png")) {
            @Override
            public void clicked(GameCore game) {
                Commands.moveTrainsCommand.executeCommand(game, this);
            }
        };
        addActor(endTurn);
        setPath = new Button(new Texture("UI/confirm route.png")) {
            @Override
            public void clicked(GameCore gameCore) {
                Commands.savePathCommand.executeCommand(gameCore, this);
            }
        };
        addActor(setPath);
        cancelPath = new Button(new Texture("UI/cancel route.png")) {
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

        resize();
        hidePathButtons();
    }

    public void setPlayerText(int player, Gold gold, Fuel fuel) {
        CharSequence text = "Gold: " + gold.getQuantity()  + "     Fuel: " + (fuel.getUsedFuel() + 1) + "/" + (fuel.getFuelCap() + 1);
        resourceTexts.get(player).setText(text);
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

    public void setHealth(int player1Health, int player2Health) {
        this.player1Health = player1Health;
        this.player2Health = player2Health;
    }

    public void resize() {
        endTurn.setCoordinate(new Coordinate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 36.75f));
        setPath.setCoordinate(new Coordinate(Gdx.graphics.getWidth() / 2 - 76, Gdx.graphics.getHeight() - 109));
        cancelPath.setCoordinate(new Coordinate(Gdx.graphics.getWidth() / 2 + 76, Gdx.graphics.getHeight() - 109));
        resourceTexts.get(0).setCoordinate(new Coordinate(120, Gdx.graphics.getHeight() - 9));
        resourceTexts.get(1).setCoordinate(new Coordinate(Gdx.graphics.getWidth() / 2 + 120, Gdx.graphics.getHeight() - 9));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        GUI.drawElement( // HUD background
                batch, texHUD,
                0, Gdx.graphics.getHeight() - texHUD.getHeight(),
                Gdx.graphics.getWidth(), texHUD.getHeight());

        GUI.drawElement( // Player 1 HP Red
                batch, texHealthbarRed,
                60, Gdx.graphics.getHeight() - 30 - texHealthbarRed.getHeight(),
                Gdx.graphics.getWidth() / 2 - 100, texHealthbarRed.getHeight()
        );

        GUI.drawElement( // Player 2 HP Red
                batch, texHealthbarRed,
                Gdx.graphics.getWidth() - 60 - (Gdx.graphics.getWidth() / 2 - 100), Gdx.graphics.getHeight() - 30 - texHealthbarRed.getHeight(),
                Gdx.graphics.getWidth() / 2 - 100, texHealthbarRed.getHeight(),
                true, false
        );

        GUI.drawElement( // Player 1 HP
                batch, texHealthbarGreen,
                60, Gdx.graphics.getHeight() - 30 - texHealthbarGreen.getHeight(),
                (Gdx.graphics.getWidth() / 2 - 100) * 0.01f * player1Health / 5, texHealthbarGreen.getHeight()
        );

        GUI.drawElement( // Player 2 HP
                batch, texHealthbarGreen,
                Gdx.graphics.getWidth() - 60 - ((Gdx.graphics.getWidth() / 2 - 100) * 0.01f * player2Health / 5), Gdx.graphics.getHeight() - 30 - texHealthbarGreen.getHeight(),
                (Gdx.graphics.getWidth() / 2 - 100) * 0.01f * player2Health / 5, texHealthbarGreen.getHeight(),
                true, false
        );

        GUI.drawElement( // Player 1 Icon
                batch, texPlayer1Icon,
                -20, Gdx.graphics.getHeight() - texPlayer1Icon.getHeight() + 20,
                texPlayer1Icon.getWidth(), texPlayer1Icon.getHeight()
        );

        GUI.drawElement( // Player 2 Icon
                batch, texPlayer2Icon,
                Gdx.graphics.getWidth() + 20 - texPlayer2Icon.getWidth(), Gdx.graphics.getHeight() - texPlayer2Icon.getHeight() + 20,
                texPlayer1Icon.getWidth(), texPlayer1Icon.getHeight()
        );

        drawChildren(batch, parentAlpha);
    }

}
