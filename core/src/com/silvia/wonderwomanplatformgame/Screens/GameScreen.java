package com.silvia.wonderwomanplatformgame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;
import com.silvia.wonderwomanplatformgame.World.MapOne;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

/**
 * Created by silvia on 11/10/2017.
 */

public class GameScreen implements Screen{

    private WonderWomanGame game;
    private OrthographicCamera gamecamera; //what follows along in our game came
    private Viewport gamePort;
    private TmxMapLoader maploader;//loaded map into our world
    private TiledMap map; //reference to the map
    private OrthogonalTiledMapRenderer renderer; // rendered our map to the screen
    //Box2D variables
    private World world;//
    private Box2DDebugRenderer b2dr; //givs up graphiccal representation of our fixtures in our world

    //making a constructor because sending game to screen
    public GameScreen(WonderWomanGame game){
        this.game = game;
        gamecamera = new OrthographicCamera();
        gamePort = new FitViewport(WonderWomanGame.virtualwidth/ WonderWomanGame.PPM,WonderWomanGame.virtualheight/WonderWomanGame.PPM, gamecamera);

        setGameMap(MapOne.mapFilePath);
        setGameCamera();
        setGameWorld();
    }

    private void setGameMap(String mapPath) {
        maploader = new TmxMapLoader();
        map = maploader.load(mapPath);
    }

    private void setGameCamera() {
        renderer = new OrthogonalTiledMapRenderer(map, 1/WonderWomanGame.PPM);
        gamecamera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight()/2 , 0);
    }

    private void setGameWorld() {
        world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        b2dr = new Box2DDebugRenderer();//
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            gamecamera.position.x += 1;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            gamecamera.position.x -= 1;
        }
    }


    public void update(float dt){//updating of gameworld
        //check for key and mouse clicking
        handleInput(dt);//deleta time

        world.step(1/60f, 6, 2); // time stamp(60 times a second, velocity, position

        gamecamera.update();
        renderer.setView(gamecamera); //render what our game cam can see
    }

    @Override
    public void render(float delta) {
        update(delta); //
        // clear the screen
        Gdx.gl.glClearColor(0,0,0,1);// clear the color
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);//clear the screen
        //game batch to reconginze where our camera is in our game world
        // only render what pur camera can see

        renderer.render();// after game clears there needs to render

        //render all the objects inside the game for the fictures and bodies
        b2dr.render(world, gamecamera.combined);

        game.batch.setProjectionMatrix(gamecamera.combined);
        game.batch.begin();
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        //hud.dispose();
    }
}


