package com.kwojewod.contra.tools;

import com.badlogic.gdx.physics.box2d.*;
import com.kwojewod.contra.Contra;
import com.kwojewod.contra.sprites.InteractiveTileObject;
import com.kwojewod.contra.sprites.Player;


public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact){
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
    }
    @Override
    public void endContact(Contact contact) {
       // player.onPlatform = false;

        //contact.setEnabled(true);

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        float velocity = 0;

        if ((fixA.getUserData()=="Ground" && fixB.getUserData()=="player")||(fixA.getUserData()=="player" && fixB.getUserData()=="Ground")) {
            if (fixA.getUserData() == "Ground") {
                // determining y positions
                velocity = fixB.getBody().getLinearVelocity().y;
            }
            if( fixA.getUserData() == "player" ){
                    // determining y positions
                    velocity = fixA.getBody().getLinearVelocity().y;

            }
            if(velocity > 0){
                   contact.setEnabled(false);
                   if(!contact.isEnabled()){
                       System.out.println("?!?");
                   }
            }
        }

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }



}
