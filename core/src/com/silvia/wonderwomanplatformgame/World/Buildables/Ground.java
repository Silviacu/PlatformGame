package com.silvia.wonderwomanplatformgame.World.Buildables;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;


public class Ground extends BodyTileObject {
    public void build_objects(World world, TiledMap map) {
        for(MapObject object : map.getLayers().get(mapResources.obj_ground).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bodydefinition.type = BodyDef.BodyType.StaticBody; //dynamic player affected by graphics and physics
            //static body doesn't move can only move programmatically, not affected by forces
            //kinematic cant be affected by forces only velocity(moving platforms)
            bodydefinition.position.set(((rect.getX() + rect.getWidth() /2)/ WonderWomanGame.PPM),(rect.getY() +rect.getHeight()/2) /WonderWomanGame.PPM );
            body = world.createBody(bodydefinition);
            shape.setAsBox(rect.getWidth() /2 / WonderWomanGame.PPM, rect.getHeight() /2 /WonderWomanGame.PPM );
            fdef.shape = shape;
            body.createFixture(fdef);// add fixture to the body
        }
    }
}
