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

        ToyRobot toyRobot = new ToyRobot();

        Assert.assertTrue(toyRobot.place(mockBoard, new Position(0, 1), Direction.NORTH));
        Assert.assertTrue(toyRobot.place(mockBoard, new Position(2, 2), Direction.SOUTH));
        Assert.assertFalse(toyRobot.place(mockBoard, new Position(6, 6), Direction.WEST));
        Assert.assertFalse(toyRobot.place(mockBoard, new Position(-1, 5), Direction.EAST));
    }

    @Test
    public void testPlacingExceptions() throws ToyRobotException {

        Board mockBoard = getMockboard();
        ToyRobot robot = new ToyRobot();

        thrown.expect(ToyRobotException.class);
        robot.place(mockBoard, null, null);
        thrown.expect(ToyRobotException.class);
        robot.place(null, new Position(0, 1), null);
        thrown.expect(ToyRobotException.class);
        robot.place(null, null, Direction.EAST);
    }

    @Test
    public void testMovement() throws ToyRobotException {

        Board mockBoard = getMockboard();
        ToyRobot robot = new ToyRobot();

        robot.place(mockBoard, new Position(0, 0), Direction.NORTH);

        Assert.assertTrue(robot.move());
        Assert.assertEquals(robot.getPosition().getX(), 0);
        Assert.assertEquals(robot.getPosition().getY(), 1);
        Assert.assertEquals(robot.getDirection(), Direction.NORTH);


        robot.place(mockBoard, new Position(1, 2), Direction.EAST);
        robot.move();
        robot.move();
        robot.rotateLeft();
        robot.move();

        Assert.assertEquals(robot.getPosition().getX(), 3);
        Assert.assertEquals(robot.getPosition().getY(), 3);
        Assert.assertEquals(robot.getDirection(), Direction.NORTH);

        robot.place(mockBoard, new Position(0, 0), Direction.NORTH);
        robot.rotateRight();
        Assert.assertEquals(robot.getDirection(), Direction.EAST);
        robot.rotateRight();
        Assert.assertEquals(robot.getDirection(), Direction.SOUTH);
        robot.rotateRight();
        Assert.assertEquals(robot.getDirection(), Direction.WEST);
        robot.rotateLeft();
        Assert.assertEquals(robot.getDirection(), Direction.SOUTH);
        robot.rotateLeft();
        Assert.assertEquals(robot.getDirection(), Direction.EAST);
        robot.rotateLeft();
        Assert.assertEquals(robot.getDirection(), Direction.NORTH);

        robot.place(mockBoard, new Position(1, 4), Direction.NORTH);
        Assert.assertTrue(robot.move());
        Assert.assertFalse(robot.move());

    }

}