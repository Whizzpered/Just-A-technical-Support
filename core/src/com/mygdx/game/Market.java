/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import static com.mygdx.game.MyGdxGame.MYGDXGAME;
import com.mygdx.gui.Element;
import com.mygdx.gui.GUILayer;
import com.mygdx.gui.GUIUtils;
import com.mygdx.gui.UpgradeButton;

/**
 *
 * @author Whizzpered
 */
public class Market extends Stage{
    
    TextureRegion background;
    GUILayer gui = new GUILayer();
    float bob = 0;

    public Market() {
        setSprites();
        setGUI();
        setListener();
    }

    public void setSprites() {
        background = GUIUtils.GUI_ATLAS.findRegion("notebook");
    }

    public void setGUI() {
        gui.add(new Element("note1", "Exit", 40, getHeight() - 40) {
            @Override
            public void tap() {
                MYGDXGAME.current = MYGDXGAME.game;
                Gdx.input.setInputProcessor(MYGDXGAME.game);
            }
        });
        gui.add(new UpgradeButton("note1", "improove commits", 320, getHeight() - 120, MYGDXGAME.game.main.upgrades[0]));
    }

    public void addMinion(AutoHero her) {
        MYGDXGAME.game.addHero(her);
        MYGDXGAME.game.limit += 500;
        bob++;
        gui.add(new UpgradeButton("note1", "Buy some brains", 340, getHeight() - 120 - bob * 160, her));
    }

    public void setListener() {
        InputListener tmp = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int we) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int we) {
                gui.tapHandleCrutch_up(event, x, y, pointer, we);
            }
        };
        addListener(tmp);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void act(float delta) {
        gui.act(delta);
    }

    @Override
    public void draw() {
        Batch b = getBatch();
        b.begin();
        b.draw(background, 0, -50);
        gui.draw(b, 1f);
        b.end();
    }
    
}
