package com.gvnn.trb.simulator;

import com.gvnn.trb.exception.ToyRobotException;

public class ToyRobot {

    private Position position;
    private Facing facing;
    private Board board;

    public boolean place(Board board, Position position, Facing facing) throws ToyRobotException {

        if (board == null)
            throw new ToyRobotException("Invalid board object");

        if (position == null)
            throw new ToyRobotException("Invalid position object");

        if (facing == null)
            throw new ToyRobotException("Invalid facing value");

        // validate the position
        if (!isValidPosition(board, position))
            return false;

        // if position is valid then assign values to fields
        this.board = board;
        this.position = position;
        this.facing = facing;

        return true;
    }

    public boolean move() {
        return false;
    }

    public Position getPosition() {
        return this.position;
    }

    public Facing getFacing() {
        return this.facing;
    }

    public boolean rotateLeft() {
        return false;
    }

    public boolean rotateRight() {
        return false;
    }

    private boolean isValidPosition(Board board, Position position) {
        return !(
                position.getX() > board.getColumns() || position.getX() < 0 ||
                        position.getY() > board.getRows() || position.getY() < 0
        );
    }
}
