package com.silvia.wonderwomanplatformgame.Characters.AttackCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silvia on 11/15/2017.
 */

public class EnemyZombieAttackCollection implements AttackCollection {


    public String attackCollectionName = "";
    public List<CharacterAttack> attacks = new ArrayList<CharacterAttack>();

    public CharacterAttack zombieAttack = new CharacterAttack("Bite Attack", 3);

    public EnemyZombieAttackCollection() {
        this.attackCollectionName = "Enemy Zombie Attacks";
        this.attacks.add(zombieAttack);
    }
}
