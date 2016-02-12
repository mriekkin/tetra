package tetra.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Produces a random stream of Tetrominos which adheres to certain rules.
 */
public class RandomTetromino {

    private Random random;
    private List<Tetromino> bag;
    private int n;

    public RandomTetromino(Random random) {
        this.random = random;
        this.bag = createBagOfTetrominoes(4);
        this.n = bag.size();
    }

    private List<Tetromino> createBagOfTetrominoes(int duplicates) {
        Tetromino[] tetrominoes = Tetromino.values();

        List<Tetromino> list = new ArrayList<>();
        for (int i = 0; i < duplicates; i++) {
            Collections.addAll(list, tetrominoes);
        }

        return list;
    }

    public Tetromino nextTetromino() {
        if (n == 0) {
            n = bag.size();
        }

        int next = random.nextInt(n);
        return remove(next);
    }

    private Tetromino remove(int index) {
        Tetromino tetromino = bag.get(index);
        Collections.swap(bag, index, n - 1);
        n--;

        return tetromino;
    }

}
