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
        this("Default Character", 10);
    }

    public Character(String name, float health) {
        this.characterName = name;
        this.health = health;
        this.status = CharacterLivingStatus.ALIVE;
    }

    public void renderSprite(World world){
        characterSprite = new CharacterSprite(world, "ww.png");
    };

    public double dealDamage(double damage, Character targetCharacter) {
        if (targetCharacter.receiveDamage(damage) <= 0) {
            System.out.println(this.characterName + " killed " + targetCharacter.characterName);
            targetCharacter.health = 0;
            targetCharacter.status = CharacterLivingStatus.DEAD;
        }
        return targetCharacter.health;
    }

    public double receiveDamage(double damage) {
        if (health - damage <= 0) {
            this.status = CharacterLivingStatus.DEAD;
            this.health =0;
            // this.rip();
        } else{
            this.health -= damage;
        }
        System.out.println(this.status);
        return health - damage;
    }
}
