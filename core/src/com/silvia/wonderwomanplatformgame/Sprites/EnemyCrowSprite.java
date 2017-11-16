package com.silvia.wonderwomanplatformgame.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by silvia on 11/15/2017.
 */

public class EnemyCrowSprite extends CharacterSprite {


    public enum State { IDLE, FLY, SWOOP };
    public EnemyCrowSprite.State currentState;
    public EnemyCrowSprite.State previousState;
    public Body b2body;
    public static final String crowTexture ="";

    private Animation<TextureRegion> crowIdle;
    private Animation<TextureRegion> crowFly;
    private Animation<TextureRegion> crowSwoop;

    private float stateTimer;
    private TextureRegion crow_stand;

    // constructor
    public EnemyCrowSprite(World world) {
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
