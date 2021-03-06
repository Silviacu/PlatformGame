package com.silvia.wonderwomanplatformgame.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;
import com.silvia.wonderwomanplatformgame.PointsTracker.PointsTracker;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

/**
 * Created by silvia on 11/15/2017.
 */

public class MainMenuScreen implements Screen {


    private WonderWomanGame game;
    private Texture backgroundImage;
    private Sprite imageSprite;
    public static float screenWidth;
    public static float screenHeight;
    public Stage stage;
    public Stage scoreStage;

    private Label nameLabel;
    private Label chooseNameLabel;
    private Label readLabel;

    public PointsTracker pointsTracker;
    public static java.util.List<String> scoreboard;

    public String testPlayerName;
    public MainMenuScreen() {
        this.testPlayerName = "Player C";
    }
    public void testChangeName(String name) {
        this.testPlayerName = name;
    }

    public MainMenuScreen(WonderWomanGame game) {
        this.game = game;

        pointsTracker = new PointsTracker();
        try {
            scoreboard = pointsTracker.readScore();
        } catch (Exception io) {
            System.out.println(io);
        }
        Viewport viewport = new FitViewport(WonderWomanGame.virtualwidth,WonderWomanGame.virtualheight, new OrthographicCamera());

        DisplayScore(viewport);
        DisplayMenu(viewport);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float dt) {
        update(dt);
        Gdx.gl.glClearColor(0,0,0,1);// clear the color
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);//clear the screen

        game.batch.begin();
        imageSprite.draw(game.batch);
        game.batch.end();
        stage.draw();
        scoreStage.draw();
    }

    public void update(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            this.game.playerName = "Player A";
            System.out.println(this.game.playerName);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
            this.game.playerName = "Player B";
            System.out.println(this.game.playerName);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            this.game.playerName = "Player C";
            System.out.println(this.game.playerName);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
            game.setScreen(new GameScreen(game));
            this.dispose();
        }

        nameLabel.setText("Current Player Name: " + game.playerName);
    }

    @Override
    public void resize(int width, int height) {
        this.screenHeight = Gdx.graphics.getHeight();
        this.screenWidth = Gdx.graphics.getWidth();
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

    }

    public void DisplayMenu(Viewport viewport) {
        stage = new Stage(viewport, game.batch);

        backgroundImage = new Texture("mm2.jpg");
        imageSprite = new Sprite(backgroundImage);
        this.screenHeight = Gdx.graphics.getHeight();
        this.screenWidth = Gdx.graphics.getWidth();
        imageSprite.setSize(640, 400);

        chooseNameLabel = new Label("Choose Player name: \n\tPlayer A (a)  \n\tPlayer B (b) \n\tPlayer C (c)", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        nameLabel = new Label(this.game.playerName, new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        Table table = new Table();

        table.top(); // put it at the top of stage
        table.left();
        table.setFillParent(true);// table is the size of the stage

        table.row();
        table.add(chooseNameLabel).expandX().padTop(50);
        table.row();
        table.add(nameLabel).expandX();

        stage.addActor(table);
    }

    public void DisplayScore(Viewport viewport){
        scoreStage = new Stage(viewport, game.batch);

        Table scoreTable = new Table();
        scoreTable.left();
        scoreTable.setFillParent(true);// table is the size of the stage

        String scoresList = "Scores:\n======";
        int currentScore = 1;
        for(String score: scoreboard) {
            scoresList += ("\n " + currentScore + ". " + score);
            currentScore++;
        }
        readLabel = new Label(scoresList, new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        scoreTable.add(readLabel).expandX().padTop(10).padLeft(10).padBottom(10);
        scoreStage.addActor(scoreTable);
    }
}
