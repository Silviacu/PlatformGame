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
import java.util.List;
import com.silvia.wonderwomanplatformgame.Screens.GameScreen;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;

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
        pauseState = false;
        this.gameScreen = gameScreen;
        viewport = new FitViewport(WonderWomanGame.virtualwidth,WonderWomanGame.virtualheight, new OrthographicCamera());
        stage = new Stage(viewport,sb);


        Table table = new Table();
        table.top(); // put it at the top of stage
        table.setFillParent(true);// table is the size of the stage

        setupLabels();

        table.add(pauseLabel).expandX().padTop(30).padLeft(180);
        table.row();
        table.add(resumeLabel).expandX().padTop(10).padRight(130);
        table.add(exitLabel).expandX().padTop(10);

        stage.addActor(table);
    }

    public void setupLabels() {
        pauseLabel =new Label("Paused", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        resumeLabel =new Label("Resume (R)", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        exitLabel =new Label("Exit (Esc)", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
    }

    public void bringUpPauseHUD() {
        for (Actor a : this.stage.getActors()) {
            a.setVisible(true);
        }
        gameScreen.hudState = GameScreen.HudState.PAUSE;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public void update(float dt){
        if (gameScreen.hudState == GameScreen.HudState.PAUSE) {
            if(Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                this.gameScreen.unpause();
            }

            if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                if (gameScreen.hudState == GameScreen.HudState.PAUSE) {
                    Gdx.app.exit();
//                    this.gameScreen.mainMenuScreen();
                }
            }
        }
    }

    public PauseHUD() {
        this.setupLabels();
    }

}
