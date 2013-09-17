import java.util.Arrays;

public class Fast {

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

        for (int i = 0; i < n - 1; i++) {
            Point[] array = new Point[n - i - 1];
            int k = 0;
            for (int j = i + 1; j < n; j++) {
                array[k] = points[j];
                k++;
            }

            Arrays.sort(array, points[i].SLOPE_ORDER);

            int newArrayIndex = 0;
            double slope = points[i].slopeTo(array[newArrayIndex]);
            int count = 0;

            while (newArrayIndex < array.length) {
                newArrayIndex++;
                if (newArrayIndex < array.length
                        && slope == points[i].slopeTo(array[newArrayIndex])) {
                    count++;
                } else {
                    if (newArrayIndex < array.length) {
                        slope = points[i].slopeTo(array[newArrayIndex]);
                    }
                    if (count >= 2) {
                        // remove sub segments
                        double oldSlope = points[i]
                                .slopeTo(array[newArrayIndex - 1]);
                        boolean print = true;
                        for (int p = 0; p < i; p++) {
                            if (points[p].slopeTo(points[i]) == oldSlope) {
                                print = false;
                                break;
                            }
                        }

                        if (print) {
                            Point prev = points[i];
                            StdOut.print(prev + " -> ");
                            for (int c = 0; c < count; c++) {
                                Point current = array[newArrayIndex - count - 1
                                        + c];
                                StdOut.print(current + " -> ");
                                prev = current;
                            }
                            points[i].drawTo(array[newArrayIndex - 1]);
                            StdOut.println(array[newArrayIndex - 1]);
                        }
                    }
                    count = 0;
                }
            }
        }
    }
}
