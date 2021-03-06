package com.silvia.wonderwomanplatformgame.World.Buildables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.NormalState;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.WonderWomanCharacter;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;
import com.silvia.wonderwomanplatformgame.Characters.Character;

public class ZoneFall extends InteractiveTileObject {
    public ZoneFall(){}
    public ZoneFall(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(WonderWomanGame.ZONEFALL_BIT);
    }

    @Override
    public void onTouch() {
        Gdx.app.log("ZoneFall", "Collision");
        WonderWomanCharacter ww = WonderWomanCharacter.getInstance();
        ww.powerupStatus = new NormalState();
        ww.status = Character.CharacterLivingStatus.DEAD;
    }

    public void build_objects(World world, TiledMap map) {
        for (MapObject object : map.getLayers().get(mapResources.obj_zone_fall).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new ZoneFall(world, map, rect);
        }
    }
}
