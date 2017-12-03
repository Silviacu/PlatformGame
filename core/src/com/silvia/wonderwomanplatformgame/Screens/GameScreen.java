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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.silvia.wonderwomanplatformgame.Characters.Character;
import com.silvia.wonderwomanplatformgame.Characters.Enemies.EnemyBig;
import com.silvia.wonderwomanplatformgame.Characters.Enemies.EnemyCrow;
import com.silvia.wonderwomanplatformgame.Characters.Enemies.EnemyZombie;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.WonderWomanCharacter;
import com.silvia.wonderwomanplatformgame.HUDs.DeadHUD;
import com.silvia.wonderwomanplatformgame.HUDs.PauseHUD;
import com.silvia.wonderwomanplatformgame.Sprites.WonderWomanSprite;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;
import com.silvia.wonderwomanplatformgame.PointsTracker.PointsTracker;
import com.silvia.wonderwomanplatformgame.HUDs.PlayHUD;
import com.silvia.wonderwomanplatformgame.World.MapOne;
import com.silvia.wonderwomanplatformgame.World.WorldObjectsBuilder;
import com.silvia.wonderwomanplatformgame.World.WorldObjectCollisionListener;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
/**
 * Created by silvia on 11/23/2017.
 */
public class GameScreen implements Screen{
    public WonderWomanGame game;

    private OrthographicCamera gamecamera; //what follows along in our game came
    private Viewport gamePort;
    private PointsTracker pointsTracker;
    private PlayHUD hud;
    private DeadHUD deadHud;
    private PauseHUD pauseHUD;

    private TmxMapLoader maploader;//loaded map into our world
    private TiledMap map; //reference to the map
    private OrthogonalTiledMapRenderer renderer; // rendered our map to the screen

    //Box2D variables
    private World world;//
    private Box2DDebugRenderer b2dr; //givs up graphiccal representation of our fixtures in our world

    public enum HudState {
        PLAY,
        PAUSE,
        DEAD
    }

    public HudState hudState;


    public Character player1,
            zombie1,
            big2,
            crow1;


    //making a constructor because sending game to screen
    public GameScreen(WonderWomanGame game){
        this.game = game;
        this.hudState = HudState.PLAY;
        gamecamera = new OrthographicCamera();
        gamePort = new FitViewport(WonderWomanGame.virtualwidth/ WonderWomanGame.PPM,WonderWomanGame.virtualheight/WonderWomanGame.PPM, gamecamera);

        pointsTracker = new PointsTracker();
        pointsTracker.setScoreboardFromFile();

        hud = new PlayHUD(game.batch, this);
        deadHud = new DeadHUD(game.batch, this);
        pauseHUD = new PauseHUD(game.batch, this);

        setGameMap(MapOne.mapFilePath);
        setGameCamera();
        setGameWorld();

        player1 = WonderWomanCharacter.getInstance();
        ((WonderWomanCharacter)player1).init(world);

        zombie1 = new EnemyZombie("Zombie 1", 1, world, 500, 500);
        crow1 = new EnemyCrow("Crow 1", 1, world, 1500, 300);
        big2 = new EnemyBig("Big 2", 1, world, 2300, 500);


        world.setContactListener(new WorldObjectCollisionListener(this));
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
        new WorldObjectsBuilder(world, map, this);
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if(Gdx.input.isKeyJustPressed(Input.Keys.K)) {
            System.out.println("Dealing 10k Damage to Player 1!");
            ((WonderWomanCharacter)player1).receiveDamage(10000);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            this.dispose();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            // If the HUD State is NOT DEAD (PLAY)
            if (this.hudState == HudState.PLAY && this.hudState != HudState.PAUSE) {
                this.hud.pausePressed();
                this.hudState = HudState.DEAD;
                this.deadHud.showDeadHud();
                // OTHERWISE... if it's DEAD...
            } else if (this.hudState == HudState.DEAD) {
                this.hudState = HudState.PLAY;
                this.hud.resume();
                this.deadHud.hideDeadHud();
            }
        }

        // If P is pressed, pause the game!
        if(Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            game.setScreen(new MainMenuScreen(game));
        }

        // If P is pressed, pause the game!
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            // If the HUD State is NOT DEAD, and is PLAY, then PAUSE
            if (this.hudState == HudState.PLAY && this.hudState != HudState.DEAD) {
                this.hud.pausePressed();
                this.pauseHUD.bringUpPauseHUD();
                // OTHERWISE... if it's PAUSE...
            } else if (this.hudState == HudState.PAUSE) {
                this.hud.resume();
            }
        }

