package org.vict.hexagonal.model.coordinate;

import org.vict.hexagonal.model.playerinfo.Placement;

public class Border {

    public enum BorderInfo{
        OutOfBoundary,
        FreeSpace,
        Placement
    }

    public Vector2 position;
    public Vector2.Direction direction;
    public BorderInfo borderInfo;
    public Placement placement;

    public Border(Vector2 position, Vector2.Direction direction, BorderInfo borderInfo, Placement placement) {
        this.position = position;
        this.direction = direction;
        this.borderInfo = borderInfo;
        this.placement = placement;
    }

}
