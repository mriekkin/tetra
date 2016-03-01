package tetra.logic;

public class RandomTetrominoStub extends RandomTetromino {

    private final Tetromino[] sequence;
    int index;

    public RandomTetrominoStub(Tetromino[] sequence) {
        super(new RandomIntStub(new int[]{0}));
        this.sequence = sequence;
        this.index = 0;
    }

    @Override
    public Tetromino nextTetromino() {
        Tetromino next = sequence[index++];
        if (index >= sequence.length) {
            index = 0;
        }

        return next;
    }

}
