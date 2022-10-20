package org.vict.hexagonal._core;

import org.vict.hexagonal.model.playerinfo.Placement;

import java.util.ArrayList;
import java.util.List;

public class PlacementController {

    List<Placement> placementList;

    public PlacementController() {
        placementList = new ArrayList<Placement>();
    }

    public void addingPlacement(Placement placement) {
        placementList.add(placement);
    }
}
