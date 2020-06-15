package com.kwojewod.contra.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.kwojewod.contra.Contra;
import com.kwojewod.contra.screens.PlayScreen;


public class Bullet extends Sprite {
    PlayScreen screen;
    World world;
    Array<TextureRegion> frames;
    private TextureRegion bullet;
    float stateTime;
    boolean destroyed;
    boolean setToDestroy;
    boolean fireRight;
    Animation fireAnimation;
    Player player;
    Player2 player2;
    float x,y;

    Body b2body;
    public Bullet(PlayScreen screen, float x, float y, boolean fireRight, Player player, Player2 player2){
        this.player = player;
        this.player2 = player2;
        this.fireRight = fireRight;
        this.screen = screen;
        this.world = screen.getWorld();
        bullet = new TextureRegion(screen.getAtlas().findRegion("NES_contra_heroes"), 444,621,4,5);
        setBounds(x, y, 4 / Contra.PPM, 5 / Contra.PPM);
        defineBullet();



    }

    public void defineBullet(){
        BodyDef bdef = new BodyDef();
        if(fireRight){
            x = getX() + 12 /Contra.PPM;
        }else {
            x = getX() - 12 / Contra.PPM;
        }
        y =  getY() + 8/Contra.PPM;
        bdef.position.set(x,y);
        bdef.type = BodyDef.BodyType.KinematicBody;
        if(!world.isLocked())
            b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(2.5f / Contra.PPM);
        fdef.filter.categoryBits = Contra.BULLET_BIT;
        fdef.filter.maskBits = Contra.GROUND_BIT;

        fdef.shape = shape;
        fdef.restitution = 1;
        fdef.friction = 0;
        b2body.createFixture(fdef).setUserData(this);
        if(player != null) {
            if (player.runningRight)
                b2body.setLinearVelocity(new Vector2(2.5f, 0));
            else
                b2body.setLinearVelocity(new Vector2(-2.5f, 0));
        }else{
            if (player2.runningRight)
                b2body.setLinearVelocity(new Vector2(2.5f, 0));
            else
                b2body.setLinearVelocity(new Vector2(-2.5f, 0));
        }
    }

    public void update(float dt){
        stateTime += dt;
        setRegion(bullet);
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        if((stateTime > 3 || setToDestroy) && !destroyed) {
            world.destroyBody(b2body);
            destroyed = true;
        }
        if(b2body.getLinearVelocity().y > 2f)
            b2body.setLinearVelocity(b2body.getLinearVelocity().x, 2f);
        if((fireRight && b2body.getLinearVelocity().x < 0) || (!fireRight && b2body.getLinearVelocity().x > 0))
            setToDestroy();


    }
    public void render(float dt){
        //batch.draw(bullet, x, y);
    }

    public void setToDestroy(){
        setToDestroy = true;
    }

    public boolean isDestroyed(){
        return destroyed;
    }

}
