package com.silvia.wonderwomanplatformgame.Characters.Enemies;

import com.silvia.wonderwomanplatformgame.Characters.EnemyCharacter;
import com.silvia.wonderwomanplatformgame.Sprites.EnemyCrowSprite;

/*
 * Created by silvia on 11/15/2017.
 */

public class EnemyCrow extends EnemyCharacter {
    private float flySpeed;
    public EnemyCrowSprite crowSprite;
    public boolean facingLeft;

    public EnemyCrow() {
        this.flySpeed = 1;
        this.facingLeft = true;
    }

    public void swoop() {

    }

    public void idle() {

    }

    public void flyLeft() {

    }

    public void flyRight() {

    }

    public float getXPosition() {
        return 0.0f;
    }

    public float getYPosition() {
        return 0.0f;
    }
}
