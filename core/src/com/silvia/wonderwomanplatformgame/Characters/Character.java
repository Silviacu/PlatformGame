package com.silvia.wonderwomanplatformgame.Characters;

import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Characters.AttackCollection.AttackCollection;
import com.silvia.wonderwomanplatformgame.Screens.GameScreen;
import com.silvia.wonderwomanplatformgame.Sprites.CharacterSprite;
import com.silvia.wonderwomanplatformgame.Characters.AttackCollection.AttackCollection;

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
    public double attackDamage = 5.0;
    public int invulnerabilityTimer;
    public float health;
    protected AttackCollection attacks;

    public CharacterSprite characterSprite;
    public CharacterLivingStatus status;

    public CharacterLivingStatus getStatus() {
        return status;
    }
    public void setStatus(CharacterLivingStatus status) {
        this.status = status;
    }

    public Character() {
        this("Default Character", 10);
    }

    public Character(String name, float health) {
        this.characterName = name;
        this.health = health;
        this.status = CharacterLivingStatus.ALIVE;
    }

    // render the characters sprite on the world/ screen
    public void renderSprite(World world){
        characterSprite = new CharacterSprite(world, "ww.png");
    };

    //     DAMAGES
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
            status = CharacterLivingStatus.DEAD;
            health = 0;
        } else{
            health -= damage;
        }
        if (invulnerabilityTimer == 0) {
            invulnerabilityTimer = 3;
        }
        System.out.println(status);
        return health;
    }
}