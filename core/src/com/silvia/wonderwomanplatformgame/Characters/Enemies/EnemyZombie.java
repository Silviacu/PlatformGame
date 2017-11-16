package com.silvia.wonderwomanplatformgame.Characters.Enemies;

import com.silvia.wonderwomanplatformgame.Characters.EnemyCharacter;
import com.silvia.wonderwomanplatformgame.Sprites.EnemyZombieSprite;

/**
 * Created by silvia on 11/15/2017.
 */

public class EnemyZombie extends EnemyCharacter {
    private float walkspeed;
    public EnemyZombieSprite zombieSprite;
    public boolean facingLeft;

    public EnemyZombie() {
        this.walkspeed = 1;
        this.facingLeft = true;
    }

    public void bite() {

    }

    public void idle() {

    }

    public void walkLeft() {

    }

    public void walkRight() {

    }

    public float getXPosition() {
        return 0.0f;
    }
}
