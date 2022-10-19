package org.vict.hexagonal.model.playerinfo;

import org.vict.hexagonal.model.coordinate.Vector2;

// type of placement and cards are out of this project scope
public class Placement {
    public Vector2 position;
    public String name;
    public String symbol;

    public Placement(Vector2 position) {
        this.position = position;
        symbol = "P";
    }
}
