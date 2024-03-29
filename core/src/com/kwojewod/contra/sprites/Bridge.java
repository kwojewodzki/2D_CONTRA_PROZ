package com.kwojewod.contra.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.kwojewod.contra.Contra;


public class Bridge extends InteractiveTileObject {
    public Bridge(World world, TiledMap map, Rectangle bounds){

        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(Contra.GROUND_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Bridge","Collision");

    }
    public void onFeet() {
        setCategoryFilter(Contra.DESTROYED_BIT);

    }

}
