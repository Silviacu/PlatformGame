package com.silvia.wonderwomanplatformgame.Screens;

/**
 * Created by silvia on 11/15/2017.
 */

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
import com.silvia.wonderwomanplatformgame.PointsTracker.PointsTracker;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;

import java.util.List;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GameOverScreen implements Screen {

    private WonderWomanGame game;
    private Texture backgroundImage;
    private Sprite imageSprite;
    public static float screenWidth;
    public static float screenHeight;
    public Stage stage;
    public Stage scoreStage;

    private Label nameLabel;
    private Label gameOverLabel;
    private Label playLabel;
    private Label scoreLabel;
    private Label readLabel;

    public PointsTracker pointsTracker;
    public List<String> scoreboard;

    public GameOverScreen(WonderWomanGame game) {
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
            game.setScreen(new MainMenuScreen(game));
            this.dispose();
        }
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

        backgroundImage = new Texture("mm.jpg");
        imageSprite = new Sprite(backgroundImage);
        this.screenHeight = Gdx.graphics.getHeight();
        this.screenWidth = Gdx.graphics.getWidth();
        imageSprite.setSize(640, 400);

        gameOverLabel = new Label("Congrats! Here's how you did: " + scoreboard.get(scoreboard.size()-1), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        Table table = new Table();
        table.top();
        table.left();
        table.setFillParent(true);

        table.add(playLabel).expandX().padTop(10);
        table.row();
        table.add(gameOverLabel).expandX();
        table.row();
        table.add(nameLabel).expandX();

        stage.addActor(table);
    }

    public void DisplayScore(Viewport viewport){
        scoreStage = new Stage(viewport, game.batch);

        Table scoreTable = new Table();
        scoreTable.left();
        scoreTable.setFillParent(true);

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