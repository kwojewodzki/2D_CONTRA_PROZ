package com.kwojewod.contra.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.kwojewod.contra.Contra;
import com.kwojewod.contra.sprites.Bridge;
import com.kwojewod.contra.sprites.DeathPit;
import com.kwojewod.contra.sprites.Ground;
import com.kwojewod.contra.sprites.Water;

public class B2WorldCreator {
    public B2WorldCreator(World world, TiledMap map) {

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //Create ground bodies/fixtures
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();


            new Ground(world, map, rect);
        }

        //Create water bodies/fixtures
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Water(world, map, rect);
        }

        //Create bounds bodies/fixtures
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new DeathPit(world, map, rect);

        }
        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / Contra.PPM, (rect.getY() + rect.getHeight() / 2) / Contra.PPM);

            body = world.createBody(bdef);


            shape.setAsBox(rect.getWidth() / 2 / Contra.PPM, rect.getHeight() / 2 / Contra.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        //Create Bridges bodies/fixtures
        for (MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Bridge(world, map, rect);
        }
    }
}
