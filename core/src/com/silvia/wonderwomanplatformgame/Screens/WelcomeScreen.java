package com.silvia.wonderwomanplatformgame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;

/**
 * Created by silvia on 11/15/2017.
 */

/**
 * The welcome screen implements libgdx screen
 * The the welcome screen uses the wonderwomangame to
 * set the new screen,
 * render all sprites
 * create the screen width and height
 */
public class WelcomeScreen implements Screen {

    private WonderWomanGame game;
    private Texture backgroundImage;
    private Sprite imageSprite;
    public static float screenWidth;
    public static float screenHeight;

    public WelcomeScreen(WonderWomanGame game) {
        this.game = game;
        backgroundImage = new Texture("wwll.png");
        imageSprite = new Sprite(backgroundImage);
        this.screenHeight = Gdx.graphics.getHeight();
        this.screenWidth = Gdx.graphics.getWidth();
        imageSprite.setSize(this.screenWidth, this.screenHeight);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        imageSprite.draw(game.batch);
        game.batch.end();
    }

    public void update(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
            game.setScreen(new MainMenuScreen(game));
//            game.setScreen(new GameOverScreen(game));
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
}
