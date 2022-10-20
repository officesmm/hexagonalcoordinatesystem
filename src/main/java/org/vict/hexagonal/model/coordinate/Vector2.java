package org.vict.hexagonal.model.coordinate;

import org.vict.hexagonal.model.other.Board;
import org.vict.hexagonal.model.playerinfo.Placement;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// in this Hexagonal Coordinate we will use "odd-r" for display.
// this board is starting from (0,0)
public class Vector2 {
    public int x;
    public int y;

    public enum Direction {
        East,
        NorthEast,
        SouthEast,
        West,
        NorthWest,
        SouthWest
    }

    final Direction[] directions = {Direction.East, Direction.NorthEast, Direction.SouthEast,
            Direction.West, Direction.NorthWest, Direction.SouthEast};


    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // this move direction is only for "odd-r" display
    public static Vector2 moveDirection(Vector2 vector2, Direction direction) {
        Vector2 newPosition = vector2;

        if (vector2.y % 2 == 0) {
            switch (direction) {
                case East:
                    newPosition.x++;
                    break;
                case NorthEast:
                    newPosition.y--;
                    break;
                case SouthEast:
                    newPosition.y++;
                    break;
                case West:
                    newPosition.x--;
                    break;
                case NorthWest:
                    newPosition.x--;
                    newPosition.y--;
                    break;
                case SouthWest:
                    newPosition.x--;
                    newPosition.y++;
                    break;
            }
        } else {
            switch (direction) {
                case East:
                    newPosition.x++;
                    break;
                case NorthEast:
                    newPosition.x++;
                    newPosition.y--;
                    break;
                case SouthEast:
                    newPosition.x++;
                    newPosition.y++;
                    break;
                case West:
                    newPosition.x--;
                    break;
                case NorthWest:
                    newPosition.y--;
                    break;
                case SouthWest:
                    newPosition.y++;
                    break;
            }
        }
        return newPosition;
    }

    public static boolean collision(Vector2 pos1, Vector2 pos2) {
        return ((pos1.x == pos2.x) && (pos1.y == pos2.y));
    }

    void collision() {
    }

    void borderInfo(Board board, Vector2 position) {
        List<Border> boundary = new ArrayList<Border>();
        for (int i = 0; i < directions.length; i++) {
            Vector2 newPosition = moveDirection(position, directions[i]);
            if (!board.isInBoard(newPosition.x, newPosition.y)) {
                boundary.add(new Border(newPosition, directions[i], Border.BorderInfo.OutOfBoundary, null));
            } else {

            }
        }
    }

    void explosion() {
    }

    void distance(Vector2 pos1, Vector2 pos2) {
    }

}
