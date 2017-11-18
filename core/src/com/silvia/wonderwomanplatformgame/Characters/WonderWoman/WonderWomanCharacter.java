package com.silvia.wonderwomanplatformgame.Characters.WonderWoman;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Characters.Character;
import com.silvia.wonderwomanplatformgame.Screens.GameScreen;
import com.silvia.wonderwomanplatformgame.Sprites.WonderWomanSprite;

/**
 * Created by silvia on 11/15/2017.
 */

public class WonderWomanCharacter extends Character {
    public String characterName = "Wonder Woman";
    public WonderWomanSprite characterSprite;

    // CONSTANT DEFAULT MOVEMENT SPEED VALUES
    private float jumpSpeed = 4f;
    private float walkSpeed = .1f;

    private static WonderWomanCharacter instance = new WonderWomanCharacter();
    private WonderWomanCharacter(){}

    public float getJumpSpeed() { return jumpSpeed; }
    public float getWalkSpeed() { return walkSpeed; }


    public static WonderWomanCharacter getInstance() {
        if (instance == null)
            instance = new WonderWomanCharacter();
        return instance;
    }

    public void init(World world) {
        this.health = 10;
        this.attackDamage = 5;
        renderSprite(world);
    }

    public void init(World world, String textureFile) { // need texturefile init constructor for testing
        this.health = 10;
        this.attackDamage = 5;
        renderSprite(world, textureFile);
    }

    @Override
    public void renderSprite(World world){
        characterSprite = new WonderWomanSprite(world);
    }

    public void renderSprite(World world, String textureFile){
        characterSprite = new WonderWomanSprite(world, textureFile);
    }

    public void jump() {
        characterSprite.b2body.applyLinearImpulse(new Vector2(0, jumpSpeed), characterSprite.b2body.getWorldCenter(), true);
    }

    public void walkRight(){
        characterSprite.b2body.applyLinearImpulse(new Vector2(walkSpeed,0), characterSprite.b2body.getWorldCenter(), true);

    }

    public void walkLeft() {
        characterSprite.b2body.applyLinearImpulse(new Vector2(-walkSpeed,0), characterSprite.b2body.getWorldCenter(), true);

    }

    public float getXPosition(){
        return characterSprite.b2body.getPosition().x;

    }


}
