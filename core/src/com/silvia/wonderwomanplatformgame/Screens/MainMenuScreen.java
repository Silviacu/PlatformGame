package com.silvia.wonderwomanplatformgame.Screens;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;
import com.silvia.wonderwomanplatformgame.PointsTracker.PointsTracker;

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
    private Label playLabel;
    private Label scoreLabel;
    private Label readLabel;

    public PointsTracker pointsTracker;
    public static java.util.List<String> scoreboard;
    public String testPlayerName;

    public MainMenuScreen() {
    }
    public void testChangeName(String name) {
    }

    public MainMenuScreen(WonderWomanGame game) {
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float dt) {
    }

    public void update(float dt) {
    }

    @Override
    public void resize(int width, int height) {
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
    }

    public void DisplayScore(Viewport viewport){
    }
}
