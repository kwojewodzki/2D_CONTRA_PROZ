package com.kwojewod.contra.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kwojewod.contra.Contra;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.kwojewod.contra.Scenes.Hud;

public class PlayScreen implements Screen {
	//Game variable
	private Contra game;
	//Variables for camera
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	//Variable for Hud class
	private Hud hud;
	//Variables for loading and rendering map
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;

	public PlayScreen(Contra game) {
		/*
		Constructor for Contra class
		Initializing game, camera and loading hud
		*/


		this.game = game;
		gamecam = new OrthographicCamera();
		gamePort = new FitViewport(Contra.V_WIDTH, Contra.V_HIGHT,gamecam);
		hud = new Hud(game.batch);

		//Loading map
		maploader = new TmxMapLoader();
		map = maploader.load("mapContra.tmx"); //TODO Update hitboxes on map
		renderer = new OrthogonalTiledMapRenderer(map);
		gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
		
	}
	

	public void handleInput(float dt){


		if(Gdx.input.isTouched())
			gamecam.position.x += 100 *dt;
	}

	public void update(float dt){
		//updating camera position and rendering map that is visible
		handleInput(dt);

		gamecam.update();
		renderer.setView(gamecam);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		update(delta);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		renderer.render();

		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
