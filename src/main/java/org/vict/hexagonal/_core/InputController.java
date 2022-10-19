package org.vict.hexagonal._core;

import org.vict.hexagonal.model.coordinate.Vector2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputController {
    BufferedReader br;

    public InputController() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public Vector2.Direction RequestDirection() throws IOException {
        System.out.println("Enter Direction");
        String direction = br.readLine();

        switch (direction) {
            case "E":
                return Vector2.Direction.East;
            case "NE":
                return Vector2.Direction.NorthEast;
            case "SE":
                return Vector2.Direction.SouthEast;
            case "W":
                return Vector2.Direction.West;
            case "NW":
                return Vector2.Direction.NorthWest;
            case "SW":
                return Vector2.Direction.SouthWest;
            default:
                System.out.println("Invalid Direction");
                return RequestDirection();
        }

    }
}
