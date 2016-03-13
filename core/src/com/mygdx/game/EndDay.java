/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import static com.mygdx.game.MyGdxGame.MYGDXGAME;
import com.mygdx.gui.Element;
import com.mygdx.gui.GUILayer;
import com.mygdx.gui.GUIUtils;
import com.mygdx.gui.Label;

/**
 *
 * @author Whizzpered
 */
public class EndDay extends Stage {

    public Game game;
    GUILayer gui = new GUILayer();
    TextureRegion tex;
    long lastcomms = 0, deltamoneys = 0, deltasouls = 0;
    long deltacoms;

    public EndDay(Game parent) {
        game = parent;
        initListener();
        initTextures();
    }

    public void initListener() {

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
        gui.add(new Element("note1", "Continue", 140, 120) {
            @Override
            public void tap() {
                MYGDXGAME.current = MYGDXGAME.game;
                lastcomms = MYGDXGAME.game.coms;
                Gdx.input.setInputProcessor(MYGDXGAME.game);
            }
        });
        gui.add(new Label("One more day passed", 90, getHeight() - 55, Color.BLACK));
        gui.add(new Label("You gained " + deltacoms + " commits", 90, getHeight() - 105, Color.BLACK) {
            @Override
            public void act(float delta) {
                ru = "You gained   " + MYGDXGAME.game.number(deltacoms) + " commits";
            }
        });
        gui.add(new Label("What equals " + deltamoneys + " dollars", 90, getHeight() - 155, Color.BLACK) {
            @Override
            public void act(float delta) {
                ru = "What equals   " + MYGDXGAME.game.number(deltamoneys) + " dollars";
            }
        });
    }

    public void initTextures() {
        tex = GUIUtils.GUI_ATLAS.findRegion("sheet");
    }

    public void update() {
        deltacoms = MYGDXGAME.game.coms - lastcomms;
        deltamoneys = deltacoms * MYGDXGAME.game.main.company;
        MYGDXGAME.game.moneys += deltamoneys;
    }

    @Override
    public void act(float delta) {
        gui.act(delta);
    }

    @Override
    public void draw() {
        Batch b = getBatch();
        game.draw();
        b.begin();
        b.draw(tex, 0, -50);
        gui.draw(b, 1f);
        b.end();
    }
}
