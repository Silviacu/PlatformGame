package com.silvia.wonderwomanplatformgame.Characters.AttackCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silvia on 11/15/2017.
 */

public class EnemyCrowAttackCollection implements AttackCollection {

    public String attackCollectionName = "";
    public List<CharacterAttack> attacks = new ArrayList<CharacterAttack>();

    public CharacterAttack flyAttack = new CharacterAttack("Fly Attack", 6);

    public EnemyCrowAttackCollection() {
        this.attackCollectionName = "Enemy Crow Attacks";
        this.attacks.add(flyAttack);
    }

}
