import junit.framework.Assert;

import org.junit.Test;


public class PointTest {
    
    @Test
    public void testCompare(){
        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 3);
        
        Assert.assertTrue(p1.compareTo(p2) < 0);
        
        p1 = new Point(1, 2);
        p2 = new Point(1, 2);
        
        Assert.assertTrue(p1.compareTo(p2) == 0);
        
        p1 = new Point(1, 3);
        p2 = new Point(1, 2);
        
        Assert.assertTrue(p1.compareTo(p2) > 0);
        
    }
    
    @Test
    public void testSlope(){
        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 3);
        
        Assert.assertEquals(Double.POSITIVE_INFINITY, p1.slopeTo(p2));
        
        p2 = new Point(1, 2);
        Assert.assertEquals(Double.NEGATIVE_INFINITY, p1.slopeTo(p2));
        
        p2 = new Point(2, 2);
        Assert.assertEquals(0.0, p1.slopeTo(p2));
        
        p2 = new Point(2, 4);
        Assert.assertEquals(2.0, p1.slopeTo(p2));
        
        
        
    }    
}
