package tetra.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomTetromino {

    private Random random;
    private List<Tetromino> bag;
    private int n;

    public RandomTetromino(Random random) {
        this.random = random;
        this.bag = createListOfTetrominoes(4);
        this.n = bag.size();
    }

    private List<Tetromino> createListOfTetrominoes(int numEach) {
        List<Tetromino> list = new ArrayList<>();
        for (Tetromino tetromino : Tetromino.values()) {
            for (int i = 0; i < numEach; i++) {
                list.add(tetromino);
            }
        }

        return list;
    }

    public Tetromino nextTetromino() {
        if (n == 0) {
            n = bag.size();
            Collections.sort(bag);
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
