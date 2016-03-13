package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MyGdxGame extends ApplicationAdapter {

    public static MyGdxGame MYGDXGAME;
    public Stage current;
    public Game game;
    public Market market;
    public EndDay end;
    public static StretchViewport vp = new StretchViewport(420, 700);

    @Override
    public void create() {
        game = new Game(vp);
        MYGDXGAME = this;
        current = game;
    }

    @Override
    public void resize(int width, int height) {
        vp.update(width, height);
    }

    @Override
    public void render() {
        current.act();
        current.draw();
    }
}
