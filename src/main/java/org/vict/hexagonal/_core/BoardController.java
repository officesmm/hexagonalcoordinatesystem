package org.vict.hexagonal._core;

import org.vict.hexagonal.model.other.Board;
import org.vict.hexagonal.model.playerinfo.Placement;
import org.vict.hexagonal.view.BoardView;

import java.util.List;

public class BoardController {
    Board board;
    BoardView boardView;

    public BoardController(Board board, BoardView boardView) {
        this.board = board;
        this.boardView = boardView;
    }

    public void BoardDisplay(List<Placement> boardItems){
        boardView.display(board, boardItems);
    }
}
