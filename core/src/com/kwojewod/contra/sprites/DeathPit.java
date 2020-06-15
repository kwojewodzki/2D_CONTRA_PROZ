package com.kwojewod.contra.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.kwojewod.contra.Contra;

public class DeathPit extends InteractiveTileObject {
    public DeathPit(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        fixture.setUserData("Death");
        setCategoryFilter(Contra.DEAD_BIT);
    }

    @Override
    public void onHeadHit() {
    }

    @Override
    public void onFeet() {

    }
}
