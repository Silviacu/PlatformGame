package com.silvia.wonderwomanplatformgame.Characters.AttackCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silvia on 11/15/2017.
 */

public class WonderWomanAttackCollection implements AttackCollection {

    public String attackCollectionName = "";
    public List<CharacterAttack> attacks = new ArrayList<CharacterAttack>();

    public CharacterAttack punchAttack = new CharacterAttack("Punch Attack", 1);
    public CharacterAttack kickAttack = new CharacterAttack("Kick Attack", 2);

    public WonderWomanAttackCollection() {
        this.attackCollectionName = "Wonder Woman Attacks";
        this.attacks.add(punchAttack);
        this.attacks.add(kickAttack);
    }
}
