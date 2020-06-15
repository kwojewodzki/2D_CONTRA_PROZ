package com.kwojewod.contra.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kwojewod.contra.Contra;


public class GameOverScreen implements Screen {
    Contra game;

    private Music music;
    private Viewport viewport;
    private Stage stage;


    public GameOverScreen(Contra game) {

        music = Contra.manager.get("audio/music/game-over.mp3", Music.class);
        music.setVolume((float) 0.1);
        music.setLooping(true);
        music.play();

        this.game = game;
        viewport = new FitViewport(Contra.V_WIDTH, Contra.V_HIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Contra) game).batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label("Nie zyjesz", font);
        Label playAgainLabel = new Label("Kliknij zeby wyjsc z gry", font);

        table.add(gameOverLabel).expandX();
        table.row();
        table.add(playAgainLabel).expandX().padTop(10f);

        stage.addActor(table);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

        if (Gdx.input.justTouched()) {
           // game.setScreen(new MainMenu(game));
            Gdx.app.exit();
            dispose();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }


    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
