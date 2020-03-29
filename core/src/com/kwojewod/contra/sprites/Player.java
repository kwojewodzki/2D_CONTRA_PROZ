package com.kwojewod.contra.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.kwojewod.contra.Contra;
import com.kwojewod.contra.screens.PlayScreen;

public class Player extends Sprite {
    public World world;
    public Body b2Body;
    private TextureRegion heroStand;

    public Player(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("Heroes"));
        this.world = world;
        definePlayer();
        heroStand = new TextureRegion(getTexture(),334,125,22,32);
        setBounds(0,0, 22/ Contra.PPM,32/ Contra.PPM );
        setRegion(heroStand);
    }

    public void update(float dt){
        setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);
    }

    public void definePlayer(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / Contra.PPM, 32 / Contra.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(8 / Contra.PPM);

        fdef.shape = shape;
        b2Body.createFixture(fdef);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2/Contra.PPM, 18/Contra.PPM), new Vector2(2/Contra.PPM, 18/Contra.PPM));
        fdef.shape = head;
        fdef.isSensor = true;

        b2Body.createFixture(fdef).setUserData("head");
    }


}
