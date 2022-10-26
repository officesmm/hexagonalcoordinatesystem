package org.vict.hexagonal._core;

import org.vict.hexagonal.common.Vector2;
import org.vict.hexagonal.model.playerinfo.Placement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlacementController {

    HashMap<String, Placement> placementList;

    public PlacementController() {
        placementList = new HashMap<String, Placement>();
    }

    public void addingPlacement(Placement placement) {
        String key = Integer.toString(placement.position.x) + Integer.toString(placement.position.y);
        placementList.put(key, placement);
    }

    public Placement getIPlacement(int i) {
        return placementList.get(i);
    }

    public List<Placement> getPlacementList(){
        List<Placement> list = new ArrayList<Placement>(placementList.values());
        return list;
    }

    public Placement findByPosition(Vector2 position) {
        String key = Integer.toString(position.x) + Integer.toString(position.y);
        return placementList.get(key);
    }

    // everytime a placement moved, the position and key will no longer the same so this function will fix the problem
    public void updateByPlacementKey(String key){
        Placement newPlacement = placementList.get(key);
        placementList.remove(key);
        String newKey = Integer.toString(newPlacement.position.x) + Integer.toString(newPlacement.position.y);
        placementList.put(newKey,newPlacement);
    }

//    public void rearrangeAll() {
//
//        for (HashMap.Entry<String, Placement> ele : placementList.entrySet()) {
//
//        }
//    }
}
