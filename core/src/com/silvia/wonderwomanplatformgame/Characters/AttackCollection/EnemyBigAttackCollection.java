package com.silvia.wonderwomanplatformgame.Characters.AttackCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silvia on 11/15/2017.
 */

public class EnemyBigAttackCollection implements AttackCollection {

    public String attackCollectionName = "";
    public List<CharacterAttack> attacks = new ArrayList<CharacterAttack>();

    public CharacterAttack bigAttack = new CharacterAttack("Big Attack", 5);

    public EnemyBigAttackCollection() {
        this.attackCollectionName = "Enemy Big Attacks";
        this.attacks.add(bigAttack);
    }


}
