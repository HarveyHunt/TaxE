package com.taxe.game.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.taxe.game.GameCore;
import com.taxe.game.inputhandling.Clickable;

/**
 * Buttons.
 */
public class Button extends Actor implements Clickable {

    private Texture texture;
    private int state;

    /**
     * Creates an instance of a Button
     *
     * @param texture the button's texture
     */
    public Button(Texture texture) {
        this.texture = texture;
        state = GuiStates.BUTTTON_IDLE;
        setTouchable(Touchable.enabled);
        setOrigin(texture.getWidth() / 2, texture.getHeight() / 6);
        setSize(texture.getWidth(), texture.getHeight() / 3);

        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                state = GuiStates.BUTTON_PRESSED;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                state = GuiStates.BUTTON_HOVERED;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                // only sets the state if the mouse is not down (pointer is -1) otherwise it would interfere with touchDown
                if (pointer == -1) {
                    state = GuiStates.BUTTON_HOVERED;
                }
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (pointer == -1) {
                    state = GuiStates.BUTTTON_IDLE;
                }
            }
        });
    }

    /**
     * returns the button's state
     *
     * @return button's state
     */
    public int getState() {
        return state;
    }

    public void clicked(GameCore gameCore) {

    }

    /**
     * Same as clicked(GameCore) except it can be used in the menu because it doesn't require a GameCore instance
     */
    public void clicked() {

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
                    texture, getX() - getOriginX(), getY() - getOriginY(),
                    getOriginX(), getOriginY(), getWidth(), getHeight(),
                    1, 1, 0,
                    0, (int) (getHeight() * state), (int) getWidth(), (int) getHeight(),
                    false, false);
        }
    }

}
