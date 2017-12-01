package com.silvia.wonderwomanplatformgame.Characters.WonderWoman;

/**
 * Created by silvia on 11/15/2017.
 */

public class AttackPowerupState implements CharacterState{
    private String stateName = "Attack_Power";
    private float jumpMult = 1;
    private float damageMult = 2;

    @Override
    public void setState(WonderWomanCharacter player) {
        player.powerupStatus = this;
    }

    @Override
    public String getStateName() {
        return stateName;
    }

    @Override
    public float getJump(float jump) {
        return jump*jumpMult;
    }

    @Override
    public float getDamage(float damage) {
        return damage*damageMult;
    }
}
