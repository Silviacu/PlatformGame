package com.silvia.wonderwomanplatformgame.Characters.WonderWoman;

/**
 * Created by silvia on 11/15/2017.
 */

public interface CharacterState {
    public String stateName = "";
    public float jumpMult = 0;
    public float damageMult = 0;

    public void setState(WonderWomanCharacter player);
    public String getStateName();
    public float getJump(float jump);
    public float getDamage(float damage);
}
