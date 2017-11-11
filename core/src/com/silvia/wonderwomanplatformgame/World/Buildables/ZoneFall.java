package com.silvia.wonderwomanplatformgame.World.Buildables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;

public class ZoneFall extends InteractiveTileObject {

    public ZoneFall(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(WonderWomanGame.ZONEFALL_BIT);
    }

    @Override
    public void onTouch() {
        Gdx.app.log("ZoneFall", "Collision");
    }

    public static void build_fall_zones(World world, TiledMap map) {
        for (MapObject object : map.getLayers().get(mapResources.obj_zone_fall).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new ZoneFall(world, map, rect);
        }
    }
}
