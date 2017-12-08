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
import com.silvia.wonderwomanplatformgame.Characters.Enemies.EnemyBig;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;
import com.silvia.wonderwomanplatformgame.Characters.Character;

/**
 * Created by silvia on 11/15/2017.
 */

public class EnemyBigSprite extends CharacterSprite {


    public EnemyBigSprite.BigSpriteState currentState;
    public EnemyBigSprite.BigSpriteState previousState;
    public Body b2body;
    private int xposition;
    private int yposition;
    private EnemyBig big;
    private float stateTimer;
    private boolean runningRight;
    public static final String bigTexture = "wolf.png";
    private boolean deadFlag = false;

    private Animation<TextureRegion> bigIdle;
    private Animation<TextureRegion> bigWalk;
    private Animation<TextureRegion> bigEnlarge;

    private TextureRegion big_stand;
    private TextureRegion big_big_stand;

    private Array<TextureRegion> bigFrames = new Array<TextureRegion>();
    public enum BigSpriteState { IDLE, WALK, ENLARGE, DEAD };

    public Animation<TextureRegion> getBigIdle() {
        return bigIdle;
    }
    public Animation<TextureRegion> getBigWalk() {
        return bigWalk;
    }
    public Animation<TextureRegion> getBigEnlarge() {
        return bigEnlarge;
    }
    public EnemyBig getBig(){return big;}
    // constructor
    public EnemyBigSprite(World world, EnemyBig big, int xposition, int yposition) {
        super(world, bigTexture);
        this.world = world;
        this.big = big;
        currentState = EnemyBigSprite.BigSpriteState.IDLE;
        previousState = EnemyBigSprite.BigSpriteState.IDLE;
        stateTimer = 0;
        runningRight = true;
        this.xposition = xposition;
        this.yposition = yposition;

        this.setupAnimationFrames();
        this.defineCharacter();

        big_stand = new TextureRegion(getTexture(), 0, 0, 60, 48);//
        setBounds(0,0, 60/ WonderWomanGame.PPM, 48 / WonderWomanGame.PPM);
        setRegion(big_stand);
    }

    private void setupAnimationFrames() {// test in progress need to finish (tedious)
        setupIdleFrames();
        setupWalkingFrames();
        setupEnlargeFrames();
    }

    private void setupIdleFrames() {
        // IDLE ANIMATION
        for(int i =0; i <6; i++) {
            bigFrames.add(new TextureRegion(getTexture(), 24 + (i * 86), 401, 86, 65)); //IDLE wolf
        }

        bigIdle = new Animation(0.2f, bigFrames);
        bigFrames.clear();
    }

    private void setupWalkingFrames() {
        // RUNNING ANIMATION
        for(int i =0; i <6; i++) {
            bigFrames.add(new TextureRegion(getTexture(), 24 + (i * 107), 162, 103, 54)); //walk wolf
        }
        bigWalk = new Animation(0.1f, bigFrames);
        bigFrames.clear();
    }

    private void setupEnlargeFrames() {
        // Enlarge ANIMATION
        for(int i =0; i <3; i++) {
            bigFrames.add(new TextureRegion(getTexture(), 24 + (i * 90), 225, 90, 80)); //enlarge wolf
        }

        bigEnlarge = new Animation(0.2f, bigFrames);
        bigFrames.clear();
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
        setRegion(getFrame(dt));

        if(big.status == Character.CharacterLivingStatus.DEAD && !deadFlag) {
            deadFlag = true;
            System.out.println("Big Dead");
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
            case ENLARGE:
                big_big_stand  = new TextureRegion(getTexture(), 0, 0, 80, 64);//
                setBounds(this.getX(),this.getY()+(0.16f), 100/ WonderWomanGame.PPM, 80 / WonderWomanGame.PPM);
                setRegion(big_big_stand);

                region = bigEnlarge.getKeyFrame(stateTimer);
                break;
            case WALK:
                big_stand = new TextureRegion(getTexture(), 0, 0, 60, 48);//
                setBounds(this.getX(),this.getY(), 60/ WonderWomanGame.PPM, 48 / WonderWomanGame.PPM);
                setRegion(big_stand);
                region = bigWalk.getKeyFrame(stateTimer, true);
                break;
            case IDLE:
            default:
                big_stand = new TextureRegion(getTexture(), 0, 0, 60, 48);//
                setBounds(this.getX(),this.getY(), 60/ WonderWomanGame.PPM, 48 / WonderWomanGame.PPM);
                setRegion(big_stand);
                region = bigIdle.getKeyFrame(stateTimer, true);
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


    public BigSpriteState getState() {
        if(this.big.health <= 0) {
            return BigSpriteState.DEAD;
        }

        if(this.big.getEnlargeTimer() > 0) {
            return BigSpriteState.ENLARGE;
        }

        if(b2body.getLinearVelocity().x !=0)
            return EnemyBigSprite.BigSpriteState.WALK;
        else
            return EnemyBigSprite.BigSpriteState.IDLE;
    }

    @Override
    public void defineCharacter() {
        BodyDef bodydefinitionw = new BodyDef();
        bodydefinitionw.position.set(this.xposition / WonderWomanGame.PPM,this.yposition/WonderWomanGame.PPM); //
        bodydefinitionw.type = BodyDef.BodyType.DynamicBody;

        b2body = this.world.createBody(bodydefinitionw);
        FixtureDef fdef = new FixtureDef();//fixture depth

        fdef.filter.categoryBits = WonderWomanGame.ENEMY_BIT;
        fdef.filter.maskBits =
                WonderWomanGame.DEFAULT_BIT |
                        WonderWomanGame.SPIKE_BIT |
                        WonderWomanGame.WONDER_BIT;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(25/WonderWomanGame.PPM, 15/WonderWomanGame.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef);

        PolygonShape shape2 = new PolygonShape();
        shape2.setAsBox(35/WonderWomanGame.PPM, 25/WonderWomanGame.PPM);
        fdef.shape = shape2;
        b2body.createFixture(fdef);
        b2body.setUserData(this);

        PolygonShape x_sensor = new PolygonShape();
        x_sensor.setAsBox(35/WonderWomanGame.PPM, 25/WonderWomanGame.PPM);
        fdef.shape = x_sensor;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData(this);
    }

    public void onTouch() {
        Gdx.app.log("Enemy Big", "Collision");
    }
}
