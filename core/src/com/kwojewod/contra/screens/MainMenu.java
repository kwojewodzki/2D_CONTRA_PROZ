package com.kwojewod.contra.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.kwojewod.contra.Contra;

public class MainMenu implements Screen {
    Contra game;
    private Texture active1Player;
    private Texture inactive1Player;
    private Texture active2Player;
    private Texture inactive2Player;
    private Texture activeExit;
    private Texture inactiveExit;
    private Texture inactiveTutorial;
    private Texture title;
    private Texture layout;
    private Texture activeTutorial;
    private Music music;


    public MainMenu(Contra game){
        this.game = game;
        active1Player = new Texture("graphics/Menus_Texture/1_player_active.png");
        inactive1Player = new Texture("graphics/Menus_Texture/1_player_inactive.png");
        activeExit = new Texture("graphics/Menus_Texture/exit_active.png");
        inactiveExit = new Texture("graphics/Menus_Texture/exit_inactive.png");
        active2Player = new Texture("graphics/Menus_Texture/2_players_active.png");
        inactive2Player = new Texture("graphics/Menus_Texture/2_players_inactive.png");
        inactiveTutorial = new Texture("graphics/Menus_Texture/tutorial_inactive.png");
        activeTutorial = new Texture("graphics/Menus_Texture/tutorial_active.png");
        title = new Texture("graphics/Menus_Texture/Name.png");
        layout = new Texture("graphics/Menus_Texture/Layout.png");

       // if(music.isPlaying()) {
            music = Contra.manager.get("audio/music/main_menu.mp3", Music.class);
            music.setVolume((float) 0.1);
            music.setLooping(true);
            music.play();
       // }

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        //Drawing single player button

        game.batch.draw(title,640 - 330, 720 - 230, 600, 230);
        game.batch.draw(layout, 700, 120,580,450);

        if((Gdx.input.getX()>=640-100 && Gdx.input.getX()<=740-100) && (Gdx.input.getY() >= 250 && Gdx.input.getY() <= 300)) {
            game.batch.draw(active1Player, 640-100, 420, 100, 50);
            if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                music.stop();
                game.setScreen(new PlayScreen(game, false));
            }

        }else {
            game.batch.draw(inactive1Player, 640-100, 420, 100, 50);
        }

        //Drawing 2 players button

        if((Gdx.input.getX()>=640-100 && Gdx.input.getX()<=740-100) && (Gdx.input.getY() >= 350 && Gdx.input.getY() <= 400)) {
            game.batch.draw(active2Player, 640 - 100, 320, 100, 50);
            if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                music.stop();
                game.setScreen(new PlayScreen(game, true));
            }
        }else {
            game.batch.draw(inactive2Player, 640 - 100, 320, 100, 50);
        }

        //Drawing tutorial button

        if((Gdx.input.getX()>=640-100 && Gdx.input.getX()<=740-100) && (Gdx.input.getY() >= 450 && Gdx.input.getY() <= 500)) {
            game.batch.draw(activeTutorial, 640-100, 220, 100, 50);
            if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                game.setScreen(new TutorialScreen(game));
            }

        }else {
            game.batch.draw(inactiveTutorial, 640-100, 220, 100, 50);
        }




        //Drawing exit button

        if((Gdx.input.getX()>=640-100 && Gdx.input.getX()<=740-100) && (Gdx.input.getY() >= 550 && Gdx.input.getY() <= 600)) {
            game.batch.draw(activeExit, 640 - 100, 120, 100, 50);
            if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                Gdx.app.exit();
            }

        }else {
            game.batch.draw(inactiveExit, 640 - 100, 120, 100, 50);
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
