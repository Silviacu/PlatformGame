package com.silvia.wonderwomanplatformgame.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by silvia on 11/15/2017.
 */

public class EnemyBigSprite extends CharacterSprite {

    public enum State { IDLE, WALK, ENLARGE };
    public EnemyBigSprite.State currentState;
    public EnemyBigSprite.State previousState;
    public Body b2body;
    public static final String bigTexture ="";
    private float stateTimer;

    private Animation<TextureRegion> bigIdle;
    private Animation<TextureRegion> bigWalk;
    private Animation<TextureRegion> bigEnlarge;

    private TextureRegion zombie_stand;

    // constructor
    public EnemyBigSprite(World world) {
    }

    private void setupAnimationFrames() {

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


