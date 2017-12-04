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
import com.silvia.wonderwomanplatformgame.Characters.Enemies.EnemyCrow;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;
import com.silvia.wonderwomanplatformgame.Characters.Character;
/**
 * Created by silvia on 11/15/2017.
 */

public class EnemyCrowSprite extends CharacterSprite {


    public EnemyCrowSprite.CrowSpriteState currentState;
    public EnemyCrowSprite.CrowSpriteState previousState;
    public Body b2body;
    public static final String crowTexture = "crow.png";
    private int xposition;
    private int yposition;
    private EnemyCrow crow;
    private boolean flyingRight;
    private boolean deadFlag = false;


    private Animation<TextureRegion> crowIdle;
    private Animation<TextureRegion> crowFly;
    private Animation<TextureRegion> crowSwoop;

    private float stateTimer;
    private TextureRegion crow_stand;
    private Array<TextureRegion> crowFrames = new Array<TextureRegion>();

    public Animation<TextureRegion> getCrowIdle() {
        return crowIdle;
    }
    public Animation<TextureRegion> getCrowFly() {
        return crowFly;
    }
    public Animation<TextureRegion> getCrowSwoop() {
        return crowSwoop;
    }
    public enum CrowSpriteState { IDLE, FLY, SWOOP, DEAD };
    public EnemyCrow getCrow(){return crow;}
    public void setSprite(EnemyCrowSprite.CrowSpriteState spriteState) {
        this.currentState = spriteState;
    }

    // constructor
    public EnemyCrowSprite(World world, EnemyCrow crow, int xposition, int yposition) {
        super(world, crowTexture);
        this.world = world;
        this.crow = crow;
        currentState = EnemyCrowSprite.CrowSpriteState.IDLE;
        previousState = EnemyCrowSprite.CrowSpriteState.IDLE;
        stateTimer = 0;

        flyingRight = true;
        this.xposition = xposition;
        this.yposition = yposition;

        this.setupAnimationFrames();

        this.defineCharacter();

        crow_stand = new TextureRegion(getTexture(), 0, 0, 40, 60);// crow
        setBounds(0,0, 40/ WonderWomanGame.PPM, 60 / WonderWomanGame.PPM);

        setRegion(crow_stand);
    }


    private void setupAnimationFrames() {// test in progress need to finish (tedious)
        setupIdleFrames();
        setupFlyingFrames();
        setupSwoopFrames();
    }

    private void setupIdleFrames() {
        // IDLE ANIMATION

        for(int i =0; i <6; i++) {
            crowFrames.add(new TextureRegion(getTexture(), 8 + (i * 43), 105, 43, 70)); //Flying crow and idle (change speed from idle ot flying)
        }

        crowIdle = new Animation(0.2f, crowFrames);
        crowFrames.clear();
    }

    private void setupFlyingFrames() {
        // FLYING ANIMATION
        for(int i =0; i <6; i++) {
            crowFrames.add(new TextureRegion(getTexture(), 8 + (i * 43), 105, 43, 70));
        }

        crowFly = new Animation(0.1f, crowFrames);
        crowFrames.clear();
    }

    private void setupSwoopFrames() {
        // BITE ANIMATION
        for(int i =0; i <4; i++) {
            crowFrames.add(new TextureRegion(getTexture(), 8 + (i * 46), 200, 46, 70));
        }
        for(int i =0; i <2; i++) {
            crowFrames.add(new TextureRegion(getTexture(), 200 + (i * 58), 200, 58, 70));
        }

        crowSwoop = new Animation(0.2f, crowFrames);
        crowFrames.clear();
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
        setRegion(getFrame(dt));

        if (this.b2body.getPosition().y < 3.8)
            b2body.applyForce(.0f, 10.4f, b2body.getWorldCenter().x, b2body.getWorldCenter().y, true);

        if (this.b2body.getPosition().y > 3.8)
            b2body.applyForce(.0f, -1f, b2body.getWorldCenter().x, b2body.getWorldCenter().y, true);

        if(crow.status == Character.CharacterLivingStatus.DEAD && !deadFlag) {
            deadFlag = true;
            System.out.println("crow Dead");
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
            case SWOOP:
                region = crowSwoop.getKeyFrame(stateTimer);
                break;
            case FLY:
                region = crowFly.getKeyFrame(stateTimer, true);
                break;
            case IDLE:
            default:
                region = crowIdle.getKeyFrame(stateTimer, true);
                break;
        }

        if((b2body.getLinearVelocity().x < 0 || !flyingRight) && !region.isFlipX()) {
            region.flip(true, false);
            flyingRight = false;
        } else if ((b2body.getLinearVelocity().x > 0 || flyingRight) && region.isFlipX()) {
            region.flip(true, false);
            flyingRight = true;
        }

        stateTimer = currentState == previousState
                ? stateTimer + dt
                : 0;

        previousState = currentState;

        return region;
    }


    public EnemyCrowSprite.CrowSpriteState getState() {
        if(this.crow.health <= 0) {
            return CrowSpriteState.DEAD;
        }

        if(this.crow.getSwoopTimer() > 0) {
            return CrowSpriteState.SWOOP;
        }

        if(b2body.getLinearVelocity().x !=0)
            return EnemyCrowSprite.CrowSpriteState.FLY;
        else
            return EnemyCrowSprite.CrowSpriteState.IDLE;
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
                        WonderWomanGame.WONDER_BIT; //// what crow can collide with

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(5/WonderWomanGame.PPM, 25/WonderWomanGame.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef);
        b2body.setUserData(this);

        PolygonShape x_sensor = new PolygonShape();
        x_sensor.setAsBox(6/WonderWomanGame.PPM, 26/WonderWomanGame.PPM);
        fdef.shape = x_sensor;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData(this);
    }

    public void onTouch() {
        Gdx.app.log("Enemy crow", "Collision");
    }
}
