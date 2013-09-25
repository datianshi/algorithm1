import java.util.Iterator;

public class Board {

    private final int[][] blocks;

    public Board(int[][] blocks) {
        this.blocks = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                this.blocks[i][j] = blocks[i][j];
            }
        }
    }

    public int dimension() {
        return blocks.length;
    }

    public int hamming() {
        int hamming = 0;
        int dimension = dimension();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (blocks[i][j] != 0 && blocks[i][j] != i * dimension + j + 1) {
                    hamming++;
                }
            }
        }
        return hamming;
    }

    public int manhattan() {
        int manhattan = 0;
        int dimension = dimension();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int row = (blocks[i][j] - 1) / dimension;
                int col = (blocks[i][j] - 1) % dimension;
                if (blocks[i][j] != 0) {
                    manhattan += (Math.abs(row - i) + Math.abs(col - j));
                }
            }
        }
        return manhattan;
    }

    public boolean isGoal() {
        int dimension = dimension();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (blocks[i][j] == 0) {
                    continue;
                }
                int row = (blocks[i][j] - 1) / dimension;
                int col = (blocks[i][j] - 1) % dimension;
                if (row != i || col != j) {
                    return false;
                }
            }
        }
        return true;
    }

    public Board twin() {
        // a board obtained by exchanging two adjacent blocks in the same row
        int dimension = dimension();
        int[][] newBlocks = copyBlocks();
        for (int i = 0; i < dimension; i++) {
            if (newBlocks[i][0] != 0 && newBlocks[i][1] != 0) {
                int temp = newBlocks[i][0];
                newBlocks[i][0] = newBlocks[i][1];
                newBlocks[i][1] = temp;
                break;
            }
        }
        return new Board(newBlocks);
    }

    public boolean equals(Object y) {
        if (!(y instanceof Board)) {
            return false;
        }

        Board o = (Board) y;
        int dimension = dimension();
        if (dimension != o.dimension()) {
            return false;
        }

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (blocks[i][j] != o.blocks[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        // all neighboring boards
        return new Iterable<Board>() {

            public Iterator<Board> iterator() {
                return new BoardIterator();
            }
        };
    }

    private class BoardIterator implements Iterator<Board> {

        // UP, Right, Down, Left
        private boolean[] move = new boolean[4];
        private int index = 0;
        private int col = 0;
        private int row = 0;

        public BoardIterator() {
            int dimension = dimension();
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    if (blocks[i][j] == 0) {
                        row = i;
                        col = j;
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                move[i] = true;
            }

            if (row == dimension - 1) {
                move[0] = false;
            }
            if (col == 0) {
                move[1] = false;
            }
            if (row == 0) {
                move[2] = false;
            }
            if (col == dimension - 1) {
                move[3] = false;
            }
        }

        public boolean hasNext() {
            for (int i = index; i < 4; i++) {
                if (move[i])
                    return true;
            }
            return false;
        }

        public Board next() {
            int i = index;
            while (!move[i]) {
                i++;
            }
            index = i + 1;

            int[][] newBlocks = copyBlocks();

            if (i == 0) {
                newBlocks[row][col] = newBlocks[row + 1][col];
                newBlocks[row + 1][col] = 0;
            }
            if (i == 1) {
                newBlocks[row][col] = newBlocks[row][col - 1];
                newBlocks[row][col - 1] = 0;
            }
            if (i == 2) {
                newBlocks[row][col] = newBlocks[row - 1][col];
                newBlocks[row - 1][col] = 0;
            }
            if (i == 3) {
                newBlocks[row][col] = newBlocks[row][col + 1];
                newBlocks[row][col + 1] = 0;
            }

            return new Board(newBlocks);
        }

        public void remove() {

        }

    }

    private int[][] copyBlocks() {
        int dimension = dimension();
        int[][] newBlocks = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                newBlocks[i][j] = blocks[i][j];
            }
        }
        return newBlocks;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dimension() + "\n");
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}