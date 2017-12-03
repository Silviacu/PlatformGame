package com.silvia.wonderwomanplatformgame.World.Buildables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.JumpPowerupState;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.WonderWomanCharacter;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;


public class Powerup extends InteractiveTileObject {

    public Powerup(){
    }

    public Powerup(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(WonderWomanGame.HEALTH_BIT);
    }

    @Override
    public void onTouch() {
        Gdx.app.log("Powerup", "Collision");
        WonderWomanCharacter ww = WonderWomanCharacter.getInstance();
        ww.powerupStatus = new JumpPowerupState();
//        ww.powerupStatus = new AttackPowerupState();
    }

    public void build_objects(World world, TiledMap map) {
        for (MapObject object : map.getLayers().get(mapResources.obj_powerups).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Powerup(world, map, rect);
        }
    }
}
