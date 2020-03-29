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
