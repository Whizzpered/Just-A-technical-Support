/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import static com.mygdx.game.Game.upgrades;

/**
 *
 * @author Whizzpered
 */
public class Upgrade {

    public static enum Type {

        POINTS, GOODCOMMITS, SPEED, NONE
    }

    public float pointsBoost = 0, speedBoost = 0, goodcomms = 0;
    public Array<Type> types = new Array<Type>();
    public int lvl;
    public long startprice = 20;
    public String description;
    public Sprite sprite;
    public String name;

    public Upgrade(String name, int lvl) {
        this.lvl = lvl;
        this.name = name;
    }

    public void setDescription() {
        StringBuilder b = new StringBuilder();
        for (Type de : types) {
            b.append(de == Type.NONE ? "" : de.name().toLowerCase() + "\n");
        }
        description = b.toString();
    }

    public void setSprite() {
        if (lvl < 20) {
            sprite = upgrades.createSprite(name, (lvl / 2));
        } else {
            sprite = upgrades.createSprite(name, 10);
        }
        sprite.setScale(2.625f);
    }

    public void draw(Batch batch, float parentX, float parentY) {
        sprite.setCenter(parentX, parentY);
        sprite.draw(batch);
    }

}
