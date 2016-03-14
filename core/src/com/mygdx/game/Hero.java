/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.mygdx.gui.FloatingLabel;

/**
 *
 * @author Whizzpered
 */
public class Hero extends Actor {

    public float pointsBoost = 0, speedBoost = 0, goodcomms = 0;
    public Sprite sprite;
    public Animation working;
    public Array<Upgrade.Type> types = new Array<Upgrade.Type>();
    public long coms = 1, startprice = 20, company = 2;
    public String description;
    Upgrade[] upgrades = new Upgrade[6];
    public float time, cooldown = 2f, x, y;

    @Override
    public Game getStage() {
        return (Game) super.getStage();
    }

    public Hero(String desc) {
        this.description = desc;
        types.add(Upgrade.Type.POINTS);
        types.add(Upgrade.Type.SPEED);
        upgrades[0] = new Upgrade("table", 0);
        upgrades[0].types.add(Upgrade.Type.SPEED);
        upgrades[0].types.add(Upgrade.Type.POINTS);
        upgrades[1] = new Upgrade("monitor", 0);
        upgrades[2] = new Upgrade("comp", 0);
        upgrades[3] = new Upgrade("chair", 0);
    }

    public Upgrade get(String name) {
        if (name == null) {
            return null;
        } else {
            for (Upgrade up : upgrades) {
                if (up != null && up.name.equals(name)) {
                    return up;
                }
            }
            return null;
        }
    }

    public void setSprite() {
        sprite = getStage().atlas.createSprite("hero", 0);
        sprite.setScale(3f);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
        setWidth(sprite.getWidth());
        setHeight(sprite.getHeight());
        for (Upgrade up : upgrades) {
            if (up != null) {
                up.setSprite();
            }
        }
        working = new Animation(0.1f, getStage().workAnimation, Animation.PlayMode.LOOP);
    }

    @Override
    public void act(float delta) {
        if (time > 0) {
            time -= delta;
            if (time <= 0) {
                makeItProfit();
            }
        } else {
            time = 0;
        }
    }

    public void makeItProfit() {
        long profit = coms;
        profit += pointsBoost;
        for (Upgrade up : upgrades) {
            if (up != null) {
                profit += up.pointsBoost;
            }
        }
        getStage().coms += profit;
        Gdx.app.log("App", " herase");
        getStage().gui.add(new FloatingLabel("+" + getStage().number(profit), x, y, Color.GREEN));
    }

    public void tap(float x, float y) {
        if (time <= 0) {
            this.x = x;
            this.y = y;
            float cooldown = this.cooldown;
            cooldown -= speedBoost;
            for (Upgrade up : upgrades) {
                if (up != null) {
                    cooldown -= up.speedBoost;
                }
            }
            time = Math.max(0.2f, cooldown);
        }
    }

    @Override
    public void draw(Batch batch, float parentalpha) {
        get("chair").draw(batch, getX(), getY());
        if (time > 0) {
            batch.draw(working.getKeyFrame(time), getX() - getWidth() / 2 - getStage().camerax, getY() - getHeight() / 2);
        } else {
            sprite.setCenter(getX() - getStage().camerax, getY());
            sprite.draw(batch);
        }
        for (Upgrade up : upgrades) {
            if (up != null && !up.name.equals("chair")) {
                up.draw(batch, getX(), getY());
            }
        }
    }

}
