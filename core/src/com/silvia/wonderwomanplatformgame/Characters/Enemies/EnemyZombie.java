package com.silvia.wonderwomanplatformgame.Characters.Enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Characters.AttackCollection.EnemyZombieAttackCollection;
import com.silvia.wonderwomanplatformgame.Characters.EnemyCharacter;
import com.silvia.wonderwomanplatformgame.HUDs.PlayHUD;
import com.silvia.wonderwomanplatformgame.Sprites.EnemyZombieSprite;
import com.silvia.wonderwomanplatformgame.Characters.Character;


/**
 * Created by silvia on 11/15/2017.
 */

public class EnemyZombie extends EnemyCharacter {

    private float walkspeed;
    public boolean facingLeft;
    public float enemyContactDamage = 0;
    public int invulnerabilityTimer;
    public int biteTimer;


    //public EnemyZombieSprite zombieSprite;

    public float getWalkSpeed(){ return walkspeed;}
    public int getBiteTimer() { return biteTimer; }
    public void setBiteTimer(int newEnlargeTimer) { this.biteTimer = newEnlargeTimer; }

    public EnemyZombie() {
        this("Zombie", 1, new World(new Vector2(0, -10), true), 400, 400);
    }

    public EnemyZombie(String name, float contactDamage, World world, int xposition, int yposition) {
        this.characterName = name;
        this.enemyContactDamage = contactDamage;
        this.walkspeed = 1;
        this.facingLeft = true;
        biteTimer = 0;
        this.invulnerabilityTimer = 0;
        this.attacks = new EnemyZombieAttackCollection();

        characterSprite = new EnemyZombieSprite(world, this, xposition, yposition);
    }

    @Override
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

    public void bite() {
        biteTimer = 120;
        enemyContactDamage = ((EnemyZombieAttackCollection)attacks).zombieAttack.attackBaseDamage;

    }

    public void idle() {
        enemyContactDamage = 1;
    }

    public void walkLeft() {
        ((EnemyZombieSprite) this.characterSprite).b2body.applyLinearImpulse(-0.04f, 0, ((EnemyZombieSprite) this.characterSprite).b2body.getWorldCenter().x, ((EnemyZombieSprite) this.characterSprite).b2body.getWorldCenter().y, true);
        this.facingLeft = true;
    }

    public void walkRight() {
        ((EnemyZombieSprite) this.characterSprite).b2body.applyLinearImpulse(0.04f, 0, ((EnemyZombieSprite) this.characterSprite).b2body.getWorldCenter().x, ((EnemyZombieSprite) this.characterSprite).b2body.getWorldCenter().y, true);
        this.facingLeft = false;
    }

    public void update (float dt){
        ((EnemyZombieSprite) this.characterSprite).update(dt);

        if(this.status == CharacterLivingStatus.ALIVE)
            idle();

        if(PlayHUD.worldTimer % 6 == 0 && biteTimer == 0) {
            bite();
        }

        if(PlayHUD.worldTimer % 2 == 0 && PlayHUD.worldTimer % 4 != 0 && biteTimer == 0) {
            walkLeft();
        }

        if(PlayHUD.worldTimer % 2 == 0 && PlayHUD.worldTimer % 4 == 0 && biteTimer == 0) {
            walkRight();
        }

        if(biteTimer > 0){
            biteTimer--;
        }

        if(invulnerabilityTimer > 0){
            invulnerabilityTimer--;
        }
    }

}
