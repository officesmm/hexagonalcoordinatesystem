package org.vict.hexagonal._core;

import org.vict.hexagonal.common.Vector2;
import org.vict.hexagonal.model.other.Board;
import org.vict.hexagonal.model.playerinfo.Placement;
import org.vict.hexagonal.view.BoardView2;

import java.util.HashMap;
import java.util.List;

public class BoardController {
    private Board board;
    private BoardView2 boardView;

    public BoardController() {
        this.boardView = new BoardView2();
    }

    public BoardController createBoard(int width, int height) {
        this.board = new Board(width, height);
        return this;
    }

    public boolean positionInBoard(Vector2 position) {
        return board.isInBoard(position.x, position.y);
    }

    public void boardDisplay(HashMap<String, Placement> boardItems) {
        boardView.display(board, boardItems);
    }


}
