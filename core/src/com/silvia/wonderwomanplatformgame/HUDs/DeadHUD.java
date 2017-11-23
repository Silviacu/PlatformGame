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
import com.silvia.wonderwomanplatformgame.Screens.GameScreen;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;

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
        for (Actor a : this.stage.getActors()) {
            a.setVisible(true);
        }
    }

    public void hideDeadHud() {
        for (Actor a : this.stage.getActors()) {
            a.setVisible(false);
        }
    }

    public DeadHUD(SpriteBatch sb, GameScreen gameScreen){
        this.gameScreen = gameScreen;
        viewport = new FitViewport(WonderWomanGame.virtualwidth,WonderWomanGame.virtualheight, new OrthographicCamera());
        stage = new Stage(viewport,sb);

        this.setupDeadHUD();
    }

    private void setupDeadHUD() {

        Table table = new Table();
        table.top(); // put it at the top of stage
        table.setFillParent(true);// table is the size of our stage

        deadLabel = new Label("DEAD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(deadLabel).expandX().padTop(70).padLeft(10);

        if (stage != null)
            stage.addActor(table);
    }

    public void update(float dt){
        if (this.gameScreen.hudState == GameScreen.HudState.DEAD) {
            if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                Gdx.app.exit();
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                gameScreen.gameoverScreen();
            }
        }
    }


    @Override
    public void dispose() {
        stage.dispose();
    }

    public DeadHUD(){
        this.setupDeadHUD();
    }
}
