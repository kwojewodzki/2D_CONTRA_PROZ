package com.kwojewod.contra;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kwojewod.contra.screens.MainMenu;

/*
@
@	Main game class
@
 */



public class Contra extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HIGHT = 208;
	public static final float PPM = 100;

	public static final short DEFAULT_BIT = 1;
	public static final short PLAYER_BIT = 2;
	public static final short GROUND_BIT = 4;
	public static final short BULLET_BIT = 8;
	public static final short DESTROYED_BIT = 16;
	public static final short DEAD_BIT = 32;
	public static AssetManager manager;
	public SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("audio/music/lvl1.mp3", Music.class);
		manager.load("audio/music/main_menu.mp3", Music.class);
		manager.load("audio/sounds/death.mp3", Music.class);
		manager.load("audio/music/game-over.mp3", Music.class);
		manager.load("audio/sounds/machine-gun.mp3", Music.class);

		manager.finishLoading();
		setScreen(new MainMenu(this));
	}

	@Override
	public void render() {

		super.render();
		manager.update();
	}


	public void dispose() {
		super.dispose();
		batch.dispose();
		manager.dispose();
	}
}
