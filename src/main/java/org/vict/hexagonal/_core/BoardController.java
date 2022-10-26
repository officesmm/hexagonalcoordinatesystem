package org.vict.hexagonal._core;

import org.vict.hexagonal.model.other.Board;
import org.vict.hexagonal.model.playerinfo.Placement;
import org.vict.hexagonal.view.BoardView;

import java.util.HashMap;
import java.util.List;

public class BoardController {
    Board board;
    BoardView boardView;

    public BoardController() {
        this.boardView = new BoardView();
    }

    public BoardController createBoard(int width, int height) {
        this.board = new Board(width, height);
        return this;
    }

    public void boardDisplay(List<Placement> boardItems) {
        boardView.display(board, boardItems);
    }


}
