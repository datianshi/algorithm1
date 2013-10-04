import junit.framework.Assert;

import org.junit.Test;


public class KdtreeTest {
    @Test
    public void testKdTree(){
        kdTree kd = new kdTree();
        Point2D p1 = new Point2D(0.1, 0.2);
        Point2D p2 = new Point2D(0.15, 0.15);
        Point2D p3 = new Point2D(0.3, 0.1);
        Point2D p4 = new Point2D(0.6, 0.1);
        
        kd.insert(p1);
        kd.insert(p2);
        kd.insert(p3);
        
        Assert.assertTrue(kd.contains(p1));
        Assert.assertTrue(kd.contains(p2));
        Assert.assertTrue(kd.contains(p3));
        Assert.assertTrue(!kd.contains(p4));
    }
}
