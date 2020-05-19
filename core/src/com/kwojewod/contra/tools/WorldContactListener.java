package com.kwojewod.contra.tools;

import com.badlogic.gdx.physics.box2d.*;
import com.kwojewod.contra.sprites.InteractiveTileObject;
import com.kwojewod.contra.sprites.Player;


public class WorldContactListener implements ContactListener {
    Player player;
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();


        if (fixA.getUserData() == "player" || fixB.getUserData() == "player") {
            Fixture head = fixA.getUserData() == "player" ? fixA : fixB;
            Fixture object1 = head == fixA ? fixB : fixA;

            if (object1.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object1.getUserData().getClass())) {
                ((InteractiveTileObject) object1.getUserData()).onHeadHit();
                contact.setEnabled(false);
            }

        }
        if (fixA.getUserData() == "player" || fixB.getUserData() == "player") {
            Fixture feet = fixA.getUserData() == "player" ? fixA : fixB;
            Fixture object2 = feet == fixA ? fixB : fixA;

            if (object2.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object2.getUserData().getClass())) {
                ((InteractiveTileObject) object2.getUserData()).onFeet();
            }
        }
    }
    @Override
    public void endContact(Contact contact) {


    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
       /* Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        if (fixA.getUserData() == "player" || fixB.getUserData() == "player") {
            Fixture head = fixA.getUserData() == "player" ? fixA : fixB;
            Fixture object2 = head == fixA ? fixB : fixA;

            if (contact.getFixtureA().getBody().getUserData() == "ground" && contact.getFixtureB().getUserData() == "player") {
                ((InteractiveTileObject) object2.getUserData()).onHeadHit();
                contact.setEnabled(false);
            }
        }

        //and we need to disable contact when our "groundChecker" will collide with "ground" and we need to check what velocity.y of player body is, when it is bigger than 0 contact should be falsed
        if (fixA.getUserData() == "player" || fixB.getUserData() == "player") {
            Fixture feet = fixA.getUserData() == "player" ? fixA : fixB;
            Fixture object2 = feet == fixA ? fixB : fixA;

            if (object2.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object2.getUserData().getClass())) {
               //((InteractiveTileObject) object2.getUserData()).onFeet();
                contact.setEnabled(false);

            }
       }*/

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
