package com.silvia.wonderwomanplatformgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.silvia.wonderwomanplatformgame.Screens.GameScreen;
import com.silvia.wonderwomanplatformgame.Screens.WelcomeScreen;

public class WonderWomanGame extends Game {
	public SpriteBatch batch; //container that hold images and textures
	// when we redner them to the screen the spritebatch draws everything
	//public so all of the screens has acces to the sprites
	public static String playerName = "Silvia";

	//	=== WINDOW VARIABLES ===
	public static final int virtualwidth =640;
	public static final int virtualheight = 400;
	public static final float PPM = 100; //pixels per meter

	//	Each number is paired with an in-game entity to identify them to be used in interactions
//	=== ENTITY VARIABLES ===
	public static final short DEFAULT_BIT =1;
	public static final short WONDER_BIT = 2;
	public static final short BREAKABLE_BIT =3;
	public static final short TREASURESMALL_BIT = 4;
	public static final short TREASURELARGE_BIT = 5;
	public static final short DESTROYED_BIT = 6;
	public static final short HEALTH_BIT = 7;
	public static final short SPIKE_BIT = 8;
	public static final short POWERUP_BIT = 9;
	public static final short ZONEEND_BIT = 10;
	public static final short ZONEFALL_BIT = 11;

	@Override
	public void create () {
		batch = new SpriteBatch();

		//the game can set screens
		setScreen(new WelcomeScreen(this));
	}

	@Override
	public void render () {
		super.render();
		//deligate the render method to the play screen or whatever screen is active
	}


}
