package com.kwojewod.contra;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kwojewod.contra.screens.PlayScreen;

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

	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
}
