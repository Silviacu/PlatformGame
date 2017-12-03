package com.silvia.wonderwomanplatformgame.World.Buildables;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.GameTest;

import com.silvia.wonderwomanplatformgame.WonderWomanGame;
import com.silvia.wonderwomanplatformgame.World.MapOne;

import org.junit.Test;

import static com.silvia.wonderwomanplatformgame.World.Buildables.BodyTileObject.body;
import static com.silvia.wonderwomanplatformgame.World.Buildables.BodyTileObject.bodydefinition;
import static com.silvia.wonderwomanplatformgame.World.Buildables.BodyTileObject.fdef;
import static com.silvia.wonderwomanplatformgame.World.Buildables.BodyTileObject.mapResources;
import static com.silvia.wonderwomanplatformgame.World.Buildables.BodyTileObject.shape;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GroundTest extends GameTest{
    @Test
    public void testGround() {
        World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(MapOne.mapFilePath); // Assume if World One is Loaded
        new Ground().build_objects(world, map);

        int numberOfGroundObjects = 0;

        for(MapObject object : map.getLayers().get(mapResources.obj_ground).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bodydefinition.type = BodyDef.BodyType.StaticBody; //dynamic player affected by graphics and phsycs
            //staticbody doesnt move can only move programmly, not affected by forces
            //kinematic canf be afftect by forces only velocity(moving platofrms)
            bodydefinition.position.set(((rect.getX() + rect.getWidth() /2)/ WonderWomanGame.PPM),(rect.getY() +rect.getHeight()/2) /WonderWomanGame.PPM );
            body = world.createBody(bodydefinition);
            shape.setAsBox(rect.getWidth() /2 / WonderWomanGame.PPM, rect.getHeight() /2 /WonderWomanGame.PPM );
            fdef.shape = shape;
            assertNotNull(body.createFixture(fdef));// add ficture to the body
            numberOfGroundObjects++;
        }

        assertEquals(8, numberOfGroundObjects);
    }
}