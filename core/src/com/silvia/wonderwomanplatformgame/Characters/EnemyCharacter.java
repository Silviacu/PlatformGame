package com.silvia.wonderwomanplatformgame.Characters;

import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Screens.GameScreen;

/**
 * Created by silvia on 11/15/2017.
 */

public class EnemyCharacter extends Character {
    public float enemyContactDamage = 0;

    public EnemyCharacter() {
        this("Default Enemy Character", 1);
    }

    public EnemyCharacter(String name, float contactDamage) {
        this.characterName = name;
        this.enemyContactDamage = contactDamage;
    }

    public void renderSprite(World world) {
        super.renderSprite(world);
    }

    public void dealContactDamage(Character targetCharacter) {
        this.dealDamage(this.enemyContactDamage, targetCharacter);
    }
}