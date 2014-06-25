import com.gvnn.trb.exception.ToyRobotException;
import com.gvnn.trb.simulator.Board;
import com.gvnn.trb.simulator.Direction;
import com.gvnn.trb.simulator.Position;
import com.gvnn.trb.simulator.ToyRobot;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class ToyRobotTest {

    @Rule
    public org.junit.rules.ExpectedException thrown = ExpectedException.none();

    private Board getMockboard() {
        Board mockBoard = Mockito.mock(Board.class);
        when(mockBoard.getRows()).thenReturn(5);
        when(mockBoard.getColumns()).thenReturn(5);
        return mockBoard;
    }

    @Test
    public void testPlacing() throws ToyRobotException {

        Board mockBoard = getMockboard();

        ToyRobot toyRobot = new ToyRobot(mockBoard);

        Assert.assertTrue(toyRobot.place(new Position(0, 1), Direction.NORTH));
        Assert.assertTrue(toyRobot.place(new Position(2, 2), Direction.SOUTH));
        Assert.assertFalse(toyRobot.place(new Position(6, 6), Direction.WEST));
        Assert.assertFalse(toyRobot.place(new Position(-1, 5), Direction.EAST));
    }

    @Test
    public void testPlacingExceptions() throws ToyRobotException {

        Board mockBoard = getMockboard();
        ToyRobot robot = new ToyRobot(mockBoard);

        thrown.expect(ToyRobotException.class);
        robot.place(null, null);
        thrown.expect(ToyRobotException.class);
        robot.place(new Position(0, 1), null);
        thrown.expect(ToyRobotException.class);
        robot.place(null, Direction.EAST);
    }

    @Test
    public void testMovement() throws ToyRobotException {

        Board mockBoard = getMockboard();
        ToyRobot robot = new ToyRobot(mockBoard);

        robot.place(new Position(0, 0), Direction.NORTH);

        Assert.assertTrue(robot.move());
        Assert.assertEquals(0, robot.getPosition().getX());
        Assert.assertEquals(1, robot.getPosition().getY());
        Assert.assertEquals(Direction.NORTH, robot.getDirection());


        robot.place(new Position(1, 2), Direction.EAST);
        robot.move();
        robot.move();
        robot.rotateLeft();
        robot.move();

        Assert.assertEquals(3, robot.getPosition().getX());
        Assert.assertEquals(3, robot.getPosition().getY());
        Assert.assertEquals(Direction.NORTH, robot.getDirection());

        robot.place(new Position(0, 0), Direction.NORTH);
        robot.rotateRight();
        Assert.assertEquals(Direction.EAST, robot.getDirection());
        robot.rotateRight();
        Assert.assertEquals(Direction.SOUTH, robot.getDirection());
        robot.rotateRight();
        Assert.assertEquals(Direction.WEST, robot.getDirection());
        robot.rotateRight();
        Assert.assertEquals(Direction.NORTH, robot.getDirection());
        robot.rotateRight();
        Assert.assertEquals(Direction.EAST, robot.getDirection());


        robot.place(new Position(1, 4), Direction.NORTH);
        Assert.assertTrue(robot.move());
        Assert.assertFalse(robot.move());

    }

    @Test
    public void testReport() throws ToyRobotException {
        Board mockBoard = getMockboard();
        ToyRobot robot = new ToyRobot(mockBoard);
        robot.place(new Position(5, 5), Direction.EAST);
        Assert.assertEquals("5,5,EAST", robot.report());
        robot.move(); // this is going outside. Command is ignored and the report is the same as before
        Assert.assertEquals("5,5,EAST", robot.report());
        robot.rotateRight();
        robot.move();
        Assert.assertEquals("5,4,SOUTH", robot.report());
    }

    @Test
    public void testEval() throws ToyRobotException {
        Board mockBoard = getMockboard();
        ToyRobot robot = new ToyRobot(mockBoard);

        robot.eval("PLACE 0,0,NORTH");
        Assert.assertEquals("0,0,NORTH", robot.eval("REPORT"));
        robot.eval("MOVE");
        robot.eval("RIGHT");
        robot.eval("MOVE");
        Assert.assertEquals("1,1,EAST", robot.eval("REPORT"));
        // if it goes out of the board it ignores the command
        for (int i = 0; i < 10; i++)
            robot.eval("MOVE");
        Assert.assertEquals("5,1,EAST", robot.eval("REPORT"));

        //rotate on itself
        for (int i = 0; i < 4; i++)
            robot.eval("LEFT");
        Assert.assertEquals("5,1,EAST", robot.eval("REPORT"));

        // invalid commands
        thrown.expect(ToyRobotException.class);
        Assert.assertEquals("Invalid command", robot.eval("PLACE12NORTH"));
        thrown.expect(ToyRobotException.class);
        Assert.assertEquals("Invalid command", robot.eval("LEFFT"));
        thrown.expect(ToyRobotException.class);
        Assert.assertEquals("Invalid command", robot.eval("RIGHTT"));
    }
}