package com.gvnn.trb.simulator;


import java.util.HashMap;
import java.util.Map;

public enum Direction {

    NORTH(0), EAST(1), SOUTH(2), WEST(3);
    private static Map<Integer, Direction> map = new HashMap<Integer, Direction>();

    static {
        for (Direction directionEnum : Direction.values()) {
            map.put(directionEnum.directionIndex, directionEnum);
        }
    }

    private int directionIndex;

    private Direction(int direction) {
        this.directionIndex = direction;
    }

    public static Direction valueOf(int directionNum) {
        return map.get(directionNum);
    }

    public Direction leftDirection() {
        return rotate(-1);
    }

    public Direction rightDirection() {
        return rotate(1);
    }

    private Direction rotate(int step) {
        return Direction.valueOf(Math.abs(this.directionIndex + step) % map.size());
    }

}
