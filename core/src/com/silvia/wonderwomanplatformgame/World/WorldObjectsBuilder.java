package com.silvia.wonderwomanplatformgame.World;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.PointsTracker.PointsTracker;

import com.silvia.wonderwomanplatformgame.Screens.GameScreen;
import com.silvia.wonderwomanplatformgame.World.Buildables.BodyTileObject;
import com.silvia.wonderwomanplatformgame.World.Buildables.Breakable;
import com.silvia.wonderwomanplatformgame.World.Buildables.Ground;
import com.silvia.wonderwomanplatformgame.World.Buildables.Health;
import com.silvia.wonderwomanplatformgame.World.Buildables.Powerup;
import com.silvia.wonderwomanplatformgame.World.Buildables.Spike;

import com.silvia.wonderwomanplatformgame.World.Buildables.Treasure_Lg;
import com.silvia.wonderwomanplatformgame.World.Buildables.Treasure_Sm;
import com.silvia.wonderwomanplatformgame.World.Buildables.ZoneEnd;
import com.silvia.wonderwomanplatformgame.World.Buildables.ZoneFall;


public class WorldObjectsBuilder {
    public WorldObjectsBuilder(World world, TiledMap map, PointsTracker pt) {
        // GROUND
        BodyTileObject ground = new Ground();
        ground.build_objects(world, map);

        // Treasure_1 Bodies
        BodyTileObject treasure_sm = new Treasure_Sm();
        ((Treasure_Sm) treasure_sm).build_objects(world, map, pt);

        // Treasure_2 Bodies
        BodyTileObject treasure_lg = new Treasure_Lg();
        ((Treasure_Lg) treasure_lg).build_objects(world, map, pt);

        // Breakable Bodies
        BodyTileObject breakables = new Breakable();
        ((Breakable) breakables).build_objects(world, map, pt);

        // Spike Bodies
        BodyTileObject spikes = new Spike();
        ((Spike) spikes).build_objects(world, map);

        // Powerup Bodies
        BodyTileObject powerups = new Powerup();
        ((Powerup) powerups).build_objects(world, map);

        // Zone_Fall Bodies
        BodyTileObject zonefall = new ZoneFall();
        ((ZoneFall) zonefall).build_objects(world, map);

        // Health Bodies
        BodyTileObject healthpots = new Health();
        ((Health) healthpots).build_objects(world, map);

        // Zone_End Bodies
        BodyTileObject zoneend = new ZoneEnd();
        ((ZoneEnd) zoneend).build_objects(world, map);
    }

    // the constructor used by gamescreen
    public WorldObjectsBuilder(World world, TiledMap map, GameScreen screen) {
        // GROUND
        BodyTileObject ground = new Ground();
        ground.build_objects(world, map);

        // Treasure_1 Bodies
        BodyTileObject treasure_sm = new Treasure_Sm();
        ((Treasure_Sm) treasure_sm).build_objects(world, map, screen.getPointsTracker());

        // Treasure_2 Bodies
        BodyTileObject treasure_lg = new Treasure_Lg();
        ((Treasure_Lg) treasure_lg).build_objects(world, map, screen.getPointsTracker());

        // Breakable Bodies
        BodyTileObject breakables = new Breakable();
        ((Breakable) breakables).build_objects(world, map, screen.getPointsTracker());

        // Spike Bodies
        BodyTileObject spikes = new Spike();
        ((Spike) spikes).build_objects(world, map);

        // Powerup Bodies
        BodyTileObject powerups = new Powerup();
        ((Powerup) powerups).build_objects(world, map);

        // Zone_Fall Bodies
        BodyTileObject zonefall = new ZoneFall();
        ((ZoneFall) zonefall).build_objects(world, map);

        // Health Bodies
        BodyTileObject healthpots = new Health();
        ((Health) healthpots).build_objects(world, map);

        // Zone_End Bodies
        BodyTileObject zoneend = new ZoneEnd();
        ((ZoneEnd) zoneend).build_objects(world, map, screen);
    }
}
