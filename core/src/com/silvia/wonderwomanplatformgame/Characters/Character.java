package com.silvia.wonderwomanplatformgame.Characters;

import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Screens.GameScreen;
import com.silvia.wonderwomanplatformgame.Sprites.CharacterSprite;

import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Screens.GameScreen;

/**
 * Created by silvia on 11/15/2017.
 */

public class Character {

    public enum CharacterLivingStatus {
        ALIVE,
        DEAD
    }

    public String characterName;
    public CharacterSprite characterSprite;
    public CharacterLivingStatus status;
    public float health;
    public double attackDamage = 5.0;

    public Character() {

    }

    public Character(String name, float health) {
    }

    public void renderSprite(World world, GameScreen screen){
    };

    public double dealDamage(double damage, Character targetCharacter) {
        return targetCharacter.health;
    }

    public double receiveDamage(double damage) {
        return health - damage;
    }
}
