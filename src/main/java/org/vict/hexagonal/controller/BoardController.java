package org.vict.hexagonal.controller;

import org.vict.hexagonal.common.Vector2;
import org.vict.hexagonal.model.other.Board;
import org.vict.hexagonal.model.playerinfo.Placement;
import org.vict.hexagonal.view.BoardView;

public class BoardController {

    private Board board;
    private BoardView boardView;

    public BoardController() {
        this.boardView = new BoardView();
    }
    public BoardController createBoard(int width, int height) {
        this.board = new Board(width, height);
        return this;
    }
    public Board getBoard() {
        return board;
    }

    public boolean positionInBoard(Vector2 position) {
        return board.isInBoard(position.x, position.y);
    }

    public void shootingBullet(Vector2 bulletPosition, Vector2.Direction bulletDirection, Placement hitPlacement) {
        boardView.shootingBullet(bulletPosition, bulletDirection, hitPlacement);
    }

    //region Controlling View
    public void boardDisplay() {
        boardView.display(board);
    }
    public void moveDisplay(Vector2 position) {
        boardView.displayNewPosition(position);
    }
    //endregion
}
