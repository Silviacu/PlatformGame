package com.silvia.wonderwomanplatformgame.HUDs;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.silvia.wonderwomanplatformgame.Screens.GameScreen;
import java.util.List;

/**
 * Created by silvia on 11/15/2017.
 */
public class DeadHUD implements Disposable {
    public Stage stage;
    private Viewport viewport;
    public Label deadLabel;
    public GameScreen gameScreen;

    public void showDeadHud() {
    }

    public void hideDeadHud() {
    }

    public DeadHUD(SpriteBatch sb, GameScreen gameScreen){
    }

    private void setupDeadHUD() {
    }

    public void update(float dt){
    }

    @Override
    public void dispose() {
        stage.dispose();
    }



}
