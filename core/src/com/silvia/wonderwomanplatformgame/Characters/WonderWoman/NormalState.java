package com.silvia.wonderwomanplatformgame.Characters.WonderWoman;

/**
 * Created by silvia on 11/15/2017.
 */

public class NormalState implements CharacterState{

    @Override
    public void setState(WonderWomanCharacter player) {

    }

    @Override
    public String getStateName() {
        return "Normal";
    }

    @Override
    public float getJump(float jump) {
        return 1;
    }

    @Override
    public float getDamage(float damage) {
        return 0;
    }
}
