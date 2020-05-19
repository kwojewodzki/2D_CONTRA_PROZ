package com.kwojewod.contra.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.kwojewod.contra.Contra;

public class Ground extends InteractiveTileObject {
    public Ground(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(Contra.GROUND_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Ground","Collision");
        setCategoryFilter(Contra.DESTROYED_BIT);
    }
    public void onFeet(){
        if(whatFilter() == Contra.DESTROYED_BIT) {
            Gdx.app.log("Ground feet", "Collision");
            setCategoryFilter(Contra.GROUND_BIT);
        }
    }
}
