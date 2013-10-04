import java.util.Arrays;

public class Brute {

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        int index = 0;
        while (!in.isEmpty()) {
            int x = in.readInt();
            int y = in.readInt();

            points[index] = new Point(x, y);
            points[index].draw();
            index++;
        }

        Arrays.sort(points);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int l = j + 1; l < n; l++) {
                    for (int m = l + 1; m < n; m++) {
                        if (points[i].slopeTo(points[j]) == points[i]
                                .slopeTo(points[l])
                                && points[i].slopeTo(points[j]) == points[i]
                                        .slopeTo(points[m])) {
                            points[i].drawTo(points[m]);
                            StdOut.println(points[i] + " -> " + points[j]
                                    + " -> " + points[l] + " -> " + points[m]);
                        }
                    }
                }
            }
        }
    }
}
