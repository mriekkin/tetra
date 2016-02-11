package tetra.logic;

import org.junit.Test;
import static org.junit.Assert.*;

public class DirectionTest {

    @Test
    public void theDefaultDirectionIsUp() {
        assertEquals(0, Direction.UP.ordinal());
    }

    @Test
    public void directionsAreSpecifiedInTheCorrectOrder() {
        assertEquals(0, Direction.UP.ordinal());
        assertEquals(1, Direction.RIGHT.ordinal());
        assertEquals(2, Direction.DOWN.ordinal());
        assertEquals(3, Direction.LEFT.ordinal());
        assertEquals(4, Direction.values().length);
    }

    @Test
    public void verticalTranslationsAreDefinedCorrectly() {
        assertEquals(-1, Direction.UP.dy());
        assertEquals(0, Direction.RIGHT.dy());
        assertEquals(1, Direction.DOWN.dy());
        assertEquals(0, Direction.LEFT.dy());
    }

    @Test
    public void horizontalTranslationsAreDefinedCorrectly() {
        assertEquals(0, Direction.UP.dx());
        assertEquals(1, Direction.RIGHT.dx());
        assertEquals(0, Direction.DOWN.dx());
        assertEquals(-1, Direction.LEFT.dx());
    }

    @Test
    public void clockwiseRotationsAreDefinedCorrectly() {
        assertEquals(Direction.RIGHT, Direction.UP.rotate(true));
        assertEquals(Direction.DOWN, Direction.RIGHT.rotate(true));
        assertEquals(Direction.LEFT, Direction.DOWN.rotate(true));
        assertEquals(Direction.UP, Direction.LEFT.rotate(true));
    }

    @Test
    public void clockwiseRotationsAreDefinedCorrectly2() {
        assertEquals(Direction.RIGHT, Direction.UP.rotateClockwise());
        assertEquals(Direction.DOWN, Direction.RIGHT.rotateClockwise());
        assertEquals(Direction.LEFT, Direction.DOWN.rotateClockwise());
        assertEquals(Direction.UP, Direction.LEFT.rotateClockwise());
    }

    @Test
    public void counterclockwiseRotationsAreDefinedCorrectly() {
        assertEquals(Direction.LEFT, Direction.UP.rotate(false));
        assertEquals(Direction.UP, Direction.RIGHT.rotate(false));
        assertEquals(Direction.RIGHT, Direction.DOWN.rotate(false));
        assertEquals(Direction.DOWN, Direction.LEFT.rotate(false));
    }

    @Test
    public void counterclockwiseRotationsAreDefinedCorrectly2() {
        assertEquals(Direction.LEFT, Direction.UP.rotateCounterclockwise());
        assertEquals(Direction.UP, Direction.RIGHT.rotateCounterclockwise());
        assertEquals(Direction.RIGHT, Direction.DOWN.rotateCounterclockwise());
        assertEquals(Direction.DOWN, Direction.LEFT.rotateCounterclockwise());
    }

}
