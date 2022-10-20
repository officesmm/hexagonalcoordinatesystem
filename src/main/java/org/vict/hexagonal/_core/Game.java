package org.vict.hexagonal._core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.vict.hexagonal.model.coordinate.Vector2;
import org.vict.hexagonal.model.other.Board;
import org.vict.hexagonal.model.playerinfo.Placement;
import org.vict.hexagonal.view.BoardView;

public class Game {
    List<Placement> boardItems = new ArrayList<Placement>();

    BoardController boardController;
    InputController input = new InputController();

    public void NewGame() {
        Board board = new Board(10, 10);
        BoardView boardView = new BoardView();
        TEST_INPUT();

        boardController = new BoardController(board,boardView);
    }

    // test input are adding dummy data for component testing
    void TEST_INPUT() {
        boardItems.add(new Placement(new Vector2(2, 2)));
        boardItems.add(new Placement(new Vector2(6, 1)));
        boardItems.add(new Placement(new Vector2(4, 3)));
    }

    public void Start() {
        rearrange();

        for (int i = 0; i < 3; i++) {
            try {
                Vector2.Direction direction = input.RequestDirection();
                Vector2.moveDirection(boardItems.get(0).position, direction);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            rearrange();
            boardController.boardView.display(boardController.board);
        }

    }

    public void move() {

    }

    // this is MVC project, but we don't want to do placement controller, so we rearrange placement here
    // compare to the Card Module of Side Mover Game top-left corner of this coordinate system is (0,0) whereas Side Mover Game save as bottom-left as (1,1)
    private void rearrange() {
        int n = boardItems.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (boardItems.get(j).position.x > boardItems.get(j + 1).position.x) {
                    swapping(j);
                }
            }
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (boardItems.get(j).position.y > boardItems.get(j + 1).position.y) {
                    swapping(j);
                }
            }
        }
    }

    private void swapping(int j) {
        Placement indexJ = boardItems.get(j);
        Placement indexJ1 = boardItems.get(j + 1);
        boardItems.remove((j + 1));
        boardItems.remove(j);
        boardItems.add(j, indexJ1);
        boardItems.add((j + 1), indexJ);
    }
}
