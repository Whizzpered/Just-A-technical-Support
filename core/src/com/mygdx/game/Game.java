/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import static com.mygdx.game.MyGdxGame.MYGDXGAME;
import com.mygdx.gui.Element;
import com.mygdx.gui.GUILayer;
import com.mygdx.gui.GUIUtils;
import com.mygdx.gui.Label;

/**
 *
 * @author Whizzpered
 */
public class Game extends Stage {

    public enum fontSizes {

        BIG, STANDART
    }

    public static final int DAY_START = 8, DAY_END = 18;
    public Hero main;
    public long coms = 0, moneys = 200, souls = 0;
    public int count = 0, camerax = 0, limit = 0;
    public float tempo = 0;
    public int mins = 50, hours = 16, days = 1;
    public boolean next = true;
    Array<AutoHero> autoheroes = new Array<AutoHero>();
    GUILayer gui = new GUILayer();
    AssetManager loader = new AssetManager();
    static TextureAtlas atlas, upgrades;
    static Sprite background, desk;
    Array<TextureRegion> workAnimation = new Array<TextureRegion>();
    public static BitmapFont font, bigfont;

    public Game(Viewport vp) {
        super(vp);
        loadAssets();
        initHero();
        initListener();
        setGUI();
        
    }

    public void initListener() {
        InputListener tmp = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int we) {
                float dist = (float) Math.sqrt(Math.pow(main.getX() - x, 2)
                        + Math.pow(main.getY() - y, 2));
                if (dist < (getViewport().getScreenWidth() / 4 + getViewport().getScreenHeight() / 4) / 2) {
                    main.tap(x, y);
                }
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

    public void draw(Batch b, String mess, float x, float y, Color color, fontSizes size) {
        if (size == Game.fontSizes.STANDART) {
            font.setColor(color);
            font.getData().setScale(0.8f);
            font.draw(b, mess, x, y);
        }
        if (size == Game.fontSizes.BIG) {
            font.getData().setScale(1f);
            font.setColor(color);
            font.draw(b, mess, x, y);
        }
    }

    public void initHero() {
        main = new Hero("Buy some brains to better calls");
        addActor(main);
        main.setSprite();
        main.setPosition(getWidth() / 2, getHeight() / 2);
    }

    public void loadAssets() {
        loader.load("sprites.pack", TextureAtlas.class);
        loader.load("upgrades.pack", TextureAtlas.class);
        loader.load("gui.pack", TextureAtlas.class);
        loader.finishLoading();
        atlas = loader.get("sprites.pack");
        upgrades = loader.get("upgrades.pack");
        background = atlas.createSprite("background");
        GUIUtils.GUI_ATLAS = loader.get("gui.pack");
        background.setScale(3f);
        background.setOrigin(0, 0);
        background.setPosition(0, 80);
        desk = atlas.createSprite("desk");
        desk.setPosition(0, -10);
        for (int i = 0; i < 3; i++) {
            Sprite s = atlas.createSprite("hero", i);
            s.setScale(3f);
            workAnimation.add(s);
        }
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
    }

    public void addHero(AutoHero h) {
        addActor(h);
        h.setSprite();
        autoheroes.add(h);
    }

    public void setGUI() {
        gui.add(new Element("note1", "to Do", 100, 80) {
            @Override
            public void tap() {
                if (MYGDXGAME.market == null) {
                    MYGDXGAME.market = new Market();
                }
                MYGDXGAME.current = MYGDXGAME.market;
                Gdx.input.setInputProcessor(MYGDXGAME.market);
            }
        });
        gui.add(new Element("note1", "next", getWidth() - 90, getHeight() - 90) {
            @Override
            public void tap() {
                if (camerax < limit) {
                    next = true;
                    camerax += 10;
                }
            }
        });
        gui.add(new Element("note1", "previous", 90, getHeight() - 90) {
            @Override
            public void tap() {
                if (camerax > 0) {
                    next = false;
                    camerax -= 10;
                }
            }
        });
        gui.add(new Label("" + coms, getWidth() / 5.1f - (getWidth() / 20 * (moneys + "").length() - 1), getHeight() - getHeight() / 5, Color.RED) {
            @Override
            public void act(float delta) {
                ru = number(coms) + " commits, $" + moneys + ", + " + souls + " souls";
            }
        });
        gui.add(new Label("" + mins + " mins", getWidth() / 4.3f, getHeight() - getHeight() / 6f, Color.RED) {
            @Override
            public void act(float delta) {
                ru = hours + ":" + (mins < 10 ? " 0" : " ") + mins + " of your " + days + "'s ХУЕВ";
            }
        });

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        gui.act(delta);
        timing(delta);
        if (camerax % 500 != 0) {
            if (next) {
                camerax += 10;
            } else {
                camerax -= 10;
            }
        }
    }

    public void timing(float delta) {
        tempo += delta * 3;
        if (tempo >= 1) {
            tempo -= 1;
            mins++;
        }
        if (mins == 60) {
            mins = 0;
            hours++;
        }
        if (hours == DAY_END) {
            hours = DAY_START;
            days++;
            newDay();
        }
    }

    public void newDay() {
        if (MYGDXGAME.end == null) {
            MYGDXGAME.end = new EndDay(this);
        } else {
            Gdx.input.setInputProcessor(MYGDXGAME.end);
        }
        MYGDXGAME.end.update();
        MYGDXGAME.current = MYGDXGAME.end;
    }

    public String number(long number) {
        long nmb = Math.abs(number);
        if (nmb >= 0 && nmb <= 1000) {
            return String.valueOf((int) number);
        } else if (nmb >= 1000 && nmb <= 1000000) {
            return String.valueOf(((int) (number)) / 1000) + 'k';
        } else if (nmb >= 1000000 && nmb <= 1000000000) {
            return String.valueOf(((int) (number)) / 1000000) + 'm';
        } else if (nmb >= 1000000000) {
            return String.valueOf(((int) (number)) / 1000000000) + 'b';
        }

        return String.valueOf(number);
    }

    @Override
    public void draw() {
        Batch b = getBatch();
        b.begin();
        desk.draw(b);
        background.draw(b);
        b.end();
        super.draw();
        b.begin();
        gui.draw(b, 1f);
        b.end();
    }

}
