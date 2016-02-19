package tetra.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Produces a random stream of Tetrominoes with specific properties. The
 * operation of this class is analogous to pulling pieces from a bag without
 * replacement. Initially the bag contains 4 instances of each piece, and pieces
 * are pulled until the bag is empty. After that the bag is filled again, again
 * with 4 instances of each piece.
 * <p>
 * This appears to be a common way to model randomness in Tetris-derivatives.
 * The objective is to reduce long sequences of the same type, and to ensure
 * that there won't be extremely long sequences without some preferred piece. It
 * also means that each piece will occur exactly 4 times in every 28 pieces.
 */
public class RandomTetromino {

    private Random random;
    private List<Tetromino> bag;
    private int n;

    /**
     * Class constructor which specifies the random generator used to produce a
     * random stream of integers.
     *
     * @param random random generator used to produce a random stream of
     * integers
     */
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

    /**
     * Returns the next tetromino pulled at random from a bag of tetrominoes.
     * The general contract of this class is that each piece will occur exactly
     * 4 times in every 28 pieces
     *
     * @return a tetromino pulled at random from a bag of tetrominoes
     */
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
