package org.vict.hexagonal.view;

import java.util.List;

import org.vict.hexagonal.common.Vector2;
import org.vict.hexagonal.model.other.Board;
import org.vict.hexagonal.model.playerinfo.Placement;

public class BoardView {

    // this function will not be using in real since this function do not show the Placement data
    public void display(Board board) {
        for (int i = 0; i < board.height; i++) {
            if (i % 2 != 0) {
                System.out.print("   ");
            }
            System.out.print("|");
            for (int j = 0; j < board.width; j++) {
                System.out.print(" "+i+","+j+" ");
                System.out.print("|");
            }
            System.out.println();
        }
    }

    // compare to the Card Module of Side Mover Game top-left corner of this coordinate system is (0,0) whereas Side Mover Game save as bottom-left as (1,1)
    // the loop system changed, the Game.rearrange function changed
    public void display(Board board, List<Placement> boardItem) {
        int boardItemCounter = 0;
        boolean flagItemExist = true;
        for (int loopY = 0; loopY < board.height; loopY++) {
            if (loopY % 2 != 0) {
                System.out.print("  ");
            }
            System.out.print("|");
            for (int loopX = 0; loopX < board.width; loopX++) {
                if (boardItem.size() == 0) {
                    System.out.print(" . ");
                } else {
                    if (!flagItemExist) {
                        System.out.print(" . ");
                    } else {
                        if (Vector2.collision(boardItem.get(boardItemCounter).position, new Vector2(loopX, loopY))) {
                            System.out.print(" " + boardItem.get(boardItemCounter).symbol + " ");
                            boardItemCounter++;
                            if (boardItemCounter >= boardItem.size()) {
                                flagItemExist = false;
                            }
                        } else {
                            System.out.print(" . ");
                        }
                    }
                }
                System.out.print("|");
            }
            System.out.println();
        }
    }
}
