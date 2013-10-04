import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class PointSET {

    private SET<Point2D> points;

    public PointSET() {
        points = new SET<Point2D>();
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D p) {
        points.add(p);
    }

    public boolean contains(Point2D p) {
        return points.contains(p);
    }

    public void draw() {
        for (Point2D point : points) {
            point.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        final Stack<Point2D> retv = new Stack<Point2D>();
        for (Point2D point : points) {
            if (point.x() <= rect.xmax() && point.x() >= rect.xmin()
                    && point.y() <= rect.ymax() && point.y() >= rect.ymin()) {
                retv.push(point);
            }
        }
        return new Iterable<Point2D>() {

            public Iterator<Point2D> iterator() {
                return retv.iterator();
            }
        };
    }

    public Point2D nearest(Point2D p) {
        TreeSet<Point2D> set = new TreeSet<Point2D>(new DistanceToOrder(p));
        for (Point2D point : points) {
            set.add(point);
        }
        Point2D ceil = set.ceiling(p);
        Point2D floor = set.floor(p);

        if (ceil != null && floor != null) {
            return ceil.distanceTo(p) <= floor.distanceTo(p) ? ceil : floor;
        } else if (ceil == null) {
            return floor;
        } else {
            return ceil;
        }
    }

    private class DistanceToOrder implements Comparator<Point2D> {
        private Point2D point2d;

        public DistanceToOrder(Point2D point2d) {
            this.point2d = point2d;
        }

        public int compare(Point2D p, Point2D q) {
            double dist1 = point2d.distanceSquaredTo(p);
            double dist2 = point2d.distanceSquaredTo(q);
            if (dist1 < dist2)
                return -1;
            else if (dist1 > dist2)
                return +1;
            else
                return 0;
        }
    }
}
