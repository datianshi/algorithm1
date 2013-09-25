import java.util.Comparator;

public class Solver {
    private final Board initial;
    private final Stack<Board> boards;
    private final MinPQ<SearchNode> minQ = new MinPQ<SearchNode>(
            new SearchNodeComparator());
    private final MinPQ<SearchNode> twinQ = new MinPQ<SearchNode>(
            new SearchNodeComparator());
    private final SearchNode goalNode;
    private final boolean solvable;

    public Solver(Board initial) {
        this.initial = initial;
        this.boards = new Stack<Board>();

        minQ.insert(new SearchNode(initial, 0, null));
        twinQ.insert(new SearchNode(initial.twin(), 0, null));

        int dimension = initial.dimension();
        int[][] goal = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                goal[i][j] = i * dimension + j + 1;
            }
        }
        goal[dimension - 1][dimension - 1] = 0;
        Board goalBoard = new Board(goal);
        SearchNode minNode = null;
        SearchNode twinNode = null;
        while (!(minNode = minQ.delMin()).board.equals(goalBoard)
                && !(twinNode = twinQ.delMin()).board.equals(goalBoard)) {
            for (Board b : minNode.board.neighbors()) {
                if (minNode.prevNode == null
                        || !b.equals(minNode.prevNode.board)) {
                    minQ.insert(new SearchNode(b, minNode.moves + 1, minNode));
                }
            }
            for (Board b : twinNode.board.neighbors()) {
                if (twinNode.prevNode == null
                        || !b.equals(twinNode.prevNode.board)) {
                    twinQ.insert(new SearchNode(b, twinNode.moves + 1, twinNode));
                }
            }
        }

        if (minNode.board.equals(goalBoard)) {
            solvable = true;
            goalNode = minNode;
        } else {
            solvable = false;
            goalNode = null;
        }

    }

    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        int moves = -1;
        SearchNode node = goalNode;
        while (node != null) {
            node = node.prevNode;
            moves++;
        }
        return moves;
    }

    public Iterable<Board> solution() {
        if (goalNode == null) {
            return null;
        }
        Stack<Board> boards = new Stack<Board>();
        SearchNode node = goalNode;
        while (node != null) {
            boards.push(node.board);
            node = node.prevNode;
        }
        return boards;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        Solver solver = new Solver(initial);

        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    private class SearchNodeComparator implements Comparator<SearchNode> {

        public int compare(SearchNode b1, SearchNode b2) {
            return b1.board.manhattan() + b1.moves - b2.board.manhattan()
                    - b2.moves;
        }

    }

    private class SearchNode {
        Board board;
        int moves;
        SearchNode prevNode;

        public SearchNode(Board board, int moves, SearchNode prevNode) {
            this.board = board;
            this.moves = moves;
            this.prevNode = prevNode;
        }
    }
}
