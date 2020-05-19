package com.kwojewod.contra.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.kwojewod.contra.Contra;


public abstract class InteractiveTileObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected short category;

    protected Fixture fixture;

    public InteractiveTileObject(World world, TiledMap map, Rectangle bounds){
        this.world = world;
        this.map = map;
        this.bounds = bounds;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / Contra.PPM, (bounds.getY() + bounds.getHeight() / 2) / Contra.PPM);

        body = world.createBody(bdef);


        shape.setAsBox(bounds.getWidth() / 2 / Contra.PPM, bounds.getHeight() / 2 / Contra.PPM);
        fdef.shape = shape;
        body.createFixture(fdef).setUserData("Ground");
        fixture = body.createFixture(fdef);

    }

    public abstract void onHeadHit();
    public abstract void onFeet();
    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
        category = filterBit;
    }
    public short whatFilter(){
        return category;
    }
}
