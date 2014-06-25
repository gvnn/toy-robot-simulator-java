package com.gvnn.trb;

import com.gvnn.trb.exception.ToyRobotException;
import com.gvnn.trb.simulator.Board;
import com.gvnn.trb.simulator.ToyRobot;

import java.io.Console;

public class Main {

    public static void main(String[] args) {

        Console console = System.console();

        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        Board board = new Board(4, 4);
        ToyRobot robot = new ToyRobot(board);

        System.out.println("Toy Robot Simulator");
        System.out.println("Enter a command, Valid commands are:");
        System.out.println("\'PLACE X,Y,NORTH|SOUTH|EAST|WEST\', MOVE, LEFT, RIGHT, REPORT or EXIT");

        boolean keepRunning = true;
        while (keepRunning) {
            String inputString = console.readLine(": ");
            if ("EXIT".equals(inputString)) {
                keepRunning = false;
            } else {
                try {
                    String outputVal = robot.eval(inputString);
                    System.out.println(outputVal);
                } catch (ToyRobotException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}