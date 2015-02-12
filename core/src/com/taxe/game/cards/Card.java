package com.taxe.game.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.taxe.game.GameCore;
import com.taxe.game.commands.Commandable;
import com.taxe.game.inputhandling.Clickable;

/**
 * Created by henry on 09/02/15.
 * Specifies methods that all cards must implement.
 */
public abstract class Card extends Actor implements Clickable
{
    public Commandable command;
    public abstract void clicked(GameCore game);

    public abstract Texture getTexture();

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(getTexture(),
                getX() - getOriginX(), getY() - getOriginY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation(),
                0, 0, getTexture().getWidth(), getTexture().getHeight(), false, false);
    }
}
