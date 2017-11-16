package com.silvia.wonderwomanplatformgame.Characters.AttackCollection;

/**
 * Created by silvia on 11/15/2017.
 */

public class CharacterAttack {
    public String attackName = "";
    public float attackBaseDamage = 0;

    public CharacterAttack(String name, float damage) {
        this.attackName = name;
        this.attackBaseDamage = damage;
    }

    public float calculateAttackDamage(float modifier) {
        if (modifier == 0.0f) {
            return this.attackBaseDamage;
        }
        return this.attackBaseDamage * modifier;
    }
}
