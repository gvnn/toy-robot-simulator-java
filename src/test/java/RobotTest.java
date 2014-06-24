import com.gvnn.trb.simulator.Board;
import com.gvnn.trb.simulator.Facing;
import com.gvnn.trb.simulator.Position;
import com.gvnn.trb.simulator.Robot;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class RobotTest {

    private Board getMockboard() {
        Board mockBoard = Mockito.mock(Board.class);
        when(mockBoard.getRows()).thenReturn(5);
        when(mockBoard.getColumns()).thenReturn(5);
        return mockBoard;
    }

    @Test
    public void testPlacing() {

        Board mockBoard = getMockboard();
        Robot robot = new Robot();

        Assert.assertTrue(robot.place(mockBoard, new Position(0, 1), Facing.NORTH));
        Assert.assertTrue(robot.place(mockBoard, new Position(2, 2), Facing.SOUTH));
        Assert.assertFalse(robot.place(mockBoard, new Position(6, 6), Facing.WEST));
        Assert.assertFalse(robot.place(mockBoard, new Position(-1, 5), Facing.EAST));
    }

    @Test
    public void testMovement() {

        Board mockBoard = getMockboard();
        Robot robot = new Robot();

        robot.place(mockBoard, new Position(0, 0), Facing.NORTH);

        Assert.assertTrue(robot.move());
        Assert.assertEquals(robot.getPosition().getX(), 0);
        Assert.assertEquals(robot.getPosition().getY(), 1);
        Assert.assertEquals(robot.getFacing(), Facing.NORTH);

        robot.place(mockBoard, new Position(1, 2), Facing.EAST);
        robot.move();
        robot.move();
        robot.rotateLeft();
        robot.move();

        Assert.assertEquals(robot.getPosition().getX(), 3);
        Assert.assertEquals(robot.getPosition().getY(), 3);
        Assert.assertEquals(robot.getFacing(), Facing.NORTH);

        robot.place(mockBoard, new Position(0, 0), Facing.NORTH);
        robot.rotateRight();
        Assert.assertEquals(robot.getFacing(), Facing.EAST);
        robot.rotateRight();
        Assert.assertEquals(robot.getFacing(), Facing.SOUTH);
        robot.rotateRight();
        Assert.assertEquals(robot.getFacing(), Facing.WEST);
        robot.rotateLeft();
        Assert.assertEquals(robot.getFacing(), Facing.SOUTH);
        robot.rotateLeft();
        Assert.assertEquals(robot.getFacing(), Facing.EAST);
        robot.rotateLeft();
        Assert.assertEquals(robot.getFacing(), Facing.NORTH);

        robot.place(mockBoard, new Position(1, 4), Facing.NORTH);
        Assert.assertTrue(robot.move());
        Assert.assertFalse(robot.move());

    }

}