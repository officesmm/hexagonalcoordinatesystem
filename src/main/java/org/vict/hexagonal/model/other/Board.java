package org.vict.hexagonal.model.other;

import org.vict.hexagonal.common.Vector2;
import org.vict.hexagonal.model.playerinfo.Placement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board {
    private int width;
    private int height;
    public HashMap<String, Placement> placementList;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        placementList = new HashMap<String, Placement>();
        addingPlacement(new Placement(new Vector2(2, 2)));
        addingPlacement(new Placement(new Vector2(6, 1)));
        addingPlacement(new Placement(new Vector2(4, 2)));
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public boolean isInBoard(int x, int y) {
        return (x > 0 && x < width && y > 0 && y < height);
    }

    //region Placement Functions
    public Placement getIPlacement(String i) {
        return placementList.get(i);
    }
    public void addingPlacement(Placement placement) {
        String key = Vector2.KeyGenerator(placement.position);
        placementList.put(key, placement);
    }
    public void removingPlacement(String key) {
        placementList.remove(key);
    }
//    public List<Placement> getPlacementList() {
//        List<Placement> list = new ArrayList<Placement>(placementList.values());
//        return list;
//    }

    public Placement findPlacementByPosition(Vector2 position) {
        String key = Vector2.KeyGenerator(position);
        return placementList.get(key);
    }

    public void movePlacement(String key, Vector2 newPosition) {
        placementList.get(key).position = new Vector2(newPosition.x, newPosition.y);
        updateByPlacementKey(key);
    }

    // everytime a placement moved, the position and key will no longer the same, so this function will fix the problem.
    private void updateByPlacementKey(String key) {
        Placement newPlacement = placementList.get(key);
        placementList.remove(key);
        String newKey = Vector2.KeyGenerator(newPlacement.position);
        placementList.put(newKey, newPlacement);
    }
    //endregion

    //Board may have other land data like tree and hill, that may require to create by Board build immutable design pattern
}
