package com.kwojewod.contra.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.kwojewod.contra.Contra;
import com.kwojewod.contra.screens.PlayScreen;
import com.sun.javafx.geom.Edge;

public class Player extends Sprite {
    public boolean playerIsDead;
    public boolean onPlatform;
    public boolean shooting;


    public enum State {FALLING, JUMPING, STANDING, RUNNING, LAYING};
    public State currentState;
    public State previousState;
    public World world;
    public Body b2Body;
    private TextureRegion heroStand;
    private Animation<TextureRegion> heroRun;
    private  Animation<TextureRegion> heroJump;
    private TextureRegion heroDown;
    private float stateTimer;
    public  boolean runningRight;
    private int widthOfSprite;
    public boolean isGrounded;
    private Array<Bullet> bullets;
    private PlayScreen screen;

    public Player(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("NES_contra_heroes"));
        this.world = world;
        this.screen = screen;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        //TODO Dodać reszte animacji
        loadHeroRun(frames);
        loadHeroJump(frames);
        heroDown = new TextureRegion(getTexture(), 417, 225, 31, 15);
        heroStand = new TextureRegion(getTexture(),335,207,22,35);
        definePlayer();
        setBounds(0,0, 22/ Contra.PPM,32/ Contra.PPM );
        setRegion(heroStand);
        bullets = new Array<Bullet>();
    }
    private void loadHeroRun( Array<TextureRegion> frames){
        int pos = 0;
        for(int i = 0; i < 6; i++){
            if( i == 0 || i == 3)
                widthOfSprite = 21;
            else if ( i == 5)
                widthOfSprite = 20;
            else if(i== 1 || i == 4)
                widthOfSprite = 17;
            else
                widthOfSprite = 19;
            frames.add(new TextureRegion(getTexture(), 336 + pos, 244, widthOfSprite, 34));
            System.out.println("Wczytywanie tekstury od x: " + (336 + pos) + "\n");
            pos = pos + widthOfSprite + 1;


        }
        heroRun = new Animation<TextureRegion>(0.1f, frames);
        playerIsDead = false;
        frames.clear();
    }
    private  void loadHeroJump (Array<TextureRegion> frames){

        int pos = 0;
        for(int i = 0 ; i < 4; ++i){
            if( i == 0 || i ==2)
                widthOfSprite = 17;
            else
                widthOfSprite = 20;
            frames.add(new TextureRegion(getTexture(), 451 + pos, 221, widthOfSprite, 19));
            pos = pos + widthOfSprite + 1;
        }
        heroJump = new Animation<TextureRegion>(0.05f, frames);
        frames.clear();
    }

    public void update(float dt){
        setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);
        if(b2Body.getPosition().y < 0){
            isDead();
        }
        setRegion(getFrame(dt));
        for(Bullet  bullet : bullets) {
            bullet.update(dt);
            if(bullet.isDestroyed())
                bullets.removeValue(bullet, true);
        }

    }

    public TextureRegion getFrame(float dt){
        currentState = getState();

        TextureRegion region;
        switch(currentState){
            case JUMPING:
                region = heroJump.getKeyFrame(stateTimer, true);
                break;
            case RUNNING:
                region = heroRun.getKeyFrame(stateTimer, true);
                break;
            case LAYING:
                region = heroDown;
                break;
            case FALLING:
            case STANDING:
            default:
                region = heroStand;
                break;
        }
        if((b2Body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        }
        else if((b2Body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight = true;
        }
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }


    public State getState(){
        if(b2Body.getLinearVelocity().y > 0 || (b2Body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
            return State.JUMPING;
        else if(b2Body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if(b2Body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            return State.LAYING;
        else
            return State.STANDING;
    }

    public void definePlayer(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(100 / Contra.PPM, 130 / Contra.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.createBody(bdef);

        CircleShape shape = new CircleShape();
        FixtureDef fdef = new FixtureDef();

        shape.setRadius(10 / Contra.PPM);
        fdef.filter.categoryBits = Contra.PLAYER_BIT;
        fdef.filter.maskBits = Contra.DEFAULT_BIT |
                Contra.GROUND_BIT |
                Contra.DEAD_BIT;

        fdef.shape = shape;
        b2Body.createFixture(fdef).setUserData("player1");
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2/Contra.PPM, 16/Contra.PPM), new Vector2(2/Contra.PPM, 16/Contra.PPM));
        fdef.shape = head;
        fdef.isSensor = true;

        b2Body.createFixture(fdef).setUserData("head");
        EdgeShape feet = new EdgeShape();
        feet.set(new Vector2(-4/Contra.PPM, -10/Contra.PPM), new Vector2(4/Contra.PPM, -10/Contra.PPM));
        fdef.shape = feet;
        fdef.isSensor = true;
        b2Body.createFixture(fdef).setUserData("feet");
    }
    public void fire(){
        bullets.add(new Bullet(screen, b2Body.getPosition().x, b2Body.getPosition().y, runningRight ? true : false, this, null));
    }
    public void draw(Batch batch){
        super.draw(batch);
        for(Bullet bullet : bullets)
            bullet.draw(batch);
    }

    public void isDead(){
        playerIsDead = true;

    }
    public float getStateTimer(){
        return stateTimer;
    }

}
