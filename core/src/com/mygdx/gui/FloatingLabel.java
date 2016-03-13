/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gui;

import com.badlogic.gdx.graphics.Color;

/**
 *
 * @author Whizzpered
 */
public class FloatingLabel extends Label {

    public FloatingLabel(String ru, float x, float y, Color color) {
        super(ru, x, y, color);
    }

    public void act(float delta) {
        setY(getY() + 20 * delta);
        if (life > 0) {
            life -= delta;
        }
    }

}
