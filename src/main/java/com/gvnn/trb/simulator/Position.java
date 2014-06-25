package com.gvnn.trb.simulator;

public class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void change(int x, int y) {
        this.x = this.x + x;
        this.y = this.y + y;
    }
}
