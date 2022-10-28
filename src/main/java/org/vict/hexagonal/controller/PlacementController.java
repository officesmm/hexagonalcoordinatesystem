package org.vict.hexagonal.controller;

import org.vict.hexagonal.common.Vector2;
import org.vict.hexagonal.model.playerinfo.Placement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlacementController {

    public HashMap<String, Placement> placementList;

    public PlacementController() {
        placementList = new HashMap<String, Placement>();
    }

    public void addingPlacement(Placement placement) {
        String key = Vector2.KeyGenerator(placement.position);
        placementList.put(key, placement);
    }

    public Placement getIPlacement(int i) {
        return placementList.get(i);
    }

    public List<Placement> getPlacementList(){
        List<Placement> list = new ArrayList<Placement>(placementList.values());
        return list;
    }

    public Placement findPlacementByPosition(Vector2 position) {
        String key = Vector2.KeyGenerator(position);
        return placementList.get(key);
    }

    // everytime a placement moved, the position and key will no longer the same so this function will fix the problem
    public void updateByPlacementKey(String key){
        Placement newPlacement = placementList.get(key);
        placementList.remove(key);
        String newKey = Vector2.KeyGenerator(newPlacement.position);
        placementList.put(newKey,newPlacement);
    }

}
