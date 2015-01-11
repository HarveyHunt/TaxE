package com.taxe.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.taxe.game.Coordinate;

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
    private TextDisplay timer;

    public HUD(int player1Health, int player2Health) {
        texHUD = new Texture("UI/HUD.png");
        texHealthbarGreen = new Texture("UI/Healthbar green.png");
        texHealthbarRed = new Texture("UI/Healthbar Red.png");
        texPlayer1Icon = new Texture("UI/Player1 Icon.png");
        texPlayer2Icon = new Texture("UI/Player2 Icon.png");

        this.player1Health = player1Health;
        this.player2Health = player2Health;

        endTurn = new Button(new Texture("UI/Clock.png"), new Coordinate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 37.5)) {
            @Override
            public void clicked() {
                // endturn clicked
                System.out.println("CLICKED MATE! GET IN!");
            }
        };
        addActor(endTurn);

        timer = new TextDisplay("34s", new Coordinate(Gdx.graphics.getWidth() / 2 - 20, Gdx.graphics.getHeight() - 37.5));
        addActor(timer);
    }

    public void resize(){
        endTurn.setCoordinate(new Coordinate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 37.5));
    }

    private void drawElement(Batch batch, Texture tex, float x, float y, float w, float h) {
        drawElement(batch, tex, x, y, w, h, 0, false, false);
    }

    private void drawElement(Batch batch, Texture tex, float x, float y, float w, float h, boolean flipX, boolean flipY) {
        drawElement(batch, tex, x, y, w, h, 0, flipX, flipY);
    }

    private void drawElement(Batch batch, Texture tex, float x, float y, float w, float h, float rotation, boolean flipX, boolean flipY) {
        batch.draw( // Draw main hud background
                tex, x, y,
                0, 0, w, h,
                1, 1, rotation,
                0, 0, tex.getWidth(), tex.getHeight(),
                flipX, flipY);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        drawElement( // HUD background
                batch, texHUD,
                0, Gdx.graphics.getHeight() - texHUD.getHeight(),
                Gdx.graphics.getWidth(), texHUD.getHeight());

        drawElement( // Player 1 HP Red
                batch, texHealthbarRed,
                60, Gdx.graphics.getHeight() - 30 - texHealthbarRed.getHeight(),
                Gdx.graphics.getWidth() / 2 - 100, texHealthbarRed.getHeight()
        );

        drawElement( // Player 2 HP Red
                batch, texHealthbarRed,
                Gdx.graphics.getWidth() - 60 - (Gdx.graphics.getWidth() / 2 - 100), Gdx.graphics.getHeight() - 30 - texHealthbarRed.getHeight(),
                Gdx.graphics.getWidth() / 2 - 100, texHealthbarRed.getHeight(),
                true, false
        );

        drawElement( // Player 1 HP
                batch, texHealthbarGreen,
                60, Gdx.graphics.getHeight() - 30 - texHealthbarGreen.getHeight(),
                (Gdx.graphics.getWidth() / 2 - 100) * 0.01f * player1Health, texHealthbarGreen.getHeight()
        );

        drawElement( // Player 2 HP Red
                batch, texHealthbarGreen,
                Gdx.graphics.getWidth() - 60 - ((Gdx.graphics.getWidth() / 2 - 100) * 0.01f * player2Health), Gdx.graphics.getHeight() - 30 - texHealthbarGreen.getHeight(),
                (Gdx.graphics.getWidth() / 2 - 100) * 0.01f * player2Health, texHealthbarGreen.getHeight(),
                true, false
        );

        drawElement( // Player 1 Icon
                batch, texPlayer1Icon,
                -20, Gdx.graphics.getHeight() - texPlayer1Icon.getHeight() + 20,
                texPlayer1Icon.getWidth(), texPlayer1Icon.getHeight()
        );

        drawElement( // Player 2 Icon
                batch, texPlayer1Icon,
                Gdx.graphics.getWidth() + 20 - texPlayer2Icon.getWidth(), Gdx.graphics.getHeight() - texPlayer2Icon.getHeight() + 20,
                texPlayer1Icon.getWidth(), texPlayer1Icon.getHeight()
        );

        if (endTurn.getState() == ButtonState.IDLE) {
            timer.setVisible(true);
        } else {
            timer.setVisible(false);
        }

        drawChildren(batch, parentAlpha);
    }

}
