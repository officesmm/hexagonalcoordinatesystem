package org.vict.hexagonal._core;

import org.vict.hexagonal.common.Vector2;
import org.vict.hexagonal.model.coordinate.BorderNode;
import org.vict.hexagonal.model.playerinfo.Placement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game2 {

    BoardController boardController;
    PlacementController2 placementController;
    InputController input;

    public void newGame() {
        input = new InputController();
        boardController = new BoardController().createBoard(10, 10);
        placementController = new PlacementController2();

        placementController = TEST_INPUT(placementController);
    }

    // test input are adding dummy data for unit testing
    static PlacementController2 TEST_INPUT(PlacementController2 placementController) {
        placementController.addingPlacement(new Placement(new Vector2(2, 2)));
        placementController.addingPlacement(new Placement(new Vector2(6, 1)));
        placementController.addingPlacement(new Placement(new Vector2(4, 3)));
        return placementController;
    }

    public void start() {
        placementController.placementList.get("33");
//        placementController.placementList.put("11",null);

        if (placementController.placementList.get("11") == null) {
            System.out.println("this is null");
        }else{

        }
//        List<Placement> list = new ArrayList<Placement>(placementController.placementList.values());
//        boardController.boardView.display(boardController.board,list);
//
        Placement placement = input.requestPlacement(placementController.placementList);
//        for (int i = 0; i < 3; i++) {
//            Vector2.Direction direction = input.requestDirection();
//            Vector2.moveDirection(placementController.getIPlacement(0).position, direction);
//
//            list = new ArrayList<Placement>(placementController.placementList.values());
//            boardController.boardView.display(boardController.board,list);
//        }

//        List<BorderNode> a = borderInfo(new Vector2(3, 2));
//        System.out.println(a.size());

//        HashMap<String, BorderNode> b = explosion(new Vector2(3, 2), 3);
//        System.out.println(b.size());
//
//        for (HashMap.Entry<String, BorderNode> ele :
//                b.entrySet()) {
////            System.out.println(ele.getKey() + " = " + ele.getValue());
//        }
    }

    public void move() {

    }


    private HashMap<String, BorderNode> movablePosition(Vector2 position, int layer) {
        HashMap<String, BorderNode> closestFreePositionList = new HashMap<>();
        closestFreePositionList.putAll(borderInfo(position));
        for (HashMap.Entry<String, BorderNode> ele :
                closestFreePositionList.entrySet()) {
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
            for (HashMap.Entry<String, BorderNode> ele :
                    boundary.entrySet()) {
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
            if (!boardController.board.isInBoard(newPosition.x, newPosition.y)) {
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
