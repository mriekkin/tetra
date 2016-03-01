package tetra.logic;

import java.awt.Color;
import org.junit.Test;
import static org.junit.Assert.*;

public class BlockTest {

    @Test
    public void constructorSetsColorCorrectly() {
        assertEquals(1010, new Block(1010).getColor());
    }

    @Test
    public void constructorSetsAwtColorObjectCorrectly() {
        assertEquals(new Color(1010), new Block(1010).getColorAwt());
    }

}
