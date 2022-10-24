package org.vict.hexagonal.common;

import org.vict.hexagonal.common.Vector2;
import org.vict.hexagonal.model.playerinfo.Placement;

public class Border implements Comparable {

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

    public float priority;
    @Override
    public int compareTo(Object o) {
        Border other = (Border) o;
        if (this.priority < other.priority) {
            return -1;
        } else if (this.priority > other.priority) {
            return 1;
        } else {
            return 0;
        }
    }
}
