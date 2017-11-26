package com.silvia.wonderwomanplatformgame.Characters.Enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Characters.EnemyCharacter;
import com.silvia.wonderwomanplatformgame.HUDs.PlayHUD;
import com.silvia.wonderwomanplatformgame.Sprites.EnemyCrowSprite;
import com.silvia.wonderwomanplatformgame.Characters.Character;

/*
 * Created by silvia on 11/15/2017.
 */

public class EnemyCrow extends EnemyCharacter {
    private float flyspeed;
    public boolean facingLeft;
    public float enemyContactDamage = 0;
    private int swoopTimer;
    public int invulnerabilityTimer;

    public EnemyCrowSprite crowSprite;


    public void setSwoopTimer(int newSwoopTimer) { this.swoopTimer = newSwoopTimer; }
    public int getSwoopTimer() { return swoopTimer; }
    public float getFlySpeed(){ return flyspeed;}


    public EnemyCrow() {
        this("Crow", 1, new World(new Vector2(0, -10), true), 400, 400);
    }

    public EnemyCrow(String name, float contactDamage, World world, int xposition, int yposition) {
        this.characterName = name;
        this.enemyContactDamage = contactDamage;
        this.flyspeed = 1;
        this.facingLeft = true;
        this.invulnerabilityTimer = 0;

        crowSprite = new EnemyCrowSprite(world, this, xposition, yposition);
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

    public void swoop() {
        swoopTimer = 120;
        enemyContactDamage =6;
        this.crowSprite.b2body.applyForce(0f, -50f, this.crowSprite.b2body.getWorldCenter().x, this.crowSprite.b2body.getWorldCenter().y, true);
    }

    public void idle() {
        enemyContactDamage = 1;
    }

    public void flyLeft() {
        //System.out.println("Walk Left");
        this.crowSprite.b2body.applyForce(-0.08f, 0, this.crowSprite.b2body.getWorldCenter().x, this.crowSprite.b2body.getWorldCenter().y, true);
        this.facingLeft = true;
    }

    public void flyRight() {
        // System.out.println("Walk Right");
        this.crowSprite.b2body.applyForce(0.08f, 0, this.crowSprite.b2body.getWorldCenter().x, this.crowSprite.b2body.getWorldCenter().y, true);
        this.facingLeft = false;
    }

    public void update (float dt){
        this.crowSprite.update(dt);

        if(this.status == CharacterLivingStatus.ALIVE && swoopTimer <= 0)
            idle();

        if(PlayHUD.worldTimer % 6 == 0 && swoopTimer == 0) {
            swoop();
        }

        if(swoopTimer > 60) {
            this.crowSprite.b2body.applyForce(0f, -50f, this.crowSprite.b2body.getWorldCenter().x, this.crowSprite.b2body.getWorldCenter().y, true);
        }

        if(PlayHUD.worldTimer % 2 == 0 && PlayHUD.worldTimer % 4 != 0 && swoopTimer == 0) {
            flyLeft();
        }

        if(PlayHUD.worldTimer % 2 == 0 && PlayHUD.worldTimer % 4 == 0 && swoopTimer == 0) {
            flyRight();
        }

        if (swoopTimer > 0) {
            swoopTimer--;
        }

        if (invulnerabilityTimer > 0) {
            invulnerabilityTimer--;
        }

    }

}
