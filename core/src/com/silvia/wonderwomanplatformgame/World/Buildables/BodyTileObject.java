package com.silvia.wonderwomanplatformgame.World.Buildables;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.silvia.wonderwomanplatformgame.World.MapResources;


public abstract class BodyTileObject {
    static BodyDef bodydefinition = new BodyDef();
    static PolygonShape shape = new PolygonShape();// for our fixture
    static FixtureDef fdef = new FixtureDef();//fixture depth, to define and then add to body
    static Body body;
    static MapResources mapResources = new MapResources();
}
