package com.silvia.wonderwomanplatformgame.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;
import com.silvia.wonderwomanplatformgame.World.WorldObjectCollisionListener;


/**
 * Created by silvia on 11/15/2017.
 */

public class WonderWomanSprite extends CharacterSprite {

    public World world; // the world wonderwoman will live in

    public enum State {FALLING , JUMPING , IDLE, RUNNING, KICKING, PUNCHING, THROWING};
    public State currentState;
    public State previousState;
    public Body b2body;
    public static final String wwTexture = "ww.png";

    // AVAILABLE WONDERWOMAN ACTION ANIMATIONS
    private Animation<TextureRegion> wwIdle;
    private Animation<TextureRegion> wwRun;
    private Animation<TextureRegion> wwJump;
    private Animation<TextureRegion> wwKick;
    private Animation<TextureRegion> wwPunch;

    public Animation<TextureRegion> getWwIdle() { return wwIdle; }
    public Animation<TextureRegion> getWwRun() { return wwRun; }
    public Animation<TextureRegion> getWwJump() { return wwJump; }
    public Animation<TextureRegion> getWwKick() { return wwKick; }
    public Animation<TextureRegion> getWwPunch() { return wwPunch; }

    private float stateTimer;
    private boolean runningRight;

    private TextureRegion ww_stand;

    // constructor
    public WonderWomanSprite(World world){
        this(world, wwTexture);
    }

    // constructor
    public WonderWomanSprite(World world, String targetTexture){
        super(world, targetTexture);
        this.world = world;
        currentState = State.IDLE;
        previousState = State.IDLE;
        stateTimer = 0;
        runningRight = true;

        this.setupAnimationFrames();

        this.defineCharacter(); // Define Wonder Woman Character within context of World and Screen

        ww_stand = new TextureRegion(getTexture(), 180, 0, 60, 53);
        setBounds(0,0, 60/WonderWomanGame.PPM, 53 / WonderWomanGame.PPM);
        setRegion(ww_stand);

    }

    private Array<TextureRegion> wwFrames = new Array<TextureRegion>();
    private void setupAnimationFrames() {// test in progress need to finish (tedious)
        setupIdleFrames();
        setupRunningFrames();
        setupJumpingFrames();
    }

    private void setupIdleFrames() {
        // IDLE ANIMATION
        for(int i =0; i <3; i++) {
            wwFrames.add(new TextureRegion(getTexture(), 180 + (i * 60), 0, 60, 54));
        }
        wwIdle = new Animation(0.1f, wwFrames);
        wwFrames.clear();
    }

    private void setupRunningFrames() {
        // RUNNING ANIMATION
        for(int i =0; i <3; i++){
            wwFrames.add(new TextureRegion(getTexture(), 180 + (i*60), 520, 60, 54));
        }
        for(int i =0; i<3; i++){
            wwFrames.add(new TextureRegion(getTexture(), 180 + (i*60), 580, 60, 54));
        }
        for(int i =0; i <2; i++){
            wwFrames.add(new TextureRegion(getTexture(), 180 + (i*60), 640, 60, 54));
        }
        wwRun = new Animation(0.1f, wwFrames);
        wwFrames.clear();
    }

    private void setupJumpingFrames() {
        // JUMPING ANIMATION
        wwFrames.add(new TextureRegion(getTexture(), 180 + 120, 120, 60, 54));
        for(int i =0; i <3; i++){
            wwFrames.add(new TextureRegion(getTexture(), 180 + (i*60), 180, 60, 54));
        }

        wwJump = new Animation(0.2f, wwFrames);
        wwFrames.clear();
    }

    public void update(float dt) {// working with function above for testing
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
        setRegion(getFrame(dt));

        checkCollision();
    }

    private void checkCollision() {
        world.setContactListener(new WorldObjectCollisionListener());
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();

        TextureRegion region;
        switch(currentState){
            case JUMPING:
                region = wwJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = wwRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case IDLE:
            default:
                region = wwIdle.getKeyFrame(stateTimer, true);
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

    public void setSprite(State spriteState) {
        this.currentState = spriteState;
    }

    private State getState() {
        if(b2body.getLinearVelocity().y >0 || (b2body.getLinearVelocity().y <0 && previousState == State.JUMPING))
            return State.JUMPING;
        else if(b2body.getLinearVelocity().y <0)
            return State.FALLING;
        else if(b2body.getLinearVelocity().x !=0)
            return State.RUNNING;
        else
            return State.IDLE;
    }

    @Override
    public void defineCharacter(){
        BodyDef bodydefinitionw = new BodyDef();
        bodydefinitionw.position.set(32 / WonderWomanGame.PPM,320/WonderWomanGame.PPM); //
        bodydefinitionw.type = BodyDef.BodyType.DynamicBody;

        b2body = this.world.createBody(bodydefinitionw);// create the body in our world

        FixtureDef fdef = new FixtureDef();//fixture depth
        //        CircleShape shape = new CircleShape();
        //        shape.setRadius(5/WonderWomanGame.PPM);// set radius of the circle

        fdef.filter.categoryBits = WonderWomanGame.WONDER_BIT;//what is this fixture
        fdef.filter.maskBits =
                WonderWomanGame.DEFAULT_BIT |
                        WonderWomanGame.TREASURESMALL_BIT |
                        WonderWomanGame.TREASURELARGE_BIT |
                        WonderWomanGame.HEALTH_BIT |
                        WonderWomanGame.POWERUP_BIT |
                        WonderWomanGame.SPIKE_BIT |
                        WonderWomanGame.ZONEFALL_BIT|
                        WonderWomanGame.ZONEEND_BIT;//// what wonderwoman can collide with
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(5/WonderWomanGame.PPM, 25/WonderWomanGame.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef);

        PolygonShape x_sensor = new PolygonShape();
        x_sensor.setAsBox(6/WonderWomanGame.PPM, 26/WonderWomanGame.PPM);
        fdef.shape = x_sensor;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("body_sensor");
    }
}
