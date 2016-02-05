package tetra.logic;

import java.util.Random;

public class RandomIntStub extends Random {

    private final int[] values;
    int index;

    public RandomIntStub(int[] values) {
        this.values = values;
        this.index = 0;
    }

    @Override
    public int nextInt(int bound) {
        int next = nextInt();

        if (next < 0 || next >= bound) {
            String msg = String.format(
                    "nextInt out of bounds: should be [0, %d) but is %d", bound, next);
            throw new IllegalStateException(msg);
        }

        return next;
    }

    @Override
    public int nextInt() {
        int next = values[index++];
        if (index >= values.length) {
            index = 0;
        }

        return next;
    }

}
