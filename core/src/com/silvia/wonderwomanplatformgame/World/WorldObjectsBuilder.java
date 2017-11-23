package com.silvia.wonderwomanplatformgame.World;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.PointsTracker.PointsTracker;

import com.silvia.wonderwomanplatformgame.Screens.GameScreen;
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
        Ground.build_ground(world, map);

        // Treasure_1 Bodies
        Treasure_Sm.build_treasure_sm(world, map, pt);

        // Treasure_2 Bodies
        Treasure_Lg.build_treasure_lg(world, map, pt);

        // Breakable Bodies
        Breakable.build_breakables(world, map, pt);

        // Spike Bodies
        Spike.build_spikes(world, map);

        // Powerup Bodies
        Powerup.build_powerups(world, map);

        // Zone_Fall Bodies
        ZoneFall.build_fall_zones(world, map);

        // Health Bodies
        Health.build_health_packs(world, map);

        // Zone_End Bodies
        ZoneEnd.build_end_zones(world, map);
    }

    public WorldObjectsBuilder(World world, TiledMap map, GameScreen screen) {
        // GROUND
        Ground.build_ground(world, map);

        // Treasure_1 Bodies
        Treasure_Sm.build_treasure_sm(world, map, screen.getPointsTracker());

        // Treasure_2 Bodies
        Treasure_Lg.build_treasure_lg(world, map, screen.getPointsTracker());

        // Breakable Bodies
        Breakable.build_breakables(world, map, screen.getPointsTracker());

        // Spike Bodies
        Spike.build_spikes(world, map);

        // Powerup Bodies
        Powerup.build_powerups(world, map);

        // Zone_Fall Bodies
        ZoneFall.build_fall_zones(world, map);

        // Health Bodies
        Health.build_health_packs(world, map);

        // Zone_End Bodies
        ZoneEnd.build_end_zones(world, map);
    }
}
