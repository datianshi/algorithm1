import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class PointSET {

    SET<Point2D> points;

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
        TreeSet<Point2D> set = new TreeSet<Point2D>(p.DISTANCE_TO_ORDER);
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
}
