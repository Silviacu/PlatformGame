package com.silvia.wonderwomanplatformgame.Characters.WonderWoman;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Characters.AttackCollection.AttackCollection;
import com.silvia.wonderwomanplatformgame.Characters.AttackCollection.WonderWomanAttackCollection;
import com.silvia.wonderwomanplatformgame.Characters.Character;
import com.silvia.wonderwomanplatformgame.Screens.GameScreen;
import com.silvia.wonderwomanplatformgame.Sprites.WonderWomanSprite;

/**
 * Created by silvia on 11/15/2017.
 */

public class WonderWomanCharacter extends Character {

    private float jumpSpeed = 4.2f;
    private float walkSpeed = .1f;
    public int invulnerabilityTimer = 0;
    public int punchTimer;
    public int kickTimer;
    public int jumpTimer =0;

    public String characterName = "Wonder Woman";
//    public WonderWomanSprite characterSprite;
    public CharacterState powerupStatus;
    private AttackCollection wwAttacks = new WonderWomanAttackCollection();
    private static WonderWomanCharacter instance = new WonderWomanCharacter();

    public WonderWomanAttackCollection getWwAttacks() {
        return (WonderWomanAttackCollection) this.attacks;
    }
    public float getJumpSpeed() {
        return jumpSpeed;
    }
    public void setJump(float jump) {
        this.jumpSpeed = jump;
    }
    public float getWalkSpeed() {
        return walkSpeed;
    }

    private WonderWomanCharacter() {
        super("Wonder Woman", 10);
    }

    public static WonderWomanCharacter getInstance() {
        if (instance == null)
            instance = new WonderWomanCharacter();
        return instance;
    }

    public void init(World world) {
        this.health = 10;
        this.attackDamage = 1;
        this.powerupStatus = new NormalState();
        renderSprite(world);
        attacks = new WonderWomanAttackCollection();
    }

    public void init(World world, String textureFile) {
        this.health = 10;
        this.attackDamage = 1;
        this.powerupStatus = new NormalState();
        this.punchTimer = 0;
        this.kickTimer = 0;
        renderSprite(world, textureFile);
        attacks = new WonderWomanAttackCollection();
    }


    @Override
    public double receiveDamage(double damage) {
        if (health - damage <= 0) {
            this.status = CharacterLivingStatus.DEAD;
            this.health = 0;
        } else {
            this.health -= damage;
        }
        if (this.invulnerabilityTimer == 0) {
            this.invulnerabilityTimer = 3;
        }
        System.out.println(this.status);
        return health;
    }

    public void renderSprite(World world) {
        characterSprite = new WonderWomanSprite(world);
    }

    public void renderSprite(World world, String textureFile) {
        characterSprite = new WonderWomanSprite(world, textureFile);
    }

    public void jump() {
        //force impluse which is an imditate change ins speed, x y of impluse (y for jump), worldcenter is where in the bosy we wentto apply center/force if its anotehr then it will have a torque
        //third parameter wakes the objects up
        if (((WonderWomanSprite)characterSprite).b2body.getLinearVelocity().y <= 0 && jumpTimer == 0) {
            ((WonderWomanSprite)characterSprite).b2body.applyLinearImpulse(new Vector2(0, this.powerupStatus.getJump(jumpSpeed)), ((WonderWomanSprite)characterSprite).b2body.getWorldCenter(), true);
            ((WonderWomanSprite) characterSprite).setSprite(WonderWomanSprite.WWSpriteState.JUMPING);
            jumpTimer = 60;
        }
    }

    public void walkRight() {
        ((WonderWomanSprite)characterSprite).b2body.applyLinearImpulse(new Vector2(walkSpeed, 0), ((WonderWomanSprite)characterSprite).b2body.getWorldCenter(), true);

    }

    public void walkLeft() {
        ((WonderWomanSprite)characterSprite).b2body.applyLinearImpulse(new Vector2(-walkSpeed, 0), ((WonderWomanSprite)characterSprite).b2body.getWorldCenter(), true);
        ((WonderWomanSprite) characterSprite).setSprite(WonderWomanSprite.WWSpriteState.RUNNING);
    }

    public void punch() {
        WonderWomanCharacter.getInstance().punchTimer = 60;
        ((WonderWomanSprite) characterSprite).setSprite(WonderWomanSprite.WWSpriteState.PUNCHING);
    }

    public void kick() {
        WonderWomanCharacter.getInstance().kickTimer = 60;
        ((WonderWomanSprite) characterSprite).setSprite(WonderWomanSprite.WWSpriteState.KICKING);
    }

    public float getXPosition() {
        return ((WonderWomanSprite)characterSprite).b2body.getPosition().x;

    }

}

