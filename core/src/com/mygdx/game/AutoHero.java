/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

/**
 *
 * @author Whizzpered
 */
public class AutoHero extends Hero {

    public AutoHero(String desc) {
        super(desc);
    }

    public void setPosition() {
        getStage().count++;
        setX(getStage().getWidth() / 2 - 10 + 500 * getStage().count);
        setY(getStage().getHeight() / 2 - 99);
        setSprite();
    }

    @Override
    public void makeItProfit() {
        x = getX() - getStage().camerax;
        y = getY() + 60;
        super.makeItProfit();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (time <= 0) {
            time = cooldown;
        }
    }
}
