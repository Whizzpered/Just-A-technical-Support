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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import java.util.ArrayList;

/**
 *
 * @author fax
 */
public class GUILayer extends ArrayList<Element> {

    public void act(float delta) {
        for (int i = 0; i < size(); i++) {
            Element e = get(i);
            if (e.life <= 0) {
                remove(e);
            } else {
                e.act(delta);
            }
        }
    }

    public void draw(Batch batch, float parentAlpha) {
        for (int i = 0; i < size(); i++) {
            Element e = get(i);
            e.draw(batch, parentAlpha);
        }
    }

    public boolean tapHandleCrutch_up(InputEvent event, float x, float y, int pointer, int we) {
        for (int i = size() - 1; i >= 0; i--) {
            Element elem = get(i);
            if (elem != null) {
                if (x >= elem.getX() - elem.getWidth() / 2
                        && y >= elem.getY() - elem.getHeight() / 2
                        && x <= elem.getX() + elem.getWidth() / 2
                        && y <= elem.getY() + elem.getHeight() / 2) {
                    elem.tap();
                    return true;
                }
            }
        }
        return false;
    }
}
