package com.silvia.wonderwomanplatformgame.World;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.silvia.wonderwomanplatformgame.Screens.GameScreen;


/**
 * Created by silvia on 11/15/2017.
 */

public class WorldObjectCollisionListener implements ContactListener {

    GameScreen screen;

    public WorldObjectCollisionListener(){}
    public WorldObjectCollisionListener(GameScreen screen){}

    @Override
    public void beginContact(Contact contact) {// two collisions begin to collide

    }

    @Override
    public void endContact(Contact contact) {//fixtures disconnect from each other

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {//once something has collided you can change the characsrtics of collison

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {// result of that collison

    }
}
