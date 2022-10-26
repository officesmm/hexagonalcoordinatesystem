package org.vict.hexagonal._core;

import org.vict.hexagonal.common.Vector2;
import org.vict.hexagonal.model.playerinfo.Placement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class InputController {
    BufferedReader br;

    public InputController() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public Vector2.Direction requestDirection() {
        System.out.println("Enter Direction");
        String direction = "";
        try {
            direction = br.readLine();
        } catch (IOException e) {
            System.out.println("Invalid Direction");
            return requestDirection();
        }
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
                return requestDirection();
        }
    }

    public String requestPlacementKey(HashMap<String, Placement> placementList) {
        if (placementList.size() == 0) {
            return "";
        } else {
            try {
                System.out.println("Enter Placement");
                for (HashMap.Entry<String, Placement> ele : placementList.entrySet()) {
                    if (ele.getValue() != null) {
                        System.out.println(ele.getKey() + " = " + ele.getValue().symbol + ", " + ele.getValue().name);
                    }
                }
                String direction = br.readLine();
                if (placementList.get(direction) == null) {
                    throw new NullDataException("No data in placement");
                }
                return direction;
            } catch (IOException | NullDataException e) {
                System.out.println("Invalid Placement");
                return requestPlacementKey(placementList);
            } catch (Exception e) {
                System.out.println("Other Error Occur, Unable to continue.");
                System.out.println(e);
                return "";
            }
        }
    }

    private class NullDataException extends Throwable {
        public NullDataException() {
            super();
        }

        public NullDataException(String message) {
            super(message);
        }
    }
}
