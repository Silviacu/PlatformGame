package com.silvia.wonderwomanplatformgame.HUDs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.WonderWomanCharacter;
import com.silvia.wonderwomanplatformgame.Screens.GameScreen;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;

/**
 * Created by silvia on 11/15/2017.
 */

public class PlayHUD implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private GameScreen gameScreen;

    public static int worldTimer;
    private float timeCount;

    public Label countdownLabel;
    public static Label healthLabel;
    public static Label scoreLabel;
    public Label timeLabel;
    public Label levelLabel;
    public Label worldLabel;
    public Label characterLabel;

    public PlayHUD(){
        this.setupPlayHUD();
    }

    public void pausePressed() {
        for (Actor a : this.stage.getActors()) {
            a.setVisible(false);
        }
        this.gameScreen.hudState = GameScreen.HudState.PAUSE;
    }

    public void resume() {
        for (Actor a : this.stage.getActors()) {
            a.setVisible(true);
        }
        this.gameScreen.hudState = GameScreen.HudState.PLAY;
    }

    public PlayHUD(SpriteBatch sb, GameScreen gameScreen){
        viewport = new FitViewport(WonderWomanGame.virtualwidth,WonderWomanGame.virtualheight, new OrthographicCamera());
        stage = new Stage(viewport,sb);
        this.gameScreen = gameScreen;
        // a stage is an empty box
        // table inside the stage keeps things organized in a postion

        this.setupPlayHUD();
    }

    private void setupPlayHUD() {
        worldTimer = 300;
        timeCount =0;

        Table table = new Table();
        table.top(); // put it at the top of stage
        table.setFillParent(true);// table is the size of our stage
        //03 how many numbers d is for interger graphic version font
        characterLabel =new Label("Wonder-Woman", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        healthLabel = new Label(String.format("10"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("0"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        worldLabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));;
        levelLabel = new Label("Level 1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        countdownLabel = new Label(String.format("%03d",worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(characterLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();
        table.row();
        table.add(healthLabel).expandX();

        if (stage != null)
            stage.addActor(table);
    }

    public void update(float dt){
        timeCount += dt;
        if (timeCount >= 1) {
            worldTimer--;
            countdownLabel.setText(String.format("%03d",worldTimer));
            timeCount = 0;
            healthLabel.setText(WonderWomanCharacter.getInstance().health + "");
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            if(gameScreen.hudState == GameScreen.HudState.PAUSE) {
                gameScreen.playIsPaused();
            }
        }
    }

    public void update(float dt, int score){
        timeCount += dt;
        if (timeCount >= 1) {
            worldTimer--;
            countdownLabel.setText(String.format("%03d",worldTimer));
            timeCount = 0;
            scoreLabel.setText("" + score);
            healthLabel.setText(WonderWomanCharacter.getInstance().health + "");
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            if(gameScreen.hudState == GameScreen.HudState.PAUSE) {
                gameScreen.playResumed();
            }
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
