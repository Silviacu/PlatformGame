package com.silvia.wonderwomanplatformgame.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;

import static com.silvia.wonderwomanplatformgame.WonderWomanGame.PPM;

/**
 * Created by silvia on 11/15/2017.
 */

public class CharacterSprite extends Sprite {
    public World world;
    public Body b2body;
    private Texture characterTexture;
    private float stateTimer;

    // constructor
    public CharacterSprite() {
    }

    public CharacterSprite(World world, String texture){
        super(new Texture(texture));
    }

    public void defineCharacter(){
        BodyDef bodydefinitionw = new BodyDef();
        bodydefinitionw.position.set(32 / WonderWomanGame.PPM,320/WonderWomanGame.PPM); //
        bodydefinitionw.type = BodyDef.BodyType.DynamicBody;

        b2body = world.createBody(bodydefinitionw);// create the body in our world

        FixtureDef fdef = new FixtureDef();//fixture depth
        //        CircleShape shape = new CircleShape();
        //        shape.setRadius(5/WonderWomanGame.PPM);// set radius of the circle
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

    public CharacterDefinition calcDefineCharacter(double x, double y, double hitboxX, double hitboxY){
        return new CharacterDefinition(x, y, hitboxX, hitboxY);
    }
    public class CharacterDefinition {
        double startX;
        double startY;
        double hitboxX;
        double hitboxY;

        public CharacterDefinition(double x, double y, double hitboxX, double hitboxY) {
            this.startX = x / WonderWomanGame.PPM;
            this.startY = y / WonderWomanGame.PPM;
            this.hitboxX = hitboxX / WonderWomanGame.PPM;
            this.hitboxY = hitboxY / WonderWomanGame.PPM;
        }
    }

}
