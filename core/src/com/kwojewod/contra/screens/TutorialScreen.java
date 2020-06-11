package com.kwojewod.contra.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.kwojewod.contra.Contra;

public class TutorialScreen implements Screen {
    Contra game;
    private Texture activeBack;
    private Texture inactiveBack;

    public TutorialScreen(Contra game){
    this.game = game;
    activeBack = new Texture("graphics/Menus_Texture/tutorial_screen_active.png");
    inactiveBack = new Texture("graphics/Menus_Texture/tutorial_screen_inactive.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        if ((Gdx.input.getX() >= 525 && Gdx.input.getX() <= 595) && (Gdx.input.getY() >= 510 && Gdx.input.getY() <= 545)) {
            game.batch.draw(activeBack, 0, 0);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                game.setScreen(new MainMenu(game));
            }
        }else {
                game.batch.draw(inactiveBack, 0, 0);
            }

            game.batch.end();
        }


    @Override
    public void resize(int width, int height) {

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
