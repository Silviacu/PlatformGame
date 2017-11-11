package com.silvia.wonderwomanplatformgame.World;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.PointsTracker.PointsTracker;

import com.silvia.wonderwomanplatformgame.World.Buildables.Ground;
import com.silvia.wonderwomanplatformgame.World.Buildables.Health;
import com.silvia.wonderwomanplatformgame.World.Buildables.Powerup;
import com.silvia.wonderwomanplatformgame.World.Buildables.Spike;

import com.silvia.wonderwomanplatformgame.World.Buildables.ZoneEnd;
import com.silvia.wonderwomanplatformgame.World.Buildables.ZoneFall;


public class WorldObjectsBuilder {
    public WorldObjectsBuilder(World world, TiledMap map, PointsTracker pt) {
        // GROUND
        Ground.build_ground(world, map);

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