        // Character Movement keys for if the game is playing
        if (hudState == HudState.PLAY) {
            if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                ((WonderWomanCharacter)player1).jump();
            }
            //want to know if they are holding it down thats why its not just,  and want to check that the person is not going a faster speed
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && ((WonderWomanSprite)((WonderWomanCharacter)player1).characterSprite).b2body.getLinearVelocity().x <= 2) {
                ((WonderWomanCharacter)player1).walkRight();
            }
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && ((WonderWomanSprite)((WonderWomanCharacter)player1).characterSprite).b2body.getLinearVelocity().x >= -2) {
                ((WonderWomanCharacter)player1).walkLeft();
            }
            if(Gdx.input.isKeyPressed(Input.Keys.J)) {
                ((WonderWomanCharacter)player1).punch();
            }
            if(Gdx.input.isKeyPressed(Input.Keys.H )) {
                ((WonderWomanCharacter)player1).kick();
            }
        }
    }

    public void playIsPaused() {
        for (Actor a : hud.stage.getActors()) {
            a.setVisible(true);
        }
        this.hudState = HudState.PAUSE;
    }

    public void unpause() {
        for (Actor a : pauseHUD.stage.getActors()) {
            a.setVisible(false);
        }
        this.hudState = HudState.PLAY;
    }

    public void playResumed() {
        for (Actor a : hud.stage.getActors()) {
            a.setVisible(true);
        }
        this.hudState = HudState.PLAY;
    }

    public PointsTracker getPointsTracker() {return this.pointsTracker;}

    public void exit() {
        this.dispose();
    }

    public void update(float dt){//updating of gameworld
        //check for key and mouse clicking
        handleInput(dt);//deleta time

        //in order for box2d to execture our physics simulation we need to tell it to
        //calcaltute our update per second

        if (this.hudState == HudState.PLAY) {
            world.step(1/60f, 6, 2); // time stamp(60 times a second, velocity, position
            ((WonderWomanSprite)((WonderWomanCharacter)player1).characterSprite).update(dt);
            ((EnemyZombie)zombie1).update(dt);
            // big1.update(dt);
            ((EnemyBig)big2).update(dt);
            ((EnemyCrow)crow1).update(dt);
            hud.update(dt, pointsTracker.getScore());
        }

        // update camera at ever iteration of game cycle

        deadHud.update(dt);
        pauseHUD.update(dt);

        if (((WonderWomanCharacter)player1).status == Character.CharacterLivingStatus.DEAD) {
            this.hud.pausePressed();
            this.hudState = HudState.DEAD;
            this.deadHud.showDeadHud();
        }

        // everytime our character moves we want to track him with our gamecam
        // gamecamera.position.x = ((WonderWomanCharacter.player1characterSprite.b2body.getPosition().x;
        gamecamera.position.x = ((WonderWomanCharacter)player1).getXPosition();
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
//        b2dr.render(world, gamecamera.combined);

        game.batch.setProjectionMatrix(gamecamera.combined);
        game.batch.begin();
        ((WonderWomanSprite)((WonderWomanCharacter)player1).characterSprite).draw(game.batch);
        ((EnemyZombie)zombie1).characterSprite.draw(game.batch);
        ((EnemyCrow)crow1).characterSprite.draw(game.batch);
        //big1.bigSprite.draw(game.batch);
        ((EnemyBig)big2).characterSprite.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        game.batch.setProjectionMatrix(deadHud.stage.getCamera().combined);
        game.batch.setProjectionMatrix(pauseHUD.stage.getCamera().combined);
        if (this.hudState == HudState.PLAY) {
            hud.stage.draw();
            for (Actor a : hud.stage.getActors()) {
                a.setVisible(true);
            }
            for (Actor a : deadHud.stage.getActors()) {
                a.setVisible(false);
            }
            for (Actor a : pauseHUD.stage.getActors()) {
                a.setVisible(false);
            }
        } else if (this.hudState == HudState.DEAD) {
            deadHud.stage.draw();
            for (Actor a : deadHud.stage.getActors()) {
                a.setVisible(true);
            }
        } else if (this.hudState == HudState.PAUSE) {
            pauseHUD.stage.draw();
            for (Actor a : pauseHUD.stage.getActors()) {
                a.setVisible(true);
            }
        }
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

    public void gameoverScreen() {
        game.setScreen(new GameOverScreen(this.game));
        this.dispose();
    }


    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
        pauseHUD.dispose();
        deadHud.dispose();
    }
}
