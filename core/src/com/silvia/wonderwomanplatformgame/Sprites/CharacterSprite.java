package com.silvia.wonderwomanplatformgame.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by silvia on 11/15/2017.
 */

public class CharacterSprite extends Sprite {
    public World world;
    public Body b2body;
    private Texture characterTexture;
    private float stateTimer;

    // constructor
    public CharacterSprite() {
    }

    public CharacterSprite(World world, String texture){
    }

    public void defineCharacter(){
    }

}
