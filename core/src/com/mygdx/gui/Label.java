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
 * @author fax
 */
public class Label extends Element {

    public Color color;
    public String ru;

    static Sprite frame[];
    static Sprite frame2[];

    public Label(String ru, float x, float y, Color color) {
        super(null, ru, x, y);
        this.ru = ru;
        this.color = color;
    }

    public void set(String ru) {
        this.ru = ru;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        MYGDXGAME.game.draw(batch, ru, getX(), getY() - 20, new Color(color.r, color.g, color.b, parentAlpha),Game.fontSizes.BIG);
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

    @Override
    public void tap() {

    }
}
