package org.vict.hexagonal._core;

import org.vict.hexagonal.common.Vector2;
import org.vict.hexagonal.model.coordinate.BorderNode;
import org.vict.hexagonal.model.other.Board;
import org.vict.hexagonal.model.playerinfo.Placement;
import org.vict.hexagonal.view.BoardView;

import java.util.ArrayList;
import java.util.List;

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
        placementController.rearrange();

//        for (int i = 0; i < 3; i++) {
//            try {
//                Vector2.Direction direction = input.RequestDirection();
//                Vector2.moveDirection(placementController.getIPlacement(0).position, direction);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            placementController.rearrange();
//            boardController.boardView.display(boardController.board);
//        }

//        List<BorderNode> a = borderInfo(new Vector2(3, 2));
//        System.out.println(a.size());

        List<BorderNode> b = explosion(new Vector2(3, 2), 3);
        System.out.println(b.size());

        for (int i = 0; i < b.size(); i++) {
            System.out.println(i + " of new Pos :: " + b.get(i).position.x + "," + b.get(i).position.y);
        }
    }

    public void move() {

    }


    private List<BorderNode> explosion(Vector2 position, int layer) {
        List<BorderNode> boundary = new ArrayList<BorderNode>();
        int layerCounter = layer;
        boundary = borderInfo(position);
        layerCounter--;
        while (layerCounter > 0) {
            List<BorderNode> anotherBoundary = new ArrayList<BorderNode>();
            for (int i = 0; i < boundary.size(); i++) {
                List<BorderNode> inner = borderInfo(boundary.get(i).position);
                anotherBoundary.addAll(inner);
            }
            boundary.addAll(anotherBoundary);
            boundary = cleanUp(boundary);
            layerCounter--;
        }
        boundary = removingMainPos(boundary, position);
        return boundary;
    }

    private List<BorderNode> borderInfo(Vector2 position) {
        List<BorderNode> boundary = new ArrayList<BorderNode>();
        for (int i = 0; i < Vector2.DIRECTION_LIST.length; i++) {
            Vector2 newPosition = Vector2.moveDirection(position, Vector2.DIRECTION_LIST[i]);
            if (!boardController.board.isInBoard(newPosition.x, newPosition.y)) {
                boundary.add(new BorderNode(newPosition, Vector2.DIRECTION_LIST[i], BorderNode.BorderInfo.OutOfBoundary, null));
            } else {
                Placement positionPlace = placementController.findByPosition(newPosition);
                if (positionPlace != null) {
                    boundary.add(new BorderNode(newPosition, Vector2.DIRECTION_LIST[i], BorderNode.BorderInfo.Placement, positionPlace));
                } else {
                    boundary.add(new BorderNode(newPosition, Vector2.DIRECTION_LIST[i], BorderNode.BorderInfo.FreeSpace, null));
                }
            }
        }
        return boundary;
    }

    private List<BorderNode> cleanUp(List<BorderNode> borderNodes) {
        for (int i = 0; i < borderNodes.size(); i++) {
            for (int j = i + 1; j < borderNodes.size(); j++) {
                if (Vector2.collision(borderNodes.get(i).position, borderNodes.get(j).position)) {
                    borderNodes.remove(j);
                }
            }
        }
        return borderNodes;
    }

    private List<BorderNode> removingMainPos(List<BorderNode> borderNodes, Vector2 originalPosition) {
        for (int i = 0; i < borderNodes.size(); i++) {
            if (Vector2.collision(borderNodes.get(i).position, originalPosition)) {
                borderNodes.remove(i);
            }
        }
        return borderNodes;
    }
}
