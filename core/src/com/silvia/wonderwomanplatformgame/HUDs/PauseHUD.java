package com.silvia.wonderwomanplatformgame.HUDs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.List;
import com.silvia.wonderwomanplatformgame.Screens.GameScreen;

/**
 * Created by silvia on 11/15/2017.
 */


public class PauseHUD implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private boolean pauseState;
    private GameScreen gameScreen;

    Label pauseLabel;
    Label resumeLabel;
    Label exitLabel;

    public PauseHUD(SpriteBatch sb, GameScreen gameScreen){
    }

    public void setupLabels() {
    }

    public void bringUpPauseHUD() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public void update(float dt){
    }

}
