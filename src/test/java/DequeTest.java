
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DequeTest {
    
    @Test
    public void test(){
        Deque<Integer> deque = new Deque<Integer>();
        Assert.assertTrue(deque.isEmpty());
        
        deque.addFirst(5);
        //5
        Assert.assertFalse(deque.isEmpty());
        deque.removeLast();
        Assert.assertTrue(deque.isEmpty());
        
        deque.addLast(6);
        //6
        Assert.assertFalse(deque.isEmpty());
        //7 6
        deque.addFirst(7);
        //7 6 10
        deque.addLast(10);
        //8 7 6 10
        deque.addFirst(8);
        
        //8 7 6
        deque.removeLast();
        
        //7 6
        deque.removeFirst();
        
        //5 7 6 10
        deque.addFirst(5);
        deque.addLast(10);
        
        Assert.assertEquals(4, deque.size());
        int[] array = {5, 7, 6, 10};
        testCorrect(deque, array);
    }
    
    private void testCorrect(Deque<Integer> deque, int[] array){
        int i = 0;
        for(int d : deque){
            Assert.assertEquals(d, array[i]);
            i++;
        }
    }
}
