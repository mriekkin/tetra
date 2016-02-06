package tetra.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TetrominoTest {

    @Test
    public void isOccupiedReturnsFalseForNegativeCoordinates() {
        assertEquals(false, Tetromino.O.isOccupied(Direction.UP, -1, 0));
        assertEquals(false, Tetromino.O.isOccupied(Direction.UP, 0, -1));
        assertEquals(false, Tetromino.O.isOccupied(Direction.UP, -1, -1));
    }

    @Test
    public void isOccupiedReturnsFalseForTooBigCoordinates() {
        assertEquals(false, Tetromino.I.isOccupied(Direction.DOWN, 4, 0));
        assertEquals(false, Tetromino.I.isOccupied(Direction.DOWN, 0, 4));
        assertEquals(false, Tetromino.I.isOccupied(Direction.DOWN, 4, 4));
    }

    @Test
    public void isOccupiedWorksForCornerCoordinates() {
        assertEquals(true, Tetromino.O.isOccupied(Direction.UP, 0, 0));
        assertEquals(true, Tetromino.I.isOccupied(Direction.UP, 3, 1));
    }

    @Test
    public void isOccupiedAndGetBlockReturnTheSameAnswer() {
        // Test with one block
        Tetromino tetromino = Tetromino.Z;
        Direction direction = Direction.DOWN;

        for (int y = 0; y < tetromino.HEIGHT; y++) {
            for (int x = 0; x < tetromino.WIDTH; x++) {
                String coordinates = String.format("Tetromino Z[DOWN] block (x,y) = (%d,%d)", x, y);

                boolean isOccupied = tetromino.isOccupied(direction, x, y);
                Block block = tetromino.getBlock(direction, x, y);

                assertEquals(coordinates, isOccupied, (block != null));
            }
        }
    }

    @Test
    public void getColorReturnsTheRightColor() {
        // Colors will be set when programming the GUI
        assertEquals(0, Tetromino.T.getColor());
    }

}
