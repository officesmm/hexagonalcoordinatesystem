package test;

import org.vict.hexagonal.common.Vector2;
import org.vict.hexagonal.model.other.Board;
import org.vict.hexagonal.model.playerinfo.Placement;

public class CREATIONAL {
    // test input are adding dummy data for unit testing
//    public static PlacementController CREATE_PLACEMENT(PlacementController placementController) {
//        placementController.addingPlacement(new Placement(new Vector2(2, 2)));
//        placementController.addingPlacement(new Placement(new Vector2(6, 1)));
//        placementController.addingPlacement(new Placement(new Vector2(4, 2)));
//        return placementController;
//    }

    public static void CREATE_PLACEMENT(Board board) {
        board.addingPlacement(new Placement(new Vector2(2, 2)));
        board.addingPlacement(new Placement(new Vector2(6, 1)));
        board.addingPlacement(new Placement(new Vector2(4, 2)));
    }
}
