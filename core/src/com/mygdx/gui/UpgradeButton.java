/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Game;
import com.mygdx.game.Hero;
import static com.mygdx.game.MyGdxGame.MYGDXGAME;
import com.mygdx.game.Upgrade;
import com.mygdx.game.Upgrade.Type;

/**
 *
 * @author Whizzpered
 */
public class UpgradeButton extends Element {

    public Upgrade target;
    public Hero owner;
    public int lvl = 0;
    public long price = 0;
    String desc = " ";

    public UpgradeButton(String sprite, String message, float x, float y, Upgrade up) {
        super(sprite, message, x, y);
        target = up;
        price = up.startprice;
        desc = up.description;
    }

    public UpgradeButton(String sprite, String message, float x, float y, Hero up) {
        super(sprite, message, x, y);
        owner = up;
        price = owner.startprice;
        desc = up.description;
    }

    public void action() {
        if (target != null) {
            for (Type t : target.types) {
                if (t != null) {
                    switch (t) {
                        case POINTS:
                            target.pointsBoost = lvl * 5 - 9;
                            break;
                        case GOODCOMMITS:
                            target.goodcomms = lvl * 2 / 100;
                            break;
                        case SPEED:
                            target.speedBoost = lvl * 6 / 10;
                            break;
                    }
                }
            }
        } else {
            
            for (Type t : owner.types) {
                if (t != null) {
                    switch (t) {
                        case POINTS:
                            owner.pointsBoost = lvl * 5 - 9;
                            break;
                        case GOODCOMMITS:
                            owner.goodcomms = lvl * 2 / 100;
                            break;
                        case SPEED:
                            owner.speedBoost = lvl * 6 / 100;
                            break;
                    }
                }
            }
        }
    }

    public void pay() {
        lvl++;
        MYGDXGAME.game.moneys -= price;
        price += price / 2;
    }

    @Override
    public void tap() {
        if (MYGDXGAME.game.moneys >= price) {
            pay();
            action();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setColor(MYGDXGAME.game.moneys >= price ? Color.WHITE : Color.GRAY);
        if (sprite != null) {
            sprite.setSize(width, height);
            sprite.setCenterX(x);
            sprite.setCenterY(y);
            sprite.setAlpha(parentAlpha);
            sprite.draw(batch, parentAlpha);
            if (mess != null) {
                MYGDXGAME.game.draw(batch, mess, x - 40, y + getHeight() / 2 - 20, Color.BLACK, Game.fontSizes.STANDART);
            }
        }
        if(desc!=null)
        MYGDXGAME.game.draw(batch,desc,
        x - getWidth() / 2 + 30, y + getHeight() / 2 - 60, Color.BLACK, Game.fontSizes.STANDART);
        MYGDXGAME.game.draw(batch, "$" + price, x - getWidth() / 2 + 30, y + getHeight() / 2 - 100, Color.RED, Game.fontSizes.STANDART);
    }

}
