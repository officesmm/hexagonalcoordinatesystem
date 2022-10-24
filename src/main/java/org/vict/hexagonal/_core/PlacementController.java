package org.vict.hexagonal._core;

import org.vict.hexagonal.common.Vector2;
import org.vict.hexagonal.model.playerinfo.Placement;

import java.util.ArrayList;
import java.util.List;

public class PlacementController {

    private List<Placement> placementList;

    public PlacementController() {
        placementList = new ArrayList<Placement>();
    }

    public void addingPlacement(Placement placement) {
        placementList.add(placement);
    }

    public Placement getIPlacement(int i) {
        return placementList.get(i);
    }

    public Placement findByPosition(Vector2 position) {
        for (int i = 0; i < placementList.size(); i++) {
            if(Vector2.collision(placementList.get(i).position, position)){
                return placementList.get(i);
            }
        }
        return null;
    }

    // compare to the Card Module of Side Mover Game top-left corner of this coordinate system is (0,0) whereas Side Mover Game save as bottom-left as (1,1)
    public void rearrange() {
        int n = placementList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (placementList.get(j).position.x > placementList.get(j + 1).position.x) {
                    swapping(j);
                }
            }
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (placementList.get(j).position.y > placementList.get(j + 1).position.y) {
                    swapping(j);
                }
            }
        }
    }

    private void swapping(int j) {
        Placement indexJ = placementList.get(j);
        Placement indexJ1 = placementList.get(j + 1);
        placementList.remove((j + 1));
        placementList.remove(j);
        placementList.add(j, indexJ1);
        placementList.add((j + 1), indexJ);
    }
}
