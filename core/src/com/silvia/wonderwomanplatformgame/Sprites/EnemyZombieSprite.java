package com.silvia.wonderwomanplatformgame.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Characters.Enemies.EnemyZombie;

/**
 * Created by silvia on 11/15/2017.
 */

public class EnemyZombieSprite extends CharacterSprite {


    public enum State { IDLE, WALK, BITE };
    public EnemyZombieSprite.State currentState;
    public EnemyZombieSprite.State previousState;
    public Body b2body;
    public static final String wwTexture ="";

    private Animation<TextureRegion> zombieIdle;
    private Animation<TextureRegion> zombieWalk;
    private Animation<TextureRegion> zombieBite;

    private float stateTimer;
    private TextureRegion zombie_stand;

    // constructor
    public EnemyZombieSprite(World world) {
    }

    private void setupAnimationFrames() {// test in progress need to finish (tedious)

    }
    private void setupIdleFrames() {

    }

    private void setupWalkingFrames() {


    }

    private void setupEnlargeFrames() {


    }

    public void update(float dt) {

    }
    private void checkCollision() {

    }

    private int getFrame(float dt) {
        return 0;
    }


    public void setSprite() {

    }

    private int getState() {
        return 0;
    }

    @Override
    public void defineCharacter() {
    }
}
