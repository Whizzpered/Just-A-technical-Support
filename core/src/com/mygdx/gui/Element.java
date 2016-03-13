/*
 * Copyright (C) 2015 fax
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mygdx.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Game;
import static com.mygdx.game.MyGdxGame.MYGDXGAME;
//import com.mygdx.game.Game;

//import static com.mygdx.game.MyGdxGame.MYGDXGAME;

/**
 *
 * @author fax
 */
public abstract class Element {

    protected float x;
    float life = 2;
    protected float y;
    protected float width;
    protected float height;
    String mess;

    public Sprite sprite;

    public Element(String sprite, String message, float x, float y) {
        if (sprite != null) {
            this.sprite = GUIUtils.GUI_ATLAS.createSprite(sprite);
        }
        if (message != null) {
            mess = message;
        }
        if (sprite != null) {
            setWidth(this.sprite.getWidth());
            setHeight(this.sprite.getHeight());
        }
        setX(x);
        setY(y);

    }

    public abstract void tap();

    public void setSprite() {
    }

    public void act(float delta) {
    }

    public void draw(Batch batch, float parentAlpha) {
        if (sprite != null) {
            sprite.setSize(width, height);
            sprite.setCenterX(x);
            sprite.setCenterY(y);
            sprite.setAlpha(parentAlpha);
            sprite.draw(batch, parentAlpha);
            if (mess != null) {
                MYGDXGAME.game.draw(batch, mess, x - 40, y + getHeight()/2 - 70, Color.BLACK, Game.fontSizes.STANDART);
            }
        }
    }

    /**
     * @return the x
     */
    public float getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * @return the width
     */
    public float getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public float getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(float height) {
        this.height = height;
    }
}
