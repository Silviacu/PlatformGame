package com.silvia.wonderwomanplatformgame.World.Buildables;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import com.silvia.wonderwomanplatformgame.PointsTracker.PointsTracker;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;
import com.silvia.wonderwomanplatformgame.World.MapResources;


public abstract class InteractiveTileObject extends BodyTileObject {
    protected Fixture fixture;
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;

    public InteractiveTileObject() {
    }
    /*
    creates a fixture and a body and assigness a category to each fixture
   */
    public InteractiveTileObject(World world, TiledMap map, Rectangle bounds) {
        this.world = world;
        this.map = map;
        this.bounds = bounds;

        bodydefinition.type = BodyDef.BodyType.StaticBody;
        bodydefinition.position.set((bounds.getX() + bounds.getWidth() / 2) / WonderWomanGame.PPM, (bounds.getY() + bounds.getHeight() / 2) / WonderWomanGame.PPM);
        body = world.createBody(bodydefinition);
        shape.setAsBox(bounds.getWidth() / 2 / WonderWomanGame.PPM, bounds.getHeight() / 2 / WonderWomanGame.PPM);
        fdef.shape = shape;

        fdef.isSensor = true;

        fixture = body.createFixture(fdef);
    }

    /*
    creates a fixture and a body and assignes a category to each fixture
   */
    public InteractiveTileObject(World world, TiledMap map, Ellipse ellipse) {
        this.world = world;
        this.map = map;

        bodydefinition.type = BodyDef.BodyType.StaticBody;
        bodydefinition.position.set((ellipse.x + ellipse.width / 2) / WonderWomanGame.PPM, (ellipse.y + ellipse.height / 2) / WonderWomanGame.PPM);

        body = world.createBody(bodydefinition);
        shape.setAsBox(ellipse.width / 2 / WonderWomanGame.PPM, ellipse.height / 2 / WonderWomanGame.PPM);
//        shape.setRadius(ellipse.width/2);
        fdef.shape = shape;
        fdef.isSensor = true;
        fixture = body.createFixture(fdef);
    }


    public abstract void onTouch();

    public void setCategoryFilter(short filterBit) {
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell() {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(7);
        return layer.getCell((int) (body.getPosition().x * WonderWomanGame.PPM / 16), (int) (body.getPosition().y * WonderWomanGame.PPM / 16));
    }

    @Override
    public void build_objects(World world, TiledMap map) {

    }

    public void build_objects(World world, TiledMap map, PointsTracker pt) {

    }
}