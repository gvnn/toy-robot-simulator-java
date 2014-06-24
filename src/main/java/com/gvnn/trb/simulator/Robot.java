package com.gvnn.trb.simulator;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Robot {

    private Position position;
    private Facing facing;

    public boolean place(Board mockBoard, Position position, Facing north) {
        throw new NotImplementedException();
    }

    public boolean move() {
        throw new NotImplementedException();
    }

    public Position getPosition() {
        return this.position;
    }

    public Facing getFacing() {
        return facing;
    }

    public boolean rotateLeft() {
        throw new NotImplementedException();
    }

    public boolean rotateRight() {
        throw new NotImplementedException();
    }
}
