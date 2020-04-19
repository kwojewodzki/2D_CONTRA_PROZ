package com.kwojewod.contra.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.kwojewod.contra.Contra;
import com.kwojewod.contra.screens.PlayScreen;

public class Player extends Sprite {
    public enum State {FALLING, JUMPING, STANDING, RUNNING};
    public State currentState;
    public State previousState;
    public World world;
    public Body b2Body;
    private TextureRegion heroStand;
    private Animation<TextureRegion> heroRun;
    private  Animation<TextureRegion> heroJump;
    private Animation<TextureRegion> heroStandInWater;
    private float stateTimer;
    private  boolean runningRight;
    private int widthOfSprite;

    public Player(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("NES_contra_heroes"));
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        //TODO Dodać reszte animacji
        loadHeroRun(frames);
        loadHeroJump(frames);


        heroStand = new TextureRegion(getTexture(),335,207,22,35);
        definePlayer();
        setBounds(0,0, 22/ Contra.PPM,32/ Contra.PPM );
        setRegion(heroStand);
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
        setRegion(getFrame(dt));
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
        else
            return State.STANDING;
    }

    public void definePlayer(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / Contra.PPM, 32 / Contra.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / Contra.PPM);

        fdef.shape = shape;
        b2Body.createFixture(fdef);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2/Contra.PPM, 18/Contra.PPM), new Vector2(2/Contra.PPM, 18/Contra.PPM));
        fdef.shape = head;
        fdef.isSensor = true;

        b2Body.createFixture(fdef).setUserData("head");
    }


}
