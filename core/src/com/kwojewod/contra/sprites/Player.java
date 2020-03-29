package com.kwojewod.contra.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.kwojewod.contra.Contra;

public class Player extends Sprite {
    public World world;
    public Body b2Body;

    public Player(World world){
        this.world = world;
        definePlayer();
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
    }


}
