package com.silvia.wonderwomanplatformgame.World;


import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Characters.Enemies.EnemyBig;
import com.silvia.wonderwomanplatformgame.Characters.Enemies.EnemyCrow;
import com.silvia.wonderwomanplatformgame.Characters.Enemies.EnemyZombie;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.WonderWomanCharacter;
import com.silvia.wonderwomanplatformgame.GameTest;
import com.silvia.wonderwomanplatformgame.PointsTracker.PointsTracker;
import com.silvia.wonderwomanplatformgame.World.Buildables.Breakable;
import com.silvia.wonderwomanplatformgame.World.Buildables.Health;
import com.silvia.wonderwomanplatformgame.World.Buildables.Spike;
import com.silvia.wonderwomanplatformgame.World.Buildables.Treasure_Lg;
import com.silvia.wonderwomanplatformgame.World.Buildables.Treasure_Sm;
import com.silvia.wonderwomanplatformgame.World.Buildables.ZoneFall;
import com.silvia.wonderwomanplatformgame.Characters.Character;


import org.junit.Test;


import static com.silvia.wonderwomanplatformgame.Characters.Character.CharacterLivingStatus.ALIVE;
import static org.junit.Assert.*;

/**
 * Created by silvia on 12/3/2017.
 */
public class WorldObjectCollisionListenerTest extends GameTest {

    @Test
    public void characterTouchesHealth() {

        WonderWomanCharacter.getInstance().health = 10;
        assertEquals(WonderWomanCharacter.getInstance().health, 10, 0.1);

        World world = new World(new Vector2(0, -10), true);// gravity, none for now, sleep objects at rest
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(MapOne.mapFilePath); // Assume if World One is Loaded
        Health potion = null;

        int i = 0;
        for (MapObject object : map.getLayers().get(MapResources.obj_health).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            if (i == 0)
                potion = new Health(world, map, rect);
        }

        potion.onTouch();
        assertEquals(12, WonderWomanCharacter.getInstance().health, 0.1);
    }

    @Test
    public void characterTouchesBreakable() {
        World world = new World(new Vector2(0, -10), true);// gravity, none for now, sleep objects at rest
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(MapOne.mapFilePath); // Assume if World One is Loaded
        PointsTracker pt = new PointsTracker();
        Breakable b = new Breakable(pt);

        int numberOfBreakables = 0;

        for (MapObject object : map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            assertNotNull(new Breakable(world, map, rect, pt));
            if (numberOfBreakables == 0)
                b = new Breakable(world, map, rect, pt);
            numberOfBreakables++;
        }

        assertEquals(0, pt.getScore());

        b.testOnTouch();
        assertEquals(200, pt.getScore());

        b.testOnTouch();
        assertEquals(200, pt.getScore());
    }

    @Test
    public void characterTouchesTreasureLg() {
        World world = new World(new Vector2(0, -10), true);// gravity, none for now, sleep objects at rest
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(MapOne.mapFilePath); // Assume if World One is Loaded
        PointsTracker pt = new PointsTracker();
        Treasure_Lg b = new Treasure_Lg(pt);

        int numberTreasure_lg = 0;

        for (MapObject object : map.getLayers().get(MapResources.obj_treasure_lg).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            assertNotNull(new Treasure_Lg(world, map, rect, pt));
            if (numberTreasure_lg == 0)
                b = new Treasure_Lg(world, map, rect, pt);
            numberTreasure_lg++;
        }

        assertEquals(0, pt.getScore());

        b.testOnTouch();
        assertEquals(200, pt.getScore());

        b.testOnTouch();
        assertEquals(200, pt.getScore());
    }

    @Test
    public void characterTouchesTreasureSm() {
        World world = new World(new Vector2(0, -10), true);// gravity, none for now, sleep objects at rest
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(MapOne.mapFilePath); // Assume if World One is Loaded
        PointsTracker pt = new PointsTracker();
        Treasure_Sm b = new Treasure_Sm(pt);

        int numberTreasure_sm = 0;

        for (MapObject object : map.getLayers().get(MapResources.obj_treasure_sm).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            assertNotNull(new Treasure_Sm(world, map, rect, pt));
            if (numberTreasure_sm == 0)
                b = new Treasure_Sm(world, map, rect, pt);
            numberTreasure_sm++;
        }
    }

    @Test
    public void dealContactDamageBig() throws Exception {
        EnemyBig big = new EnemyBig();
        WonderWomanCharacter.getInstance().health = 10;

        assertEquals(10, WonderWomanCharacter.getInstance().health, 0.1);
        big.dealContactDamage(WonderWomanCharacter.getInstance());
        assertEquals(9, WonderWomanCharacter.getInstance().health, 0.1);
    }

    @Test
    public void dealContactDamageCrow() throws Exception {

        EnemyCrow crow = new EnemyCrow();

        WonderWomanCharacter.getInstance().health = 10;

        assertEquals(10, WonderWomanCharacter.getInstance().health, 0.1);
        crow.dealContactDamage(WonderWomanCharacter.getInstance());
        assertEquals(9, WonderWomanCharacter.getInstance().health, 0.1);
    }

    @Test
    public void dealContactDamage() throws Exception {

        EnemyZombie zombie = new EnemyZombie();
        WonderWomanCharacter.getInstance().health = 10;
        assertEquals(10, WonderWomanCharacter.getInstance().health, 0.1);
        zombie.dealContactDamage(WonderWomanCharacter.getInstance());
        assertEquals(9, WonderWomanCharacter.getInstance().health, 0.1);
    }

    public void ontouch_spikesTest() throws Exception {
        World world = new World(new Vector2(0, -10), true);
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(MapOne.mapFilePath);

        WonderWomanCharacter.getInstance().status = ALIVE;

        Rectangle rectangle =
                ((RectangleMapObject) map
                        .getLayers()
                        .get(MapResources.obj_zone_fall)
                        .getObjects()
                        .getByType(RectangleMapObject.class)
                        .get(0)).getRectangle();

        ZoneFall zonefall = new ZoneFall(world, map, rectangle);

        assertEquals(WonderWomanCharacter.getInstance().status, Character.CharacterLivingStatus.ALIVE);
        zonefall.onTouch();
        assertEquals(WonderWomanCharacter.getInstance().status, Character.CharacterLivingStatus.DEAD);

    }

    public void ontouch_zoneFallTest() throws Exception {
        World world = new World(new Vector2(0, -10), true);// gravity, none for now, sleep objects at rest
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(MapOne.mapFilePath); // Assume if World One is Loaded

        WonderWomanCharacter.getInstance().status = ALIVE;

        Ellipse ellipse =
                ((EllipseMapObject) map
                        .getLayers()
                        .get(MapResources.obj_spikes)
                        .getObjects()
                        .getByType(EllipseMapObject.class)
                        .get(0)).getEllipse();

        Spike spike = new Spike(world, map, ellipse);

        assertEquals(WonderWomanCharacter.getInstance().status, Character.CharacterLivingStatus.ALIVE);
        spike.onTouch();
        assertEquals(WonderWomanCharacter.getInstance().status, Character.CharacterLivingStatus.DEAD);

    }
}
