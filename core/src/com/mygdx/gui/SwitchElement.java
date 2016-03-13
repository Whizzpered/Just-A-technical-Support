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

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;

/**
 *
 * @author fax
 */
public abstract class SwitchElement extends Element{

    public boolean value;
    
    Sprite sprite_on, sprite_off;

    public SwitchElement(boolean value, String sprite, float x, float y) {
        super(null,null, x, y);
        if (sprite != null) {
            this.sprite_on = GUIUtils.GUI_ATLAS.createSprite(sprite + "_on");
            this.sprite_off = GUIUtils.GUI_ATLAS.createSprite(sprite + "_off");
        }
        this.value = value;
    }

    @Override
    public void tap(){
        value = !value;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite = value ? sprite_on : sprite_off;
        sprite.setSize(width, height);
        sprite.setCenterX(x);
        sprite.setCenterY(y);
        sprite.setAlpha(parentAlpha);
        sprite.draw(batch, parentAlpha);
    }
}
