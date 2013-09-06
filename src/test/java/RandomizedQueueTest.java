
import junit.framework.Assert;

import org.junit.Test;
public class RandomizedQueueTest {
    @Test
    public void test(){
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        Assert.assertEquals(0, queue.size());
        queue.enqueue("5");
        testCorrect(queue, array("5"));
        Assert.assertEquals(1, queue.size());
        Assert.assertEquals("5", queue.dequeue());
        
        testCorrect(queue, array());
        
        Assert.assertEquals(0, queue.size());
        
        queue.enqueue("5");
        queue.enqueue("10");
        queue.enqueue("20");
        
        testCorrect(queue, array("5", "10", "20"));        
        
        Assert.assertEquals(3, queue.size());
        Assert.assertEquals("5", queue.dequeue());
        Assert.assertEquals(2, queue.size());
        Assert.assertEquals("10", queue.dequeue());
        Assert.assertEquals(1, queue.size());
        queue.enqueue("5");
        Assert.assertEquals(2, queue.size());
        testCorrect(queue, array("20", "5"));
        queue.enqueue("6");
        Assert.assertEquals(3, queue.size());

        testCorrect(queue, array("20", "5", "6"));
        
        Assert.assertEquals("20", queue.dequeue());
        Assert.assertEquals("5", queue.dequeue());
        Assert.assertEquals(1, queue.size());
        Assert.assertEquals("6", queue.dequeue());
        Assert.assertEquals(0, queue.size());
        
        queue.enqueue("5");
        queue.enqueue("6");
        queue.enqueue("7");
        queue.enqueue("8");
        Assert.assertEquals(4, queue.size());
        queue.dequeue();
        queue.dequeue();
        queue.enqueue("9");
        queue.enqueue("10");
        queue.dequeue();
        
        testCorrect(queue, array("8", "9", "10"));
        
        
        
    }
    
    private void testCorrect(RandomizedQueue<String> queue, String[] array){
        int i = 0;
        for(String q : queue){
            Assert.assertEquals(q, array[i]);
            i++;
        }
    }    
    
    private String[] array(String... args){
        return args;
    }
    
}
