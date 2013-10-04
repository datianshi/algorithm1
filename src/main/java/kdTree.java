import java.util.Iterator;
import java.util.TreeSet;


public class kdTree {
    Node root = null;
    int size = 0;

    public kdTree() {
        
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        int height = 0;
        Node node = root;
        if (root == null){
            RectHV rectHV = new RectHV(0, 0, p.x(), 1);
            root = new Node(p, rectHV, null, null);
        }
        else insertHelper(height + 1, node, p);
    }
    
    private void insertHelper(int height, Node node, Point2D point){
        boolean left = false;
        RectHV rectHV = null;
        if (height%2 == 1){
            left = point.x() <= node.p.x();
            rectHV = left ? new RectHV(0, 0, node.p.x(), point.y()): new RectHV(node.p.x(), 0, 1, point.y());
        }
        else{
            left = point.y() <= node.p.y();
            rectHV = left ? new RectHV(0, 0, point.x(), node.p.y()) : new RectHV(0, node.p.y(), point.x(), 1);
        }
        if(left){
            if(node.lb == null){
                node.lb = new Node(point, rectHV, null, null);
            }
            else{
                insertHelper(height+1, node.lb, point);
            }
        }
        else{
            if(node.rt == null){
                node.rt = new Node(point, rectHV, null, null);
            }
            else{
                insertHelper(height+1, node.rt, point);
            }
        }
    }
    
    private boolean containsHelper(int height, Node node, Point2D point){
        boolean left = false;
        if(isEmpty()){ 
            return false;
        }
        else if(point.equals(node.p)){
            return true;
        }
        else if(height %2 == 0){
            left = point.x() <= node.p.x();
        }
        else{
            left = point.y() <= node.p.y();
        }
        if(left){
            if(node.lb == null){
                return false;
            }
            else {
                return containsHelper(height+1, node.lb, point);
            }
        }
        else{
            if(node.rt == null){
                return false;
            }
            else{
                return containsHelper(height+1, node.rt, point);
            }
        }
    }

    public boolean contains(Point2D p) {
        return containsHelper(0, root, p);
    }
    
    private void drawHelper(int height, Node node){
        if(node == null){ 
            return;
        }
        if(height % 2 == 0){
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.01);
            node.p.draw();
            StdDraw.setPenRadius(.005);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
//            Stand
        }
        else{
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.01);
            node.p.draw();
            StdDraw.setPenRadius(.005);
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
        }
        drawHelper(height+1, node.lb);
        drawHelper(height+1, node.rt);
    }    

    public void draw() {
        drawHelper(0, root);
    }

    private void rangeHelper(Stack<Point2D> stack, RectHV rect, Node node){
        if(node == null){
            return;
        }
        else{
            
        }
    }
    public Iterable<Point2D> range(RectHV rect) {
        Stack<Point2D> points = new Stack<Point2D>();
        
        return null;
    }

    public Point2D nearest(Point2D p) {
        return null;
    }
    
    private static class Node {
        Point2D p;
        
        public Node(Point2D p, RectHV rect, Node lb, Node rt) {
            super();
            this.p = p;
            this.rect = rect;
            this.lb = lb;
            this.rt = rt;
        }
        RectHV rect;    
        Node lb;        
        Node rt;        
     }    
}
