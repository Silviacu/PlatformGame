package com.silvia.wonderwomanplatformgame.HUDs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.silvia.wonderwomanplatformgame.Screens.GameScreen;

/**
 * Created by silvia on 11/15/2017.
 */

public class PlayHUD implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private GameScreen gameScreen;

    public Integer worldTimer;
    private float timeCount;

    public Label countdownLabel;
    public static Label scoreLabel;
    public Label timeLabel;
    public Label levelLabel;
    public Label worldLabel;
    public Label characterLabel;

    public PlayHUD(){
        this.setupPlayHUD();
    }

    public void pausePressed() {
    }

    public void resume() {
    }

    public PlayHUD(SpriteBatch sb, GameScreen gameScreen){
    }

    private void setupPlayHUD() {
    }

    public void update(float dt){
    }

    public void update(float dt, int score){
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
