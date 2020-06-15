package com.kwojewod.contra.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.kwojewod.contra.Contra;
import com.kwojewod.contra.screens.PlayScreen;


public class Player2 extends Sprite {

    public enum State {FALLING, JUMPING, STANDING, RUNNING, LAYING};
    public Player.State currentState;
    public Player.State previousState;
    public World world;
    public Body b2Body;
    private TextureRegion heroStand;
    private Animation<TextureRegion> heroRun;
    private  Animation<TextureRegion> heroJump;
    private TextureRegion heroDown;
    private float stateTimer;
    public  boolean runningRight;
    private int widthOfSprite;
    public boolean playerIsDead;
    private Array<Bullet> bullets;
    private PlayScreen screen;

    public Player2(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("NES_contra_heroes"));
        this.screen = screen;
        this.world = world;
        currentState = Player.State.STANDING;
        previousState = Player.State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        loadHeroRun(frames);
        loadHeroJump(frames);
        heroDown = new TextureRegion(getTexture(), 417, 450, 31, 15);
        heroStand = new TextureRegion(getTexture(),335,431,22,35);
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
            frames.add(new TextureRegion(getTexture(), 336 + pos, 469, widthOfSprite, 34));
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
            frames.add(new TextureRegion(getTexture(), 451 + pos, 446, widthOfSprite, 19));
            pos = pos + widthOfSprite + 1;
        }
        heroJump = new Animation<TextureRegion>(0.05f, frames);
        frames.clear();
    }


    public void update(float dt) {
        setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }


    public TextureRegion getFrame(float dt) {
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


    public Player.State getState() {

        if(b2Body.getLinearVelocity().y > 0 || (b2Body.getLinearVelocity().y < 0 && previousState == Player.State.JUMPING))
            return Player.State.JUMPING;
        else if(b2Body.getLinearVelocity().y < 0)
            return Player.State.FALLING;
        else if(b2Body.getLinearVelocity().x != 0)
            return Player.State.RUNNING;
        else if(Gdx.input.isKeyPressed(Input.Keys.S))
            return Player.State.LAYING;
        else
            return Player.State.STANDING;
    }


    public void definePlayer() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(130 / Contra.PPM, 130 / Contra.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.createBody(bdef);

        CircleShape shape = new CircleShape();
        FixtureDef fdef = new FixtureDef();

        shape.setRadius(10 / Contra.PPM);
        fdef.filter.categoryBits = Contra.PLAYER_BIT;
        fdef.filter.maskBits = Contra.DEFAULT_BIT | Contra.GROUND_BIT;

        fdef.shape = shape;
        b2Body.createFixture(fdef).setUserData("player2");
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2/Contra.PPM, 16/Contra.PPM), new Vector2(2/Contra.PPM, 16/Contra.PPM));
        fdef.shape = head;
        fdef.isSensor = true;
    }


    public void fire(){
            bullets.add(new Bullet(screen, b2Body.getPosition().x, b2Body.getPosition().y, runningRight ? true : false, null, this));
        }


    public void isDead() {
        playerIsDead = true;
    }


    public float getStateTimer() {
       return 0;
    }
}
