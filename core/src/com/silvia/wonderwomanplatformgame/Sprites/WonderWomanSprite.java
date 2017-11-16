package com.silvia.wonderwomanplatformgame.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;


/**
 * Created by silvia on 11/15/2017.
 */

public class WonderWomanSprite extends CharacterSprite {

    public World world; // the world wonderwoman will live in

    public enum State { JUMPING, IDLE, RUNNING, KICKING, PUNCHING };
    public State currentState;
    public State previousState;
    public Body b2body;
    public static final String wwTexture ="";

    private Animation<TextureRegion> wwIdle;
    private Animation<TextureRegion> wwRun;
    private Animation<TextureRegion> wwJump;
    private Animation<TextureRegion> wwKick;
    private Animation<TextureRegion> wwPunch;

    private float stateTimer;
    private boolean runningRight;

    private TextureRegion ww_stand;

    // constructor
    public WonderWomanSprite(World world) {
    }

    private void setupAnimationFrames() {

    }
    private void setupIdleFrames() {
        // IDLE ANIMATION

    }

    private void setupRunningFrames() {
        // RUNNING ANIMATION

    }

    private void setupJumpingFrames() {
        // JUMPING ANIMATION

    }

    public void update(float dt) {// working with function above for testing

    }
    private void checkCollision() {

    }

    private int getFrame(float dt) {
        return 0;
    }


    public void setSprite(State spriteState) {

    }

    private int getState() {
        return 0;
    }

    @Override
    public void defineCharacter() {
    }

}
