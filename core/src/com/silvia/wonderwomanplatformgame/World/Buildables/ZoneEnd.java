package com.silvia.wonderwomanplatformgame.World.Buildables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Screens.GameOverScreen;
import com.silvia.wonderwomanplatformgame.Screens.GameScreen;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;

public class ZoneEnd extends InteractiveTileObject {

    private GameScreen game;
    private boolean alreadyTouched;

    public ZoneEnd(){}

    public ZoneEnd(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(WonderWomanGame.ZONEEND_BIT);
        alreadyTouched = false;
    }

    public ZoneEnd(World world, TiledMap map, Rectangle bounds, GameScreen gameScreen) {
        super(world, map, bounds);
        this.game = gameScreen;
        fixture.setUserData(this);
        setCategoryFilter(WonderWomanGame.ZONEEND_BIT);
        alreadyTouched = false;
    }


    @Override
    public void onTouch() {
        Gdx.app.log("ZoneEnd", "Collision");
        if (!alreadyTouched){
            try {
                game.getPointsTracker().updateScoreboard();
                game.getPointsTracker().saveScore();
            } catch (Exception io) {
                System.out.println(io);
            }
            this.game.game.setScreen(new GameOverScreen(this.game.game));
            alreadyTouched = true;
        }
    }

    public void build_objects(World world, TiledMap map) {
        for (MapObject object : map.getLayers().get(mapResources.obj_zone_end).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new ZoneEnd(world, map, rect);
        }
    }

    public void build_objects(World world, TiledMap map, GameScreen gameScreen) {
        for (MapObject object : map.getLayers().get(mapResources.obj_zone_end).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new ZoneEnd(world, map, rect, gameScreen);
        }
    }
}
