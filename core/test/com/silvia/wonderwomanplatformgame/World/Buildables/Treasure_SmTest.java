package com.silvia.wonderwomanplatformgame.World.Buildables;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.AttackPowerupState;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.NormalState;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.WonderWomanCharacter;
import com.silvia.wonderwomanplatformgame.GameTest;
import com.silvia.wonderwomanplatformgame.PointsTracker.PointsTracker;
import com.silvia.wonderwomanplatformgame.World.MapOne;
import com.silvia.wonderwomanplatformgame.World.MapResources;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 12/1/2017.
 */
public class Treasure_SmTest extends GameTest {

    @Test
    public void build_Treasure_Sm_Test() throws Exception {
        World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(MapOne.mapFilePath); // Assume if World One is Loaded
        PointsTracker pt = new PointsTracker();

        int numberTreasure_sm = 0;

        for (MapObject object : map.getLayers().get(MapResources.obj_treasure_sm).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            assertNotNull(new Treasure_Sm(world, map, rect, pt));
            numberTreasure_sm++;
        }

        assertEquals(1, numberTreasure_sm);
    }

    @Test
    public void onTouch() throws Exception {
        World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(MapOne.mapFilePath); // Assume if World One is Loaded
        PointsTracker pt = new PointsTracker();
        Rectangle dRect = new Rectangle();
        Treasure_Sm b = new Treasure_Sm(world, map, dRect, pt);

        int numberTreasure_sm = 0;

        for (MapObject object : map.getLayers().get(MapResources.obj_treasure_sm).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            assertNotNull(new Treasure_Sm(world, map, rect, pt));
            if(numberTreasure_sm == 0)
                b = new Treasure_Sm(world, map, rect, pt);
            numberTreasure_sm++;
        }

        assertEquals(0, pt.getScore());

        b.onTouch();
        assertEquals(100, pt.getScore());

        b.onTouch();// should still be the same number because after first touch it gets destroyed
        assertEquals(100, pt.getScore());

        WonderWomanCharacter ww = WonderWomanCharacter.getInstance();

        ww.init(world);

        assertEquals(new NormalState().getStateName(), ww.powerupStatus.getStateName());

        Treasure_Sm treasure_sm = new Treasure_Sm(world, map, new Rectangle(), pt);
        treasure_sm.onTouch();

        assertEquals(new AttackPowerupState().getStateName(), ww.powerupStatus.getStateName());
    }

}