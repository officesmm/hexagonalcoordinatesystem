package org.vict.hexagonal.model.other;

public class Board {
    public int width;
    public int height;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean isInBoard(int x, int y) {
        return (x > 0 && x < width && y > 0 && y < height);
    }

    //Board may have other land data like tree and hill, that may require to create by Board build immutable design pattern
}
