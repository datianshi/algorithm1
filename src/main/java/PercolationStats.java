public class PercolationStats {

    private final double[] frac;
    private int T;

    public PercolationStats(int N, int T) {
        this.T = T;
        frac = new double[T];
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException(
                    "N and T could not be less or equal than 0");
        }
        for (int t = 0; t < T; t++) {
            Percolation percolation = new Percolation(N);
            double count = 0;
            while (!percolation.percolates()) {
                int i = StdRandom.uniform(1, N + 1);
                int j = StdRandom.uniform(1, N + 1);
                if (!percolation.isOpen(i, j)) {
                    percolation.open(i, j);
                    count++;
                }
            }
            frac[t] = count / (N * N);
        }

    }

    public double mean() {
        return StdStats.mean(frac);
    }

    public double stddev() {
        return StdStats.stddev(frac);
    }

    public double confidenceLo() {
        return this.mean() - (1.96 * this.stddev() / Math.sqrt(T));
    }

    public double confidenceHi() {
        return this.mean() + (1.96 * this.stddev() / Math.sqrt(T));
    }

    public static void main(String[] args) {
        int N = Integer.valueOf(args[0]);
        int T = Integer.valueOf(args[1]);
        PercolationStats stats = new PercolationStats(N, T);
        System.out.println("mean\t\t\t\t= " + stats.mean());
        System.out.println("stddev\t\t\t\t= " + stats.stddev());
        System.out.println("95% confidence interval\t\t= "
                + stats.confidenceLo() + ", " + stats.confidenceHi());
    }
}
