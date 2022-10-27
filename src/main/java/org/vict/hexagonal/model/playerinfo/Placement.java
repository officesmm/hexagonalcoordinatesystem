package org.vict.hexagonal.model.playerinfo;

import org.vict.hexagonal.common.Vector2;

// type of placement and cards are out of this project scope
public class Placement {

    public enum AttackType {
        Melee(0),
        Range(1);

        public final int id;

        AttackType(int id) {
            this.id = id;
        }
    }

    public Vector2 position;
    public String name;
    public String symbol;
    public int damage;
    public AttackType attackType;
    public int attackRange;

    public Placement(Vector2 position) {
        this.position = position;
        symbol = "P";
        damage = 1;
        attackRange = 3;
        attackType = AttackType.Range;
    }
}
