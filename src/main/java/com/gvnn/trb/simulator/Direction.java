package com.gvnn.trb.simulator;

import java.util.HashMap;
import java.util.Map;

public enum Direction {

    NORTH(0), EAST(1), SOUTH(2), WEST(3);
    private static Map<Integer, Direction> map = new HashMap<Integer, Direction>();

    static {
        for (Direction directionEnum : Direction.values()) {
            map.put(directionEnum.direction, directionEnum);
        }
    }

    private final int direction;

    private Direction(int direction) {
        this.direction = direction;
    }

    public static Direction valueOf(int directionNum) {
        return map.get(directionNum);
    }

    public Direction rotateLeft() {
        return rotate(-1);
    }

    public Direction rotateRight() {
        return rotate(1);
    }

    private Direction rotate(int step) {
        return Direction.valueOf((Direction.valueOf(this.direction).direction + step) % map.size());
    }
}
