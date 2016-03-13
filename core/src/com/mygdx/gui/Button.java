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
public abstract class Button extends Element {

    public Color color;
    public String ru;

    static Sprite frame[];
    static Sprite frame2[];

    public Button(String ru, float x, float y, float width, float height, Color color) {
        super(null,null, x, y);
        setWidth(width);
        setHeight(height);
        this.ru = ru;
        this.color = color;
    }

    @Override
    public abstract void tap();

    @Override
    public void setSprite() {
    }

    @Override
    public void act(float delta) {
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (frame == null) {
            frame = new Sprite[]{
                GUIUtils.GUI_ATLAS.createSprite("frame_left"),
                GUIUtils.GUI_ATLAS.createSprite("frame"),
                GUIUtils.GUI_ATLAS.createSprite("frame_right")
            };
            frame2 = new Sprite[]{
                GUIUtils.GUI_ATLAS.createSprite("frame2_left"),
                GUIUtils.GUI_ATLAS.createSprite("frame2"),
                GUIUtils.GUI_ATLAS.createSprite("frame2_right")
            };
        }
        frame[0].setSize(31f * height / 125f, height);
        frame[0].setCenter(getX() - width / 2 - frame[0].getWidth() / 2, getY());
        frame[1].setSize(width, height);
        frame[1].setCenter(getX(), getY());
        frame[2].setSize(31f * height / 125f, height);
        frame[2].setCenter(getX() + width / 2 + frame[2].getWidth() / 2, getY());
        frame2[0].setSize(31f * height / 125f, height);
        frame2[0].setCenter(getX() - width / 2 - frame[0].getWidth() / 2, getY());
        frame2[1].setCenter(getX(), getY());
        frame2[1].setSize(0, 0);
        frame2[2].setSize(31f * height / 125f, height);
        frame2[2].setCenter(getX() + width / 2 + frame[2].getWidth() / 2, getY());
        for (int i = 0; i < 3; i++) {
            frame[i].setColor(color);
            frame[i].setAlpha(parentAlpha);
            frame[i].draw(batch);
            frame2[i].setAlpha(parentAlpha);
            frame2[i].draw(batch);
        }
        MYGDXGAME.game.draw(batch, ru, getX() - getWidth()/2 + 20, getY() + 20, new Color(color.r, color.g, color.b, parentAlpha), Game.fontSizes.STANDART);
    }

    /**
     * @return the x
     */
    @Override
    public float getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    @Override
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    @Override
    public float getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    @Override
    public void setY(float y) {
        this.y = y;
    }

    /**
     * @return the width
     */
    @Override
    public float getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    @Override
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    @Override
    public float getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    @Override
    public void setHeight(float height) {
        this.height = height;
    }
}
