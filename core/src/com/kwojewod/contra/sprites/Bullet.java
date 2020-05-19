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
   /* PlayScreen screen;
    World world;
    Array<TextureRegion> frames;
    float stateTime;
    boolean destroyed;
    boolean setToDestroy;
    boolean fireRight;
    Animation fireAnimation;

    Body b2body;
    public Bullet(PlayScreen screen, float x, float y, boolean fireRight){
        this.fireRight = fireRight;
        this.screen = screen;
        this.world = screen.getWorld();
        frames = new Array<TextureRegion>();
           frames.add(new TextureRegion(screen.getAtlas().findRegion("bullet"), 0,0,4,4));
          fireAnimation = new Animation(0.2f, frames);
        setRegion((TextureRegion) fireAnimation.getKeyFrame(0));
        setBounds(x, y, 6 / Contra.PPM, 6 / Contra.PPM);
        defineBullet();
    }

    public void defineBullet(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(fireRight ? getX() + 12 /Contra.PPM : getX() - 12 /Contra.PPM, getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        if(!world.isLocked())
            b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(3 / Contra.PPM);
        fdef.filter.categoryBits = Contra.BULLET_BIT;
        fdef.filter.maskBits = Contra.GROUND_BIT;

        fdef.shape = shape;
        fdef.restitution = 1;
        fdef.friction = 0;
        b2body.createFixture(fdef).setUserData(this);
        b2body.setLinearVelocity(new Vector2(fireRight ? 2 : -2, 2.5f));
    }

    public void update(float dt){
        stateTime += dt;
        setRegion((TextureRegion) fireAnimation.getKeyFrame(stateTime, true));
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

    public void setToDestroy(){
        setToDestroy = true;
    }

    public boolean isDestroyed(){
        return destroyed;
    }

 */
}
