package com.taxe.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.taxe.game.Coordinate;

/**
 * Created by Owen on 09/01/2015.
 */
public class HUD extends Group {

    private Texture texture;
    private Button endTurn;
    private TextDisplay textTest;

    public HUD() {
        texture = new Texture("HUD.png");

        endTurn = new Button(new Texture("endturn.png"), new Coordinate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - texture.getHeight() / 2));
        addActor(endTurn);
        endTurn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Button Clicked");
            }
        });

        textTest = new TextDisplay("This is a sentence!", new Coordinate(50, 50));
        addActor(textTest);
    }

    public void resize(){
        endTurn.setCoordinate(new Coordinate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - texture.getHeight() / 2));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(
                texture, 0, Gdx.graphics.getHeight() - texture.getHeight(),
                0, 0, Gdx.graphics.getWidth(), texture.getHeight(),
                1, 1, 0,
                0, 0, texture.getWidth(), texture.getHeight(),
                false, false);
        drawChildren(batch, parentAlpha);
    }

}
