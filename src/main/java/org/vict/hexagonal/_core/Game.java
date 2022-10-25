package org.vict.hexagonal._core;

import java.io.IOException;
import java.util.List;

import org.vict.hexagonal.model.coordinate.BorderNode;
import org.vict.hexagonal.common.PriorityQueue;
import org.vict.hexagonal.common.Vector2;
import org.vict.hexagonal.model.other.Board;
import org.vict.hexagonal.model.playerinfo.Placement;
import org.vict.hexagonal.view.BoardView;

public class Game {

    BoardController boardController;
    PlacementController placementController;
    InputController input;

    public void NewGame() {
        Board board = new Board(10, 10);
        BoardView boardView = new BoardView();

        input = new InputController();
        boardController = new BoardController(board, boardView);
        placementController = new PlacementController();

        TEST_INPUT();

//        for (int i = 0; i < Vector2.DIRECTION_LIST.length; i++) {
//            Vector2 po = new Vector2(3,3);
//            Vector2 newPosition = Vector2.moveDirection(po, Vector2.DIRECTION_LIST[i]);
//            System.out.println("Start info of new Pos :: " + newPosition.x + "," + newPosition.y);
//        }

        Vector2 po = new Vector2(3, 3);
        Vector2 aa = po;
//        po.x++;
//        po.y--;
        System.out.println(po.x + "," + po.y);
        System.out.println(aa.x + "," + aa.y);

    }

    // test input are adding dummy data for component testing
    void TEST_INPUT() {
        placementController.addingPlacement(new Placement(new Vector2(2, 2)));
        placementController.addingPlacement(new Placement(new Vector2(6, 1)));
        placementController.addingPlacement(new Placement(new Vector2(4, 3)));
    }

    public void Start() {
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

        PriorityQueue<BorderNode> a = borderInfo(new Vector2(3, 2));
        System.out.println(a.getCount());
    }

    public void move() {

    }


    private PriorityQueue<BorderNode> explosion(Vector2 position, int layer) {
        PriorityQueue<BorderNode> boundary = new PriorityQueue<BorderNode>();
        int layerCounter = layer;
        boundary = borderInfo(position);
        layerCounter--;
        while (layerCounter > 0) {
            PriorityQueue<BorderNode> anotherBoundary = new PriorityQueue<BorderNode>();
            for (int i = 0; i < boundary.getCount(); i++) {
                PriorityQueue<BorderNode> inner = borderInfo(boundary.Dequeue().position);
                for (int j = 0; j < inner.getCount(); j++) {
                    anotherBoundary.Enqueue(inner.Dequeue());
                }
            }
            anotherBoundary.CleanUp();
            layerCounter--;
        }
        return boundary;
    }

    private PriorityQueue<BorderNode> borderInfo(Vector2 position) {
        PriorityQueue<BorderNode> boundary = new PriorityQueue<BorderNode>();
        for (int i = 0; i < Vector2.DIRECTION_LIST.length; i++) {
            Vector2 newPosition = Vector2.moveDirection(position, Vector2.DIRECTION_LIST[i]);
            System.out.println(i + " of new Pos :: " + newPosition.x + "," + newPosition.y);
            if (!boardController.board.isInBoard(newPosition.x, newPosition.y)) {
                boundary.Enqueue(new BorderNode(newPosition, Vector2.DIRECTION_LIST[i], BorderNode.BorderInfo.OutOfBoundary, null));
            } else {
                Placement positionPlace = placementController.findByPosition(newPosition);
                if (positionPlace != null) {
                    boundary.Enqueue(new BorderNode(newPosition, Vector2.DIRECTION_LIST[i], BorderNode.BorderInfo.Placement, positionPlace));
                } else {
                    boundary.Enqueue(new BorderNode(newPosition, Vector2.DIRECTION_LIST[i], BorderNode.BorderInfo.FreeSpace, null));
                }
            }
        }
//        boundary.ShowALLData();
        return boundary;
    }
}
