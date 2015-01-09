package com.taxe.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.taxe.game.Coordinate;

/**
 * Created by Owen on 09/01/2015.
 */
public class Button extends Actor {

    private Texture texture;
    private Coordinate coordinate;
    private int state; // 0 idle; 1 hover; 2 pressed
    
    public Button(Texture texture, Coordinate coordinate) {
        this.texture = texture;
        state = 0;

        setTouchable(Touchable.enabled);
        setCoordinate(coordinate);

        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                state = 2;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                state = 1;
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (pointer == -1) { // only sets the state if the mouse is not down (pointer is -1) otherwise it would interfere with touchDown
                    state = 1;
                }
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (pointer == -1) {
                    state = 0;
                }
            }
        });
    }

    public void setCoordinate(Coordinate coordinate){
        this.coordinate = coordinate;
        setBounds(
                (float) coordinate.getX() - texture.getWidth() / 2, (float) coordinate.getY() - texture.getHeight() / 6,
                texture.getWidth(), texture.getHeight() / 3);
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.draw(
                texture, (float)coordinate.getX() - texture.getWidth() / 2, (float)coordinate.getY() - texture.getHeight() / 6,
                0, 0, texture.getWidth(), texture.getHeight() / 3,
                1, 1, 0,
                0, (texture.getHeight() / 3) * state, texture.getWidth(), texture.getHeight() / 3,
                false, false);
    }

}
