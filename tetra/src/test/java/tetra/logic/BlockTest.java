package tetra.logic;

import org.junit.Test;
import static org.junit.Assert.*;

public class BlockTest {

    @Test
    public void constructorSetsColorCorrectly() {
        assertEquals(1010, new Block(1010).getColor());
    }

}
