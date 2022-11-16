package org.vict.hexagonal.view;

import org.vict.hexagonal.common.Vector2;
import org.vict.hexagonal.model.other.Board;
import org.vict.hexagonal.model.playerinfo.Placement;

import java.util.HashMap;

public class BoardView {

    // this function will not be using in real since this function do not show the Placement data
    public void displayMap(Board board) {
        for (int i = 0; i < board.getHeight(); i++) {
            if (i % 2 != 0) {
                System.out.print("   ");
            }
            System.out.print("|");
            for (int j = 0; j < board.getWidth(); j++) {
                System.out.print(" " + i + "," + j + " ");
                System.out.print("|");
            }
            System.out.println();
        }
    }

    // Compare to the Card Module of Side Mover Game, this project navigate top-left corner of this coordinate system is (0,0) whereas Side Mover Game save as bottom-left as (1,1)
    // the loop system changed, the Game. rearrange function changed
    public void display(Board board) {
        HashMap<String, Placement> boardItem = board.placementList;
        boolean flagItemExist = true;
        for (int loopY = 0; loopY < board.getHeight(); loopY++) {
            if (loopY % 2 != 0) {
                System.out.print("  ");
            }
            System.out.print("|");
            for (int loopX = 0; loopX < board.getWidth(); loopX++) {
                if (boardItem.size() == 0) {
                    System.out.print(" . ");
                } else {
                    if (!flagItemExist) {
                        System.out.print(" . ");
                    } else {
                        String Key = Vector2.KeyGenerator(new Vector2(loopX, loopY));
                        if (boardItem.get(Key) != null) {
                            System.out.print(" " + boardItem.get(Key).symbol + " ");
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

    public void shootingBullet(Vector2 bulletPosition, Vector2.Direction bulletDirection, Placement hitPlacement) {
        if (hitPlacement == null) {
            System.out.println("A bullet is shooting to the " + bulletDirection + " direction and nothing hit");
        } else {
            System.out.println("A bullet is shooting to the " + bulletDirection + " direction and hit on" + hitPlacement.position.x + "," + hitPlacement.position.y);
        }
    }

    public void displayNewPosition(Vector2 position) {
        System.out.println("new Position " + position.x + ", " + position.y);
    }
}
