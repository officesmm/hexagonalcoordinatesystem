package org.vict.hexagonal._core;

import org.vict.hexagonal.common.Vector2;
import org.vict.hexagonal.controller.BoardController;
import org.vict.hexagonal.controller.InputController;
import org.vict.hexagonal.model.coordinate.BorderNode;
import org.vict.hexagonal.model.playerinfo.Placement;

import java.util.HashMap;

public class Game {

    BoardController boardController;
    InputController input;

    public void newGame() {
        input = new InputController();
        boardController = new BoardController().createBoard(10, 10);
    }

    public void start() {
        while (true) {
            boardController.boardDisplay();
            String selectedPlacementKey = input.requestPlacementKey(boardController.getBoard().placementList);
            // Action should be an interface with perform by Commend pattern
            InputController.Action action = input.requestAction();
            if (action == InputController.Action.Attack) {
                shot(selectedPlacementKey);
            } else if (action == InputController.Action.Move) {
                move(selectedPlacementKey);
            }
        }
    }

    void move(String selectedPlacementKey) {
        Vector2.Direction direction = input.requestDirection();
        Vector2 newPosition = Vector2.moveDirection(boardController.getBoard().placementList.get(selectedPlacementKey).position, direction);

        boardController.moveDisplay(newPosition);
        if (boardController.getBoard().getIPlacement(Vector2.KeyGenerator(newPosition)) != null) {
            System.out.println("Unable to move");
        } else {
            boardController.getBoard().movePlacement(selectedPlacementKey, newPosition);
        }
    }

    void shot(String selectedPlacementKey) {
        Vector2.Direction direction = input.requestDirection();
        Placement placementAttacker = boardController.getBoard().placementList.get(selectedPlacementKey);
        Vector2 attackerPosition = new Vector2(placementAttacker.position.x, placementAttacker.position.y);
        if (placementAttacker.attackType == Placement.AttackType.Range) {
            for (int i = 0; i < placementAttacker.attackRange; i++) {
                Vector2 newPosition = Vector2.moveDirection(attackerPosition, direction);
                Placement possibleHitee = boardController.getBoard().findPlacementByPosition(newPosition);
                if (possibleHitee != null) {
                    boardController.shootingBullet(attackerPosition, direction, possibleHitee);
                    boardController.getBoard().removingPlacement(Vector2.KeyGenerator(newPosition)); // there may be some calculation for hit hitPoint and die condition
                    return; // if the bullet is keep going, don't break it
                }
                attackerPosition = newPosition;
            }
            boardController.shootingBullet(attackerPosition, direction, null);
        } else if (placementAttacker.attackType == Placement.AttackType.Melee) { // melee hitee will fight back
            Vector2 newPosition = Vector2.moveDirection(attackerPosition, direction);
            Placement possibleHitee = boardController.getBoard().findPlacementByPosition(newPosition);
            if (possibleHitee != null) {
                boardController.shootingBullet(attackerPosition, direction, possibleHitee);
                boardController.getBoard().removingPlacement(Vector2.KeyGenerator(newPosition));// there may be some calculation for hit hitPoint and die condition
                boardController.getBoard().removingPlacement(Vector2.KeyGenerator(attackerPosition));
                return; // if the bullet is keep going, don't break it
            }
        }
    }
    //Hitee meant for a placement, target, hit by the bullet or melee weapon.
    private HashMap<String, BorderNode> movablePosition(Vector2 position, int layer) {
        HashMap<String, BorderNode> closestFreePositionList = new HashMap<>();
        closestFreePositionList.putAll(borderInfo(position));
        for (HashMap.Entry<String, BorderNode> ele : closestFreePositionList.entrySet()) {
            if (ele.getValue().borderInfo != BorderNode.BorderInfo.FreeSpace) {
                closestFreePositionList.remove(ele.getKey());
            }
        }
        return closestFreePositionList;
    }

    private HashMap<String, BorderNode> exploration(Vector2 position, int layer) {
        HashMap<String, BorderNode> boundary = new HashMap<>();
        int layerCounter = layer;
        boundary = borderInfo(position);
        layerCounter--;
        while (layerCounter > 0) {
            HashMap<String, BorderNode> anotherBoundary = new HashMap<>();
            for (HashMap.Entry<String, BorderNode> ele : boundary.entrySet()) {
                anotherBoundary.putAll(borderInfo(ele.getValue().position));
            }
            boundary.putAll(anotherBoundary);
            layerCounter--;
        }
        String key = Vector2.KeyGenerator(position);
        boundary.remove(key);
        return boundary;
    }

    private HashMap<String, BorderNode> borderInfo(Vector2 position) {
        HashMap<String, BorderNode> boundary = new HashMap<>();
        for (int i = 0; i < Vector2.DIRECTION_LIST.length; i++) {
            Vector2 newPosition = Vector2.moveDirection(position, Vector2.DIRECTION_LIST[i]);
            String key = Vector2.KeyGenerator(newPosition);
            if (!boardController.positionInBoard(newPosition)) {
                boundary.put(key, new BorderNode(newPosition, Vector2.DIRECTION_LIST[i], BorderNode.BorderInfo.OutOfBoundary, null));
            } else {
                Placement positionPlace = boardController.getBoard().findPlacementByPosition(newPosition);
                if (positionPlace != null) {
                    boundary.put(key, new BorderNode(newPosition, Vector2.DIRECTION_LIST[i], BorderNode.BorderInfo.Placement, positionPlace));
                } else {
                    boundary.put(key, new BorderNode(newPosition, Vector2.DIRECTION_LIST[i], BorderNode.BorderInfo.FreeSpace, null));
                }
            }
        }
        return boundary;
    }

}
