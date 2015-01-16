package com.taxe.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.game.GameCore;
import com.taxe.game.commands.MoveTrainsCommand;
import com.taxe.game.commands.SavePathCommand;
import com.taxe.game.commands.SwitchPlayerCommand;
import com.taxe.game.util.Coordinate;

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

    private TextDisplay turnText;

    public HUD(int player1Health, int player2Health) {
        texHUD = new Texture("UI/HUD.png");
        texHealthbarGreen = new Texture("UI/Healthbar green.png");
        texHealthbarRed = new Texture("UI/Healthbar Red.png");
        texPlayer1Icon = new Texture("UI/Player1 Icon.png");
        texPlayer2Icon = new Texture("UI/Player2 Icon.png");

        this.player1Health = player1Health;
        this.player2Health = player2Health;

        endTurn = new Button(new Texture("UI/Clock Square.png"), new Coordinate()) {
            @Override
            public void clicked(GameCore game) {
                MoveTrainsCommand.executeCommand(game, this);
                SwitchPlayerCommand.executeCommand(game, this);
            }
        };
        addActor(endTurn);
        setPath = new Button(new Texture("UI/Clock Square.png"), new Coordinate()) {
            @Override
            public void clicked(GameCore gameCore) {
                SavePathCommand.executeCommand(gameCore, this);
            }
        };
        addActor(setPath);

        turnText = new TextDisplay("Player 1 : Turn 1", new Coordinate(), Color.YELLOW, 1f);
        addActor(turnText);

        resize();
    }

    public void lockButtons() {
        endTurn.setTouchable(Touchable.disabled);
        setPath.setTouchable(Touchable.disabled);
    }

    public void unlockButtons() {
        endTurn.setTouchable(Touchable.enabled);
        setPath.setTouchable(Touchable.enabled);
    }

    public void setHealth(int player1Health, int player2Health) {
        this.player1Health = player1Health;
        this.player2Health = player2Health;
    }

    public TextDisplay getTurnText() {
        return turnText;
    }

    public void resize() {
        endTurn.setCoordinate(new Coordinate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 36.75f));
        setPath.setCoordinate(new Coordinate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 109));
        turnText.setCoordinate(new Coordinate(200, Gdx.graphics.getHeight() - 9));
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
                (Gdx.graphics.getWidth() / 2 - 100) * 0.01f * player1Health, texHealthbarGreen.getHeight()
        );

        GUI.drawElement( // Player 2 HP Red
                batch, texHealthbarGreen,
                Gdx.graphics.getWidth() - 60 - ((Gdx.graphics.getWidth() / 2 - 100) * 0.01f * player2Health), Gdx.graphics.getHeight() - 30 - texHealthbarGreen.getHeight(),
                (Gdx.graphics.getWidth() / 2 - 100) * 0.01f * player2Health, texHealthbarGreen.getHeight(),
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
