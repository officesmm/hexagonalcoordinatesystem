package org.vict.hexagonal._core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.vict.hexagonal.common.Border;
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
    }

    // test input are adding dummy data for component testing
    void TEST_INPUT() {
        placementController.addingPlacement(new Placement(new Vector2(2, 2)));
        placementController.addingPlacement(new Placement(new Vector2(6, 1)));
        placementController.addingPlacement(new Placement(new Vector2(4, 3)));
    }

    public void Start() {
        placementController.rearrange();

        for (int i = 0; i < 3; i++) {
            try {
                Vector2.Direction direction = input.RequestDirection();
                Vector2.moveDirection(placementController.getIPlacement(0).position, direction);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            placementController.rearrange();
            boardController.boardView.display(boardController.board);
        }

    }

    public void move() {

    }


//    private List<Border> explosion(Vector2 position, int layer) {
//        List<Border> boundary = new ArrayList<Border>();
//        if (layer == 1) {
//            boundary = borderInfo(position);
//        }
//        if (layer == 2) {
//            boundary = borderInfo(position);
//            for (int i = 0; i < boundary.size(); i++) {
//
//            }
//        }
//        return boundary;
//    }

    private PriorityQueue<Border> borderInfo(Vector2 position) {
        PriorityQueue<Border> boundary = new PriorityQueue<Border>();
        for (int i = 0; i < Vector2.DIRECTION_LIST.length; i++) {
            Vector2 newPosition = Vector2.moveDirection(position, Vector2.DIRECTION_LIST[i]);
            if (!boardController.board.isInBoard(newPosition.x, newPosition.y)) {
                boundary.Enqueue(new Border(newPosition, Vector2.DIRECTION_LIST[i], Border.BorderInfo.OutOfBoundary, null));
            } else {
                Placement positionPlace = placementController.findByPosition(newPosition);
                if (positionPlace != null) {
                    boundary.Enqueue(new Border(newPosition, Vector2.DIRECTION_LIST[i], Border.BorderInfo.Placement, positionPlace));
                }else{
                    boundary.Enqueue(new Border(newPosition, Vector2.DIRECTION_LIST[i], Border.BorderInfo.FreeSpace, null));
                }
            }
        }
        return boundary;
    }
}
