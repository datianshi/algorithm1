
public class SimpleDrawVisualizer {

    public static void main(String[] args) {

        String filename = args[0];
        In in = new In(filename);


        StdDraw.show(0);

        kdTree kdtree = new kdTree();
//        PointSET kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
//            kdtree.insert(p);
            kdtree.insert(p);
        }

        // draw the points
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        kdtree.draw();
        StdDraw.show(40);

//        while (true) {
////            StdDraw.show(40);
//
//            // user starts to drag a rectangle
//            if (StdDraw.mousePressed() && !isDragging) {
//                x0 = StdDraw.mouseX();
//                y0 = StdDraw.mouseY();
//                isDragging = true;
//                continue;
//            }
//
//            // user is dragging a rectangle
//            else if (StdDraw.mousePressed() && isDragging) {
//                x1 = StdDraw.mouseX();
//                y1 = StdDraw.mouseY();
//                continue;
//            }
//
//            // mouse no longer pressed
//            else if (!StdDraw.mousePressed() && isDragging) {
//                isDragging = false;
//            }
//
//
//            RectHV rect = new RectHV(Math.min(x0, x1), Math.min(y0, y1),
//                                     Math.max(x0, x1), Math.max(y0, y1));
//            // draw the points
//            StdDraw.clear();
//            StdDraw.setPenColor(StdDraw.BLACK);
//            StdDraw.setPenRadius(.01);
//            kdtree.draw();
//
//            // draw the rectangle
//            StdDraw.setPenColor(StdDraw.BLACK);
//            StdDraw.setPenRadius();
//            rect.draw();
//
//            // draw the range search results for brute-force data structure in red
//            StdDraw.setPenRadius(.03);
//            StdDraw.setPenColor(StdDraw.RED);
//            for (Point2D p : kdtree.range(rect))
//                p.draw();
//
//            // draw the range search results for kd-tree in blue
////            StdDraw.setPenRadius(.02);
////            StdDraw.setPenColor(StdDraw.BLUE);
////            for (Point2D p : kdtree.range(rect))
////                p.draw();
//
//            StdDraw.show(40);
//        }
    }
}
