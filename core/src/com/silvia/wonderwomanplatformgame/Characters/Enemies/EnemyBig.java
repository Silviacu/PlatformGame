package com.silvia.wonderwomanplatformgame.Characters.Enemies;

import com.silvia.wonderwomanplatformgame.Characters.EnemyCharacter;
import com.silvia.wonderwomanplatformgame.Sprites.EnemyBigSprite;
import com.silvia.wonderwomanplatformgame.Sprites.EnemyZombieSprite;

/**
 * Created by silvia on 11/15/2017.
 */

public class EnemyBig extends EnemyCharacter {
    private float walkspeed;
    public EnemyBigSprite bigSprite;
    public boolean facingLeft;

    public EnemyBig() {
        this.walkspeed = 1;
        this.facingLeft = true;
    }

    public void enlarge() {

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
