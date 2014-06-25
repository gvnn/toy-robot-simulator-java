package com.gvnn.trb.simulator;

import com.gvnn.trb.exception.ToyRobotException;

public class ToyRobot {

    private Position position;
    private Direction direction;
    private Board board;

    public boolean place(Board board, Position position, Direction direction) throws ToyRobotException {

        if (board == null)
            throw new ToyRobotException("Invalid board object");

        if (position == null)
            throw new ToyRobotException("Invalid position object");

        if (direction == null)
            throw new ToyRobotException("Invalid direction value");

        // validate the position
        if (!isValidPositionOnBoard(board, position))
            return false;

        // if position is valid then assign values to fields
        this.board = board;
        this.position = position;
        this.direction = direction;

        return true;
    }

    public boolean move() {
        // new position to validate
        Position newPosition = new Position(this.position);
        switch (this.direction) {
            case NORTH:
                newPosition.change(0, 1);
                break;
            case EAST:
                newPosition.change(1, 0);
                break;
            case SOUTH:
                newPosition.change(0, -1);
                break;
            case WEST:
                newPosition.change(-1, 0);
                break;
        }
        if (!isValidPositionOnBoard(this.board, newPosition))
            return false;

        // change position
        this.position = newPosition;
        return true;
    }

    public Position getPosition() {
        return this.position;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public boolean rotateLeft() {
        this.direction = this.direction.rotateLeft();
        return true;
    }

    public boolean rotateRight() {
        this.direction = this.direction.rotateRight();
        return true;
    }

    private boolean isValidPositionOnBoard(Board board, Position position) {
        return !(
                position.getX() > board.getColumns() || position.getX() < 0 ||
                        position.getY() > board.getRows() || position.getY() < 0
        );
    }
}
