package com.silvia.wonderwomanplatformgame.Characters.Enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Characters.AttackCollection.EnemyBigAttackCollection;
import com.silvia.wonderwomanplatformgame.Characters.EnemyCharacter;
import com.silvia.wonderwomanplatformgame.HUDs.PlayHUD;
import com.silvia.wonderwomanplatformgame.Sprites.EnemyBigSprite;
import com.silvia.wonderwomanplatformgame.Sprites.EnemyZombieSprite;
import com.silvia.wonderwomanplatformgame.Characters.Character;

/**
 * Created by silvia on 11/15/2017.
 */

public class EnemyBig extends EnemyCharacter {

    private float walkspeed;
    public boolean facingLeft;
    public float enemyContactDamage = 0;
    public int invulnerabilityTimer;
    private int enlargeTimer;


    public EnemyBigSprite bigSprite;

    public int getEnlargeTimer() { return enlargeTimer; }
    public void setEnlargeTimer(int newEnlargeTimer) { this.enlargeTimer = newEnlargeTimer; }
    public float getWalkSpeed(){ return walkspeed;}

    public EnemyBig() {
        this("Big", 1, new World(new Vector2(0, -10), true), 400, 400);
    }
    public EnemyBig(String name, float contactDamage, World world, int xposition, int yposition) {
        this.characterName = name;
        this.enemyContactDamage = contactDamage;
        this.walkspeed = 1;
        this.facingLeft = true;
        this.invulnerabilityTimer = 0;
        this.enlargeTimer = 0;
        this.attacks = new EnemyBigAttackCollection();

        bigSprite = new EnemyBigSprite(world, this, xposition, yposition);
    }


    public void dealContactDamage(Character targetCharacter) {
        this.dealDamage(this.enemyContactDamage, targetCharacter);
    }

    @Override
    public double receiveDamage(double damage) {
        if (health - damage <= 0) {
            this.status = CharacterLivingStatus.DEAD;
            this.health = 0;
        } else{
            this.health -= damage;
        }
        if (this.invulnerabilityTimer == 0) {
            this.invulnerabilityTimer = 3;
        }
        System.out.println(this.status);
        return health;
    }

    public void idle() {
        enemyContactDamage = 2;
    }

    public void enlarge() {
        enlargeTimer = 120;
        enemyContactDamage = ((EnemyBigAttackCollection) attacks).bigAttack.attackBaseDamage;

    }

    public void walkLeft() {
        this.bigSprite.b2body.applyLinearImpulse(-0.06f, 0, this.bigSprite.b2body.getWorldCenter().x, this.bigSprite.b2body.getWorldCenter().y, true);
        this.facingLeft = true;
    }

    public void walkRight() {
        this.bigSprite.b2body.applyLinearImpulse(0.06f, 0, this.bigSprite.b2body.getWorldCenter().x, this.bigSprite.b2body.getWorldCenter().y, true);
        this.facingLeft = false;
    }

    public void update(float dt) {
        this.bigSprite.update(dt);

        if(this.status == CharacterLivingStatus.ALIVE)
            idle();

        if(PlayHUD.worldTimer % 6 == 0 && enlargeTimer == 0) {
            enlarge();
        }

        if(PlayHUD.worldTimer % 2 == 0 && PlayHUD.worldTimer % 4 != 0 && enlargeTimer == 0) {
            walkLeft();
        }

        if(PlayHUD.worldTimer % 2 == 0 && PlayHUD.worldTimer % 4 == 0 && enlargeTimer == 0) {
            walkRight();
        }

        if (enlargeTimer > 0) {
            enlargeTimer--;
        }

        if (invulnerabilityTimer > 0) {
            invulnerabilityTimer--;
        }
    }



}
