package com.silvia.wonderwomanplatformgame.World;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.WonderWomanCharacter;
import com.silvia.wonderwomanplatformgame.Screens.GameScreen;
import com.silvia.wonderwomanplatformgame.Sprites.EnemyBigSprite;
import com.silvia.wonderwomanplatformgame.Sprites.EnemyCrowSprite;
import com.silvia.wonderwomanplatformgame.Sprites.EnemyZombieSprite;
import com.silvia.wonderwomanplatformgame.Sprites.WonderWomanSprite;
import com.silvia.wonderwomanplatformgame.World.Buildables.InteractiveTileObject;

import static com.silvia.wonderwomanplatformgame.WonderWomanGame.ENEMY_BIT;
import static com.silvia.wonderwomanplatformgame.WonderWomanGame.WONDER_BIT;


/**
 * Created by silvia on 11/15/2017.
 */

public class WorldObjectCollisionListener implements ContactListener {

    static GameScreen screen;

    public WorldObjectCollisionListener(){}

    public WorldObjectCollisionListener(GameScreen screen) {
        this.screen = screen;
    }

    @Override
    public void beginContact(Contact contact) {// two collisions begin to collide
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA.getFilterData().categoryBits == WONDER_BIT || fixB.getFilterData().categoryBits == WONDER_BIT ){// two fixtures and test which one is touching
            Fixture touch = fixA.getUserData() == "body_head" ? fixA : fixB;// if it is then we want to get the touch and the object it is colliding
            Fixture object = touch == fixA ? fixB : fixA;
            Fixture object2 = touch != fixA ? fixB : fixA;

            if(object.getUserData() instanceof InteractiveTileObject){// check to see if the object is an interactive tile object
                ((InteractiveTileObject) object.getUserData()).onTouch();
            }

            if(fixA.getFilterData().categoryBits == WONDER_BIT && fixB.getFilterData().categoryBits == ENEMY_BIT) {

                if(fixB.getUserData() == null)
                    fixB.setUserData("Nothin");
                // ZOMBIE SPRITE BEING TOUCCHHHED ===========================
                if(fixB.getUserData() == ((EnemyZombieSprite) screen.zombie1.characterSprite).b2body.getUserData()) {
                    if (
                            ((WonderWomanCharacter) screen.player1).invulnerabilityTimer == 0 &&
                                    ((WonderWomanCharacter) screen.player1).kickTimer == 0 &&
                                    ((WonderWomanCharacter) screen.player1).punchTimer == 0
                            ){
//                        ((WonderWomanCharacter) screen.player1).receiveDamage(((EnemyZombieSprite) fixB.getUserData()).getZombie().enemyContactDamage);
                        ((EnemyZombieSprite) fixB.getUserData()).getZombie().dealContactDamage((WonderWomanCharacter) screen.player1);
                    }
                    else if (
                            ((WonderWomanCharacter) screen.player1).kickTimer > 0 &&
                                    fixB.getUserData() == ((EnemyZombieSprite) screen.zombie1.characterSprite).b2body.getUserData()
                            ) {
                        if(((EnemyZombieSprite) fixB.getUserData()).getZombie().invulnerabilityTimer == 0) {
                            ((EnemyZombieSprite) fixB.getUserData()).getZombie().receiveDamage(
                                    (((WonderWomanCharacter) screen.player1).getWwAttacks())
                                            .kickAttack.calculateAttackDamage(
                                            ((WonderWomanCharacter) screen.player1).powerupStatus.getDamage(1)
                                    )
                            );
                            ((EnemyZombieSprite) fixB.getUserData()).getZombie().invulnerabilityTimer = 60;
                        }
                    }
                    else if (
                            ((WonderWomanCharacter) screen.player1).punchTimer > 0 &&
                                    fixB.getUserData() == ((EnemyZombieSprite) screen.zombie1.characterSprite).b2body.getUserData()
                            ) {
                        if(((EnemyZombieSprite) fixB.getUserData()).getZombie().invulnerabilityTimer == 0) {
                            ((EnemyZombieSprite) fixB.getUserData()).getZombie().receiveDamage(
                                    (((WonderWomanCharacter) screen.player1).getWwAttacks())
                                            .punchAttack.calculateAttackDamage(
                                            ((WonderWomanCharacter) screen.player1).powerupStatus.getDamage(1)
                                    )
                            );
                            ((EnemyZombieSprite) fixB.getUserData()).getZombie().invulnerabilityTimer = 60;
                        }
                    }


                    // WOLFIE SPRITE BEING TOUCCHHHED ===========================
                } else if (fixB.getUserData() == ((EnemyBigSprite) screen.big2.characterSprite).b2body.getUserData()) {
                    System.out.println("Woof");
                    if (
                            ((WonderWomanCharacter) screen.player1).invulnerabilityTimer == 0 &&
                                    ((WonderWomanCharacter) screen.player1).kickTimer == 0 &&
                                    ((WonderWomanCharacter) screen.player1).punchTimer == 0
                            ){
//                        ((WonderWomanCharacter) screen.player1).receiveDamage(((EnemyBigSprite) fixB.getUserData()).getBig().enemyContactDamage);
                        ((EnemyBigSprite) fixB.getUserData()).getBig().dealContactDamage((WonderWomanCharacter) screen.player1);
                    }
                    else if (
                            ((WonderWomanCharacter) screen.player1).kickTimer > 0 &&
                                    fixB.getUserData() == ((EnemyBigSprite) screen.big2.characterSprite).b2body.getUserData()
                            ) {
                        if(((EnemyBigSprite) fixB.getUserData()).getBig().invulnerabilityTimer == 0) {
                            ((EnemyBigSprite) fixB.getUserData()).getBig().receiveDamage(
                                    (((WonderWomanCharacter) screen.player1).getWwAttacks())
                                            .kickAttack.calculateAttackDamage(
                                            ((WonderWomanCharacter) screen.player1).powerupStatus.getDamage(1)
                                    )
                            );
                            ((EnemyBigSprite) fixB.getUserData()).getBig().invulnerabilityTimer = 60;
                        }
                    }
                    else if (
                            ((WonderWomanCharacter) screen.player1).punchTimer > 0 &&
                                    fixB.getUserData() == ((EnemyBigSprite) screen.big2.characterSprite).b2body.getUserData()
                            ) {
                        if(((EnemyBigSprite) fixB.getUserData()).getBig().invulnerabilityTimer == 0) {
                            ((EnemyBigSprite) fixB.getUserData()).getBig().receiveDamage(
                                    (((WonderWomanCharacter) screen.player1).getWwAttacks())
                                            .punchAttack.calculateAttackDamage(
                                            ((WonderWomanCharacter) screen.player1).powerupStatus.getDamage(1)
                                    )
                            );
                            ((EnemyBigSprite) fixB.getUserData()).getBig().invulnerabilityTimer = 60;
                        }
                    }


                    // CROW SPRITE BEING TOUCCHHHED ===========================
                } else if (fixB.getUserData() == ((EnemyCrowSprite) screen.crow1.characterSprite).b2body.getUserData()) {
                    if (
                            ((WonderWomanCharacter) screen.player1).invulnerabilityTimer == 0 &&
                                    ((WonderWomanCharacter) screen.player1).kickTimer == 0 &&
                                    ((WonderWomanCharacter) screen.player1).punchTimer == 0
                            ) {
//                        ((WonderWomanCharacter) screen.player1).receiveDamage(((EnemyCrowSprite) fixB.getUserData()).getCrow().enemyContactDamage);
                        ((EnemyCrowSprite) fixB.getUserData()).getCrow().dealContactDamage((WonderWomanCharacter) screen.player1);
                    }
                    else if (
                            ((WonderWomanCharacter) screen.player1).kickTimer > 0 &&
                                    fixB.getUserData() == ((EnemyCrowSprite) screen.crow1.characterSprite).b2body.getUserData()
                            ) {
                        if(((EnemyCrowSprite) fixB.getUserData()).getCrow().invulnerabilityTimer == 0) {
                            ((EnemyCrowSprite) fixB.getUserData()).getCrow().receiveDamage(
                                    (((WonderWomanCharacter) screen.player1).getWwAttacks())
                                            .kickAttack.calculateAttackDamage(
                                            ((WonderWomanCharacter) screen.player1).powerupStatus.getDamage(1)
                                    )
                            );
                            ((EnemyCrowSprite) fixB.getUserData()).getCrow().invulnerabilityTimer = 60;
                        }
                    }
                    else if (
                            ((WonderWomanCharacter) screen.player1).punchTimer > 0 &&
                                    fixB.getUserData() == ((EnemyCrowSprite) screen.crow1.characterSprite).b2body.getUserData()
                            ) {
                        if(((EnemyCrowSprite) fixB.getUserData()).getCrow().invulnerabilityTimer == 0) {
                            ((EnemyCrowSprite) fixB.getUserData()).getCrow().receiveDamage(
                                    (((WonderWomanCharacter) screen.player1).getWwAttacks())
                                            .punchAttack.calculateAttackDamage(
                                            ((WonderWomanCharacter) screen.player1).powerupStatus.getDamage(1)
                                    )
                            );
                            ((EnemyCrowSprite) fixB.getUserData()).getCrow().invulnerabilityTimer = 60;
                        }
                    }
                }
                ((WonderWomanSprite) ((WonderWomanCharacter) screen.player1).characterSprite).b2body.applyForce(
                        -((WonderWomanSprite) ((WonderWomanCharacter) screen.player1).characterSprite).b2body.getLinearVelocity().x*25,
                        -((WonderWomanSprite) ((WonderWomanCharacter) screen.player1).characterSprite).b2body.getLinearVelocity().y*25,
                        ((WonderWomanSprite) ((WonderWomanCharacter) screen.player1).characterSprite).b2body.getWorldCenter().x,
                        ((WonderWomanSprite) ((WonderWomanCharacter) screen.player1).characterSprite).b2body.getWorldCenter().y,
                        true
                );
            }
        }
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
