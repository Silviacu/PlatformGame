package com.silvia.wonderwomanplatformgame.World.Buildables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.NormalState;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.WonderWomanCharacter;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;
import com.silvia.wonderwomanplatformgame.Characters.Character;

public class Spike extends InteractiveTileObject {

    public Spike(World world, TiledMap map, Ellipse bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(WonderWomanGame.SPIKE_BIT);
    }

    @Override
    public void onTouch() {
        Gdx.app.log("Spikes", "Collision");
        WonderWomanCharacter ww = WonderWomanCharacter.getInstance();
        ww.powerupStatus = new NormalState();
        ww.status = Character.CharacterLivingStatus.DEAD;
    }


    public static void build_spikes(World world, TiledMap map) {
        for (MapObject object : map.getLayers().get(mapResources.obj_spikes).getObjects().getByType(EllipseMapObject.class)){
            Ellipse ellipse = ((EllipseMapObject) object).getEllipse();
            new Spike(world, map, ellipse);
        }
    }
}
