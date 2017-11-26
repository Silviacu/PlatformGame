package com.silvia.wonderwomanplatformgame.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.silvia.wonderwomanplatformgame.Characters.Enemies.EnemyZombie;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;
import com.silvia.wonderwomanplatformgame.Characters.Character;
/**
 * Created by silvia on 11/15/2017.
 */

public class EnemyZombieSprite extends CharacterSprite {


    public ZombieSpriteState currentState;
    public ZombieSpriteState previousState;
    public Body b2body;;
    public static final String zombieTexture = "zombie.png";
    private int xposition;
    private int yposition;
    private EnemyZombie zombie;
    private boolean runningRight;
    private float stateTimer;
    private boolean deadFlag = false;

    private TextureRegion zombie_stand;

    private Animation<TextureRegion> zombieIdle;
    private Animation<TextureRegion> zombieWalk;
    private Animation<TextureRegion> zombieBite;

    private Array<TextureRegion> zombieFrames = new Array<TextureRegion>();
    public enum ZombieSpriteState { IDLE, WALK, BITE, DEAD };

    public Animation<TextureRegion> getZombieIdle() {
        return zombieIdle;
    }
    public Animation<TextureRegion> getZombieWalk() {
        return zombieWalk;
    }
    public Animation<TextureRegion> getZombieBite() {
        return zombieBite;
    }
    public EnemyZombie getZombie(){return zombie;}

    // constructor
    public EnemyZombieSprite(World world, EnemyZombie zombie, int xposition, int yposition) {
        super(world, zombieTexture);
        this.world = world;
        this.zombie = zombie;
        currentState = ZombieSpriteState.IDLE;
        previousState = ZombieSpriteState.IDLE;
        stateTimer = 0;
        runningRight = true;
        this.xposition = xposition;
        this.yposition = yposition;
        this.setupAnimationFrames();
        this.defineCharacter();

        zombie_stand = new TextureRegion(getTexture(), 0, 0, 50, 60);// Zombie
        setBounds(0,0, 50/ WonderWomanGame.PPM, 60 / WonderWomanGame.PPM);
        setRegion(zombie_stand);
    }

    private void setupAnimationFrames() {// test in progress need to finish (tedious)
        setupIdleFrames();
        setupWalkingFrames();
        setupBiteFrames();
    }

    private void setupIdleFrames() {
        // IDLE ANIMATION
        for(int i =0; i <6; i++) {
            zombieFrames.add(new TextureRegion(getTexture(), 10 + (i * 48), 162, 48, 70)); //IDLE Zombie
        }

        zombieIdle = new Animation(0.2f, zombieFrames);
        zombieFrames.clear();
    }

    private void setupWalkingFrames() {

        //
        for(int i =0; i <6; i++) {
            zombieFrames.add(new TextureRegion(getTexture(), 7 + (i * 57), 87, 57, 70)); //Walking Zombie
        }

        for(int i =0; i <6; i++) {
            zombieFrames.add(new TextureRegion(getTexture(), 286 + (i * 50), 87, 57, 70)); //Walking Zombie
        }

        zombieWalk = new Animation(0.1f, zombieFrames);
        zombieFrames.clear();
    }

    private void setupBiteFrames() {
        // BITE ANIMATION
        for(int i =0; i <5; i++) {
            zombieFrames.add(new TextureRegion(getTexture(), 10 + (i * 48), 244, 48, 70)); //Bite/Attack Zombie

        }

        zombieBite = new Animation(0.2f, zombieFrames);
        zombieFrames.clear();
    }



    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
        setRegion(getFrame(dt));

        if(zombie.status == Character.CharacterLivingStatus.DEAD && !deadFlag) {
            deadFlag = true;
            System.out.println("Zombie Dead");
            setRegion(new TextureRegion(getTexture(), 0,0,0,0));
            world.destroyBody(b2body);
        }
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();

        TextureRegion region;
        switch(currentState){
            case DEAD:
                region = new TextureRegion(getTexture(), 0,0,0,0);
                break;
            case BITE:
                region = zombieBite.getKeyFrame(stateTimer);
                break;
            case WALK:
                region = zombieWalk.getKeyFrame(stateTimer, true);
                break;
            case IDLE:
            default:
                region = zombieIdle.getKeyFrame(stateTimer, true);
                break;
        }

        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        } else if ((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }

        stateTimer = currentState == previousState
                ? stateTimer + dt
                : 0;

        previousState = currentState;

        return region;
    }

    public ZombieSpriteState getState() {
        if(this.zombie.health <= 0) {
            return ZombieSpriteState.DEAD;
        }

        if(this.zombie.biteTimer > 0) {
            return ZombieSpriteState.BITE;
        }

        if(b2body.getLinearVelocity().x !=0)
            return ZombieSpriteState.WALK;
        else
            return ZombieSpriteState.IDLE;
    }

    @Override
    public void defineCharacter() {
        BodyDef bodydefinitionw = new BodyDef();
        bodydefinitionw.position.set(this.xposition / WonderWomanGame.PPM,this.yposition/WonderWomanGame.PPM); //
        bodydefinitionw.type = BodyDef.BodyType.DynamicBody;

        b2body = this.world.createBody(bodydefinitionw);// create the body in our world

        FixtureDef fdef = new FixtureDef();//fixture depth

        fdef.filter.categoryBits = WonderWomanGame.ENEMY_BIT;//what is this fixture
        fdef.filter.maskBits =
                WonderWomanGame.DEFAULT_BIT |
                        WonderWomanGame.SPIKE_BIT |
                        WonderWomanGame.WONDER_BIT; //// what zombie can collide with

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(5/WonderWomanGame.PPM, 25/WonderWomanGame.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef);

        PolygonShape x_sensor = new PolygonShape();
        x_sensor.setAsBox(6/WonderWomanGame.PPM, 26/WonderWomanGame.PPM);
        fdef.shape = x_sensor;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData(this);
    }

    public void onTouch() {
        Gdx.app.log("Enemy Zombie", "Collision");
    }
}
