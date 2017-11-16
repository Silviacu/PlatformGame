package com.silvia.wonderwomanplatformgame.World.Buildables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;

public class Spike extends InteractiveTileObject {

    public Spike(World world, TiledMap map, Ellipse bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(WonderWomanGame.SPIKE_BIT);
    }

    @Override
    public void onTouch() {

    }


    public static void build_spikes(World world, TiledMap map) {
        for (MapObject object : map.getLayers().get(mapResources.obj_spikes).getObjects().getByType(EllipseMapObject.class)){
            Ellipse ellipse = ((EllipseMapObject) object).getEllipse();
            new Spike(world, map, ellipse);
        }
    }
}
