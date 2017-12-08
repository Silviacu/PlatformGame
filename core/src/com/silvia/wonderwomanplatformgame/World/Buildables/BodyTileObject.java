package com.silvia.wonderwomanplatformgame.World.Buildables;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;
import com.silvia.wonderwomanplatformgame.World.MapResources;


public abstract class BodyTileObject {
    static BodyDef bodydefinition = new BodyDef();
    static PolygonShape shape = new PolygonShape();// for the fixture
    static FixtureDef fdef = new FixtureDef();//fixture depth, to define and then add to body
    static Body body;
    static MapResources mapResources = new MapResources();

    public void build_objects(World world, TiledMap map) {
        for(MapObject object : map.getLayers().get(mapResources.obj_ground).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bodydefinition.type = BodyDef.BodyType.StaticBody; //dynamic player affected by graphics and physics
            //static body doesn't move can only move programmatically, not affected by forces
            //kinematic cant be affected by forces only velocity(moving platforms)
            bodydefinition.position.set(((rect.getX() + rect.getWidth() /2)/ WonderWomanGame.PPM),(rect.getY() +rect.getHeight()/2) /WonderWomanGame.PPM );
            // body definition is the position and type
            body = world.createBody(bodydefinition);
            shape.setAsBox(rect.getWidth() /2 / WonderWomanGame.PPM, rect.getHeight() /2 /WonderWomanGame.PPM );
            fdef.shape = shape;
            body.createFixture(fdef);// add fixture to the body
        }
    }
}
