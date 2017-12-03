package com.silvia.wonderwomanplatformgame.World.Buildables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.WonderWomanCharacter;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;
import com.silvia.wonderwomanplatformgame.Characters.Character;

public class Health extends InteractiveTileObject {

    public Health() {}

    public Health(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(WonderWomanGame.HEALTH_BIT);
    }

    @Override
    public void onTouch() {
        Gdx.app.log("Health", "Collision");
        if (WonderWomanCharacter.getInstance().health <= 10) {
            WonderWomanCharacter.getInstance().health += 2;
        }
    }

    public void onTouch(Character character) {
        Gdx.app.log("Health", "Collision");
        if (character.health <= 10) {
            character.health += 2;
        }
    }

    public void build_objects(World world, TiledMap map) {
        for (MapObject object : map.getLayers().get(mapResources.obj_health).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Health(world, map, rect);
        }
    }
}
