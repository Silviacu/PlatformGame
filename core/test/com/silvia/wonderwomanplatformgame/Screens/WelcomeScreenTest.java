package com.silvia.wonderwomanplatformgame.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.silvia.wonderwomanplatformgame.GameTest;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 11/15/2017.
 */
public class WelcomeScreenTest extends GameTest {
    @Test
    public void testBackgroundImage() {
        Texture backgroundImage = new Texture("wwspl.png");
        assertNotNull(backgroundImage);

        Sprite imageSprite = new Sprite(backgroundImage);
        assertNotNull(imageSprite);
    }

}