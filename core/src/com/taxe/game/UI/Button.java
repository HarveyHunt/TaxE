package com.taxe.game.UI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.taxe.game.GameCore;
import com.taxe.game.InputHandling.Clickable;
import com.taxe.game.Util.Coordinate;

/**
 * Created by Owen on 09/01/2015.
 */
public class Button extends Actor implements Clickable {

    private Texture texture;
    private int state; // 0 idle; 1 hover; 2 pressed

    public Button(Texture texture, Coordinate coordinate) {
        this.texture = texture;
        state = 0;
        setTouchable(Touchable.enabled);
        setOrigin(texture.getWidth() / 2, texture.getHeight() / 6);
        setCoordinate(coordinate);

        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                state = ButtonState.PRESSED;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                state = ButtonState.HOVERED;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (pointer == -1) { // only sets the state if the mouse is not down (pointer is -1) otherwise it would interfere with touchDown
                    state = ButtonState.HOVERED;
                }
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (pointer == -1) {
                    state = ButtonState.IDLE;
                }
            }
        });
    }

    public int getState() {
        return state;
    }

    public void clicked(GameCore gameCore) {

    }

    public void clicked() { // This is used in the menu class when we don't want to pass an instance of GameCore

    }

    public void setCoordinate(Coordinate coordinate) {
        setPosition(coordinate.getX(), coordinate.getY());
        setSize(texture.getWidth(), texture.getHeight() / 3);
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        if (touchable && this.getTouchable() != Touchable.enabled) return null;
        return x >= -getOriginX() && x < getWidth() - getOriginX() && y >= -getOriginY() && y < getHeight() - getOriginY() ? this : null;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isVisible()) {
            batch.draw(
                    texture, (int) (getX() - getOriginX()), (int) (getY() - getOriginY()),
                    getOriginX(), getOriginY(), getWidth(), getHeight(),
                    1, 1, 0,
                    0, (int) (getHeight() * state), (int) getWidth(), (int) getHeight(),
                    false, false);
        }
    }

}
