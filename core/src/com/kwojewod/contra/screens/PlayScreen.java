package com.kwojewod.contra.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kwojewod.contra.Contra;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.kwojewod.contra.Scenes.Hud;
import com.kwojewod.contra.sprites.Bullet;
import com.kwojewod.contra.sprites.Player;
import com.kwojewod.contra.tools.B2WorldCreator;
import com.kwojewod.contra.tools.WorldContactListener;

import java.util.ArrayList;

public class PlayScreen implements Screen {
	 ArrayList<Bullet> bullets;
	//Game variable
	private Contra game;
	private TextureAtlas atlas;

	//player class object
	private Player player;

	//Variables for camera
	private OrthographicCamera gamecam;
	private Viewport gamePort;

	//Variable for Hud class
	private Hud hud;

	//Variables for loading and rendering map
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;

	//Box2d variables
	private World world;
	private Box2DDebugRenderer b2dr;

	public PlayScreen(Contra game) {
		bullets = new ArrayList<Bullet>();
		/*
		Constructor for Contra class
		Initializing game, camera and loading hud
		*/
		atlas = new TextureAtlas("Hero_and_Enemies.pack");

		this.game = game;
		gamecam = new OrthographicCamera();
		gamePort = new FitViewport(Contra.V_WIDTH / Contra.PPM, Contra.V_HIGHT / Contra.PPM,gamecam );
		hud = new Hud(game.batch);

		//Loading and rendering map
		maploader = new TmxMapLoader();
		map = maploader.load("lvl1Contra.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 /Contra.PPM);
		gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

		//Initializing world physics
		world = new World(new Vector2(0, -10), true);
		b2dr = new Box2DDebugRenderer();

		new B2WorldCreator(world, map);

		//Creating player
		player = new Player(world, this);

		world.setContactListener(new WorldContactListener());

	}

	public TextureAtlas getAtlas(){
		return atlas;
	}

	//TODO make handleInput usable
	public void handleInput(float dt){
		//Checking if key is being pressed
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) ) {
			if(player.b2Body.getLinearVelocity().y == 0)
			player.b2Body.applyLinearImpulse(new Vector2(0, 4), player.b2Body.getWorldCenter(), true);

		}
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2Body.getLinearVelocity().x <= 2)
			player.b2Body.applyLinearImpulse(new Vector2(0.1f,0),player.b2Body.getWorldCenter(), true);

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2Body.getLinearVelocity().x >= -2)
			player.b2Body.applyLinearImpulse(new Vector2(-0.1f,0),player.b2Body.getWorldCenter(), true);
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			if(player.b2Body.getLinearVelocity().y == 0)
			player.b2Body.setLinearVelocity(new Vector2(0,0));
			//player.b2Body.applyLinearImpulse(new Vector2(0, -0.001f), player.b2Body.getWorldCenter(), true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.X)){
			//Gdx.app.exit();
		}


	}


	public void update(float dt){
		//updating camera position and rendering map that is visible
		handleInput(dt);

		world.step(1/60f, 6, 2);

		player.update(dt);

		gamecam.position.x = player.b2Body.getPosition().x;

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
		//Clearing screen

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//rendering game
		renderer.render();

		//render box2dDebugLines
		b2dr.render(world, gamecam.combined);

		game.batch.setProjectionMatrix(gamecam.combined);
		game.batch.begin();
		player.draw(game.batch);
		game.batch.end();

		//Drawing on screen
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		//Resizing textures
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
	public World getWorld(){
		return world;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		map.dispose();
		renderer.dispose();
		world.dispose();
		b2dr.dispose();
	}

}
