package com.gvnn.trb.simulator;

import com.gvnn.trb.exception.ToyRobotException;

public class ToyRobot {

    private Position position;
    private Direction direction;
    private Board board;

    public ToyRobot(Board board) {
        this.board = board;
    }

    /**
     * Places the robot on the board  in position X,Y and facing NORTH, SOUTH, EAST or WEST
     *
     * @param position  Robot position
     * @param direction Robot direction
     * @return true if placed successfully
     * @throws ToyRobotException
     */
    public boolean place(Position position, Direction direction) throws ToyRobotException {

        if (this.board == null)
            throw new ToyRobotException("Invalid board object");

        if (position == null)
            throw new ToyRobotException("Invalid position object");

        if (direction == null)
            throw new ToyRobotException("Invalid direction value");

        // validate the position
        if (!isValidPositionOnBoard(board, position))
            return false;

        // if position is valid then assign values to fields
        this.position = position;
        this.direction = direction;

        return true;
    }

    /**
     * Moves the robot one unit forward in the direction it is currently facing
     *
     * @return true if moved successfully
     */
    public boolean move() {
        if (this.position == null || this.board == null)
            return false;

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

    /**
     * Rotates the robot 90 degrees LEFT
     *
     * @return true if rotated successfully
     */
    public boolean rotateLeft() {
        if (this.direction == null)
            return false;

        this.direction = this.direction.leftDirection();
        return true;
    }

    /**
     * Rotates the robot 90 degrees RIGHT
     *
     * @return true if rotated successfully
     */
    public boolean rotateRight() {
        if (this.direction == null)
            return false;

        this.direction = this.direction.rightDirection();
        return true;
    }

    /**
     * Returns the X,Y and Direction of the robot
     */
    public String report() {
        if (this.position == null || this.direction == null)
            return null;

        return this.position.getX() + "," + this.position.getY() + "," + this.direction.toString();
    }

    private boolean isValidPositionOnBoard(Board board, Position position) {
        return !(
                position.getX() > board.getColumns() || position.getX() < 0 ||
                        position.getY() > board.getRows() || position.getY() < 0
        );
    }

    /**
     * Evals and executes a string command.
     *
     * @param inputString command string
     * @return string value of the executed command
     * @throws ToyRobotException
     */
    public String eval(String inputString) throws ToyRobotException {
        String[] args = inputString.split(" ");

        // validate command
        Command command;
        try {
            command = Command.valueOf(args[0]);
        } catch (IllegalArgumentException e) {
            throw new ToyRobotException("Invalid command");
        }
        if (command == Command.PLACE && args.length < 2) {
            throw new ToyRobotException("Invalid command");
        }

        // validate PLACE params
        String[] params;
        int x = 0;
        int y = 0;
        Direction commandDirection = null;
        if (command == Command.PLACE) {
            params = args[1].split(",");
            try {
                x = Integer.parseInt(params[0]);
                y = Integer.parseInt(params[1]);
                commandDirection = Direction.valueOf(params[2]);
            } catch (Exception e) {
                throw new ToyRobotException("Invalid command");
            }
        }

        String output;

        switch (command) {
            case PLACE:
                output = String.valueOf(place(new Position(x, y), commandDirection));
                break;
            case MOVE:
                output = String.valueOf(move());
                break;
            case LEFT:
                output = String.valueOf(rotateLeft());
                break;
            case RIGHT:
                output = String.valueOf(rotateRight());
                break;
            case REPORT:
                output = report();
                break;
            default:
                throw new ToyRobotException("Invalid command");
        }

        return output;
    }
}
