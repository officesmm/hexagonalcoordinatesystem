package org.vict.hexagonal._core;

import org.vict.hexagonal.common.Vector2;
import org.vict.hexagonal.model.coordinate.BorderNode;
import org.vict.hexagonal.model.playerinfo.Placement;

import java.util.HashMap;

public class Game {

    BoardController boardController;
    PlacementController placementController;
    InputController input;

    public void newGame() {
        input = new InputController();
        boardController = new BoardController().createBoard(10, 10);
        placementController = new PlacementController();

        placementController = TEST_INPUT(placementController);
    }

    // test input are adding dummy data for unit testing
    static PlacementController TEST_INPUT(PlacementController placementController) {
        placementController.addingPlacement(new Placement(new Vector2(2, 2)));
        placementController.addingPlacement(new Placement(new Vector2(6, 1)));
        placementController.addingPlacement(new Placement(new Vector2(4, 3)));
        return placementController;
    }

    public void start() {
        while (true) {
            boardController.boardDisplay(placementController.placementList);

            String selectedPlacementKey = input.requestPlacementKey(placementController.placementList);
            move(selectedPlacementKey);
        }
    }

    void move(String selectedPlacementKey) {
        Vector2.Direction direction = input.requestDirection();
        Vector2 newPosition = Vector2.moveDirection(placementController.placementList.get(selectedPlacementKey).position, direction);

        String newPositionAsKey = Integer.toString(newPosition.x) + Integer.toString(newPosition.y);
        System.out.println("new Position " + newPosition.x + ", " + newPosition.y);
        if (placementController.placementList.get(newPositionAsKey) != null) {
            System.out.println("Unable to move");
        } else {
            placementController.placementList.get(selectedPlacementKey).position = newPosition;
            placementController.updateByPlacementKey(selectedPlacementKey);
        }
    }

    private HashMap<String, BorderNode> movablePosition(Vector2 position, int layer) {
        HashMap<String, BorderNode> closestFreePositionList = new HashMap<>();
        closestFreePositionList.putAll(borderInfo(position));
        for (HashMap.Entry<String, BorderNode> ele : closestFreePositionList.entrySet()) {
            if (ele.getValue().borderInfo != BorderNode.BorderInfo.FreeSpace) {
                closestFreePositionList.remove(ele.getKey());
            }
        }
        return closestFreePositionList;
    }

    private HashMap<String, BorderNode> explosion(Vector2 position, int layer) {
        HashMap<String, BorderNode> boundary = new HashMap<>();
        int layerCounter = layer;
        boundary = borderInfo(position);
        layerCounter--;
        while (layerCounter > 0) {
            HashMap<String, BorderNode> anotherBoundary = new HashMap<>();
            for (HashMap.Entry<String, BorderNode> ele : boundary.entrySet()) {
                anotherBoundary.putAll(borderInfo(ele.getValue().position));
            }
            boundary.putAll(anotherBoundary);
            layerCounter--;
        }
        String key = Integer.toString(position.x) + Integer.toString(position.y);
        boundary.remove(key);
        return boundary;
    }

    private HashMap<String, BorderNode> borderInfo(Vector2 position) {
        HashMap<String, BorderNode> boundary = new HashMap<>();
        for (int i = 0; i < Vector2.DIRECTION_LIST.length; i++) {
            Vector2 newPosition = Vector2.moveDirection(position, Vector2.DIRECTION_LIST[i]);
            String key = Integer.toString(newPosition.x) + Integer.toString(newPosition.y);
            if (!boardController.positionInBoard(newPosition)) {
                boundary.put(key, new BorderNode(newPosition, Vector2.DIRECTION_LIST[i], BorderNode.BorderInfo.OutOfBoundary, null));
            } else {
                Placement positionPlace = placementController.findByPosition(newPosition);
                if (positionPlace != null) {
                    boundary.put(key, new BorderNode(newPosition, Vector2.DIRECTION_LIST[i], BorderNode.BorderInfo.Placement, positionPlace));
                } else {
                    boundary.put(key, new BorderNode(newPosition, Vector2.DIRECTION_LIST[i], BorderNode.BorderInfo.FreeSpace, null));
                }
            }
        }
        return boundary;
    }

}
