package tetra.logic;

import org.junit.Test;
import static org.junit.Assert.*;

public class TetrominoShapeTest {

    private String[] getShape(Tetromino tetromino, Direction direction) {
        String[] shape = new String[tetromino.height];
        for (int y = 0; y < tetromino.height; y++) {
            shape[y] = getRow(tetromino, direction, y);
        }

        return shape;
    }

    private String getRow(Tetromino tetromino, Direction direction, int y) {
        StringBuilder row = new StringBuilder();
        for (int x = 0; x < tetromino.width; x++) {
            boolean isOccupied = tetromino.isOccupied(direction, x, y);

            if (isOccupied) {
                row.append("x");
            } else {
                row.append(" ");
            }
        }

        return row.toString();
    }

    String[][] i = {
        {
            "    ",
            "xxxx",
            "    ",
            "    "
        },
        {
            "  x ",
            "  x ",
            "  x ",
            "  x "
        },
        {
            "    ",
            "    ",
            "xxxx",
            "    "
        },
        {
            " x  ",
            " x  ",
            " x  ",
            " x  "
        }
    };

    @Test
    public void tetrominoIHasCorrectShape() {
        assertArrayEquals(i[0], getShape(Tetromino.I, Direction.UP));
        assertArrayEquals(i[1], getShape(Tetromino.I, Direction.RIGHT));
        assertArrayEquals(i[2], getShape(Tetromino.I, Direction.DOWN));
        assertArrayEquals(i[3], getShape(Tetromino.I, Direction.LEFT));
    }

    String[][] j = {
        {
            "x   ",
            "xxx ",
            "    ",
            "    "
        },
        {
            " xx ",
            " x  ",
            " x  ",
            "    "
        },
        {
            "    ",
            "xxx ",
            "  x ",
            "    "
        },
        {
            " x  ",
            " x  ",
            "xx  ",
            "    "
        }
    };

    @Test
    public void tetrominoJHasCorrectShape() {
        assertArrayEquals(j[0], getShape(Tetromino.J, Direction.UP));
        assertArrayEquals(j[1], getShape(Tetromino.J, Direction.RIGHT));
        assertArrayEquals(j[2], getShape(Tetromino.J, Direction.DOWN));
        assertArrayEquals(j[3], getShape(Tetromino.J, Direction.LEFT));
    }

    @Test
    public void testOneShapeWithoutHelperMethod() {
        // Row 0: "    "
        assertFalse(Tetromino.I.isOccupied(Direction.UP, 0, 0));
        assertFalse(Tetromino.I.isOccupied(Direction.UP, 1, 0));
        assertFalse(Tetromino.I.isOccupied(Direction.UP, 2, 0));
        assertFalse(Tetromino.I.isOccupied(Direction.UP, 3, 0));

        // Row 1: "xxxx"
        assertTrue(Tetromino.I.isOccupied(Direction.UP, 0, 1));
        assertTrue(Tetromino.I.isOccupied(Direction.UP, 1, 1));
        assertTrue(Tetromino.I.isOccupied(Direction.UP, 2, 1));
        assertTrue(Tetromino.I.isOccupied(Direction.UP, 3, 1));

        // Row 2: "    "
        assertFalse(Tetromino.I.isOccupied(Direction.UP, 0, 2));
        assertFalse(Tetromino.I.isOccupied(Direction.UP, 1, 2));
        assertFalse(Tetromino.I.isOccupied(Direction.UP, 2, 2));
        assertFalse(Tetromino.I.isOccupied(Direction.UP, 3, 2));

        // Row 3: "    "
        assertFalse(Tetromino.I.isOccupied(Direction.UP, 0, 3));
        assertFalse(Tetromino.I.isOccupied(Direction.UP, 1, 3));
        assertFalse(Tetromino.I.isOccupied(Direction.UP, 2, 3));
        assertFalse(Tetromino.I.isOccupied(Direction.UP, 3, 3));
    }

}
