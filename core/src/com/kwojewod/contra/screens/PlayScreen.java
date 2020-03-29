package com.kwojewod.contra.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kwojewod.contra.Contra;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.kwojewod.contra.Scenes.Hud;
import com.kwojewod.contra.sprites.Player;

public class PlayScreen implements Screen {
	//Game variable
	private Contra game;
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
		/*
		Constructor for Contra class
		Initializing game, camera and loading hud
		*/


		this.game = game;
		gamecam = new OrthographicCamera();
		gamePort = new FitViewport(Contra.V_WIDTH / Contra.PPM, Contra.V_HIGHT / Contra.PPM,gamecam );
		hud = new Hud(game.batch);

		//Loading and rendering map
		maploader = new TmxMapLoader();
		map = maploader.load("mapContra.tmx"); //TODO Update hitboxes on map
		renderer = new OrthogonalTiledMapRenderer(map, 1 /Contra.PPM);
		gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

		//Initializing world physics
		world = new World(new Vector2(0, -10), true);
		b2dr = new Box2DDebugRenderer();

		//Creating player
		player = new Player(world);
		//TODO move this part of constructor
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;

		//Create ground bodies/fixtures
		//TODO make jumping from underneath on rocks
		for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) object).getRectangle();

			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth() / 2) / Contra.PPM, (rect.getY() + rect.getHeight() / 2) / Contra.PPM);

			body = world.createBody(bdef);

			shape.setAsBox((rect.getWidth() / 2) / Contra.PPM, (rect.getHeight() / 2) / Contra.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
		}
		//Create Bridges bodies/fixtures
		for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle(); //TODO make brides colapse

			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth() / 2) / Contra.PPM, (rect.getY() + rect.getHeight() / 2) / Contra.PPM);

			body = world.createBody(bdef);


			shape.setAsBox(rect.getWidth() / 2 / Contra.PPM, rect.getHeight() / 2 / Contra.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
		}
		for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle(); //TODO make brides colapse

			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth() / 2) / Contra.PPM, (rect.getY() + rect.getHeight() / 2) / Contra.PPM);

			body = world.createBody(bdef);


			shape.setAsBox(rect.getWidth() / 2 / Contra.PPM, rect.getHeight() / 2 / Contra.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
		}
		for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle(); //TODO make brides colapse

			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth() / 2) / Contra.PPM, (rect.getY() + rect.getHeight() / 2) / Contra.PPM);

			body = world.createBody(bdef);


			shape.setAsBox(rect.getWidth() / 2 / Contra.PPM, rect.getHeight() / 2 / Contra.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
		}
	}
	

	public void handleInput(float dt){
		//Checking if key is being pressed
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			player.b2Body.applyLinearImpulse(new Vector2(0, 4f), player.b2Body.getWorldCenter(), true);
		}
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2Body.getLinearVelocity().x <= 2)
			player.b2Body.applyLinearImpulse(new Vector2(0.1f,0),player.b2Body.getWorldCenter(), true);

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2Body.getLinearVelocity().x >= -2)
			player.b2Body.applyLinearImpulse(new Vector2(-0.1f,0),player.b2Body.getWorldCenter(), true);




	}

	public void update(float dt){
		//updating camera position and rendering map that is visible
		handleInput(dt);

		world.step(1/60f, 6, 2);

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

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
